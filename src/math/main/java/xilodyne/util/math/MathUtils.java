package xilodyne.util.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class MathUtils {
	
	//private static Logger log = new Logger();


	//https://en.wikipedia.org/wiki/Standard_deviation
	public static double getStandardDeviation(double mean, double val) {
		double result = 0;
		result = (val - mean) * (val - mean);
		//System.out.println("value: " + val+" mean: " + mean +" sqrt: " + Math.sqrt(result));
		return Math.sqrt(result);
	}
	
	//assuming have entire population of data and not a sample of data
	public static double getStandardScore(double mean, double val) {
		double result = 0;
		double stdDev = MathUtils.getStandardDeviation(mean, val);
		result = (val - mean) / stdDev;
		return result;
	}
	
	public static int getMeanRounded (ArrayList<Float> list) {
		float sum = 0;
		for (float f : list) {
			sum += f;
		}
		sum = sum / list.size();
		return Double.valueOf(sum).intValue();
	}
	
	public static float getMean (ArrayList<Float> list) {
		float sum = 0;
		for (float f : list) {
			sum += f;
		}
		return sum = sum / list.size();
	}
	
	public static void sortList(ArrayList<Float> list) {
		Collections.sort(list, new Comparator<Float>() {
			@Override
			public int compare(Float lbl1, Float lbl2) {
				return lbl1.compareTo(lbl2);
			}
		});
	}
	


	
}
