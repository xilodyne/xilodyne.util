package xilodyne.util.jpyplot.examples;


//import xilodyne.util.jnumpy.J2NumPY;
import xilodyne.util.logger.Logger;
import xilodyne.util.python.pyplot.ScatterPlot;

public class CreateScatterPlot {

	static private Logger log = new Logger("plot");
	static private ScatterPlot scatterPlot;
	
	static private double x_min = 0.0, x_max = 1.0;
	static private double y_min = 0.0, y_max = 1.0;
	static private double h = 0.01;

	
	public static void main(String[] args) {
		//double[][] XX = J2NumPY.meshgrid_getXX(J2NumPY.arange(x_min, x_max, h), J2NumPY.arange(y_min, y_max, h));
		//double[][] YY = J2NumPY.meshgrid_getYY(J2NumPY.arange(x_min, x_max, h), J2NumPY.arange(y_min, y_max, h));

		//System.out.println(ArrayUtils.printArray(XX));
		log.logln_withClassName(Logger.lF, "Predicting frontier (backgroup display).");
	//	double[] boundaryDecision = clf.predict(J2NumPY._c(J2NumPY.ravel(XX), J2NumPY.ravel(YY)));
	//	double[][] boundaryDecisionReshaped = J2NumPY.shape1D_2_2D(boundaryDecision, XX.length, XX[0].length);

		scatterPlot = new ScatterPlot("Test Scatter Plot", "x axis", "y axis");


	}

}
