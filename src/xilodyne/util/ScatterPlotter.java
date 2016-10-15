package xilodyne.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;

/**
 * Wrapper class for jFreeChart scatter plot. Configured to match the python
 * matplotlib.pyplot scatter plot methods and presentation.
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.1
 */

public class ScatterPlotter {

	private Logger log = new Logger();

	// private final Random rand = new Random();
	private static final int REND_FRONTIER = 1;
	private static final int REND_DATA = 0;
	private static final int dimX = 800;
	private static final int dimY = 600;

	private XYSeriesCollection graphDataSet = new XYSeriesCollection();
	private XYSeriesCollection frontierDataset = null;

	private XYPlot plot0, plot1;
	private int seriesIndex = -1;
	private JFreeChart chart;
	private XYDotRenderer renderFrontier = new XYDotRenderer();
	private XYLineAndShapeRenderer renderData;
	private LegendItemCollection chartLegend = new LegendItemCollection();

	
	/**
	 * Create new chart object.
	 * @param chartName Title that appears at top of chart
	 * @param xAxis  Name of axis on bottom
	 * @param yAxis  Name of axis on left side
	 */
	public ScatterPlotter(String chartName, String xAxis, String yAxis) {
		
		log.logln_withClassName(G.LOG_FINE, "Creating chart [" + chartName +"]");
		chart = this.createChart(chartName, xAxis, yAxis);

		ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new Dimension(dimX, dimY));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setPreferredSize(chartPanel.getPreferredSize());
		scrollPane.setViewportView(chartPanel);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}

	private JFreeChart createChart(String chartName, String xAxis, String yAxis) {

		JFreeChart chart = ChartFactory.createScatterPlot(chartName, xAxis, // x
																			// axis
																			// label
				yAxis, // y axis label
				this.frontierDataset, // data ***-----PROBLEM------***
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);

		chart.setBackgroundPaint(Color.white);

		renderData = new XYLineAndShapeRenderer(false, true) {

			private static final long serialVersionUID = 1L;

			@Override
			public Shape getItemShape(int row, int column) {
				return createCircle(7);
			}
		};

		// set a few custom plot features
		plot0 = (XYPlot) chart.getPlot();
		plot0.setBackgroundPaint(new Color(0xffffe0));
		plot0.setDomainGridlinesVisible(false);
		// plot0.setDomainGridlinePaint(Color.YELLOW);
		// plot0.setRangeGridlinePaint(Color.lightGray);
		// chart.removeLegend();

		plot1 = (XYPlot) chart.getPlot();
		// plot1.setBackgroundPaint(new Color(0xffffe0));
		plot1.setBackgroundPaint(new Color(255, 255, 255));
		plot1.setDomainGridlinesVisible(false);
		// plot1.setDomainGridlinePaint(Color.YELLOW);
		// plot1.setRangeGridlinePaint(Color.lightGray);

		NumberAxis domain = (NumberAxis) plot0.getDomainAxis();
		domain.setRange(0.00, 1.00);
		domain.setTickUnit(new NumberTickUnit(0.2));
		domain.setVerticalTickLabels(true);
		NumberAxis range = (NumberAxis) plot0.getRangeAxis();
		range.setRange(0.0, 1.0);
		range.setTickUnit(new NumberTickUnit(0.2));

		chart.removeLegend();

		LegendTitle lt = new LegendTitle(plot1);
		lt.setItemFont(new Font("Dialog", Font.PLAIN, 20));
		// lt.setItemFont(new Font("Dialog", Font.PLAIN, 9));
		lt.setBackgroundPaint(Color.white);
		lt.setFrame(new BlockBorder(Color.white));
		lt.setPosition(RectangleEdge.BOTTOM);

		XYTitleAnnotation ta = new XYTitleAnnotation(0.90, 0.90, lt, RectangleAnchor.TOP_RIGHT);

		ta.setMaxWidth(0.48);
		plot1.addAnnotation(ta);

		chart.getXYPlot().setRenderer(ScatterPlotter.REND_FRONTIER, renderFrontier);
		chart.getXYPlot().setRenderer(ScatterPlotter.REND_DATA, renderData);

		return chart;

	}

	/**
	 * Create a visual boundary in the chart background based upon
	 * the dataset.  Values can be either 0 or 1.  0 added to Series 0
	 * coordinates, 1 added to Series 1 coordinates.  Each series has 
	 * its own color.
	 * 
	 * @param frontier double[][] x,y = 0 or 1
	 * @param cOne Color One
	 * @param cTwo Color Two
	 */
	public void addFrontier(double[][] frontier, Color cOne, Color cTwo) {
		log.logln(G.lF, "Creating frontier.");
		frontierDataset = this.loadFrontierDataset(frontier);
		int dotSizeHeight = dimY / frontier[0].length;
		int dotSizeWidth = dimX / frontier.length;
		log.logln_withClassName(G.lD, "Dot size x,y: " + dotSizeWidth + "," + dotSizeHeight);

		renderFrontier.setDotHeight(dotSizeHeight);
		renderFrontier.setDotWidth(dotSizeWidth);
		renderFrontier.setSeriesPaint(0, cOne);
		renderFrontier.setSeriesPaint(1, cTwo);

		plot0.setDataset(ScatterPlotter.REND_FRONTIER, frontierDataset);

	}

	
	/**
	 * Create a series of dots to display on chart.
	 * 
	 * @param name to appear in chart
	 * @param x coordinate
	 * @param y coordinate
	 * @param color of the series
	 */
	public void scatter(String name, double[] x, double[] y, Color color) {
		log.logln(G.lF, "Creating series [" + name +"]");
		int nextSeriesIndex = this.getNextSeriesIndex();
		renderData.setSeriesPaint(nextSeriesIndex, color);
		this.graphDataSet.addSeries(this.createDatasetSeries(name, x, y));
		// this.graphDataSet.addSeries(series);
		plot1.setDataset(ScatterPlotter.REND_DATA, this.graphDataSet);

		chartLegend.add(new LegendItem(name + "  ", null, null, null, this.createCircle(10), color));
		plot1.setFixedLegendItems(chartLegend);
	}

	
	/**
	 * Create a new set of data to plot.
	 * 
	 * @param name to appear on chart
	 * @param x  x coordinates
	 * @param y  y coordinates
	 * @return XYseries
	 */
	private XYSeries createDatasetSeries(String name, double[] x, double[] y) {
		XYSeries series = new XYSeries(name);
		for (int index = 0; index < x.length; index++) {
			series.add(x[index], y[index]);
		}
		return series;
	}

	/**
	 * Create a frontier between two datasets.  Values can only
	 * be 0 or 1.  If 0 output one color, if 1 output different color.
	 * 
	 * @param Z double[][] x,y  0 or 1
	 * @return XYSeriesCollection for adding to background
	 */
	private XYSeriesCollection loadFrontierDataset(double[][] Z) {
		// two series: 0 & 1
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series0 = new XYSeries("0");
		XYSeries series1 = new XYSeries("1");
		int xLength = Z.length;
		int yLength = Z[0].length;
		for (int iY = 0; iY < yLength; iY++) {
			for (int iX = 0; iX < xLength; iX++) {
				/** create a cell entry spaced equally for width of chart */
				double x = (iX / (double) xLength) + ((1 / (double) xLength) / 2);
				double y = (iY / (double) yLength) + ((1 / (double) yLength) / 2);
				if (Z[iY][iX] == 0) {
					series0.add(x, y);
				} else {
					series1.add(x, y);
				}
			}
		}
		dataset.addSeries(series0);
		dataset.addSeries(series1);

		return dataset;
	}

	private int getNextSeriesIndex() {
		this.seriesIndex++;
		return this.seriesIndex;
	}

	private Shape createCircle(double size) {
		return new Ellipse2D.Double(-size / 2, -size / 2, size, size);
	}

	/**
	 * Save chart to file in PNG format. File name is same name as chart name.
	 * 
	 * @throws IOException
	 *             on error.
	 */
	public void saveChartToPNG() throws IOException {
		final String chartTitle = chart.getTitle().getText().replace(' ', '_');
		ChartUtilities.saveChartAsPNG(new File(chartTitle + ".png"), chart, dimX, dimY);
	}

}
