package xilodyne.util;


/**
 * Routines for working with NDArray, double[], double[][]
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.1
 */
public class ArrayUtils {


	

	/**
	 * @param array
	 *            double[]
	 * @return String [value value value ...]
	 */
	public static String print1DArray(double[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int lineBreak = 0;
		for (int x = 0; x < array.length; x++) {
			sb.append(String.format("%.1f", array[x]) + "  ");
			lineBreak++;
			if (lineBreak > 13) {
				sb.append("\n");
				lineBreak = 0;
			}
		}
		sb.append("]\n");

		return sb.toString();
	}

	/**
	 * @param array
	 *            double[][]
	 * @return String
	 */
	public static String print2DArray(double[][] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int y = 0; y < array[0].length; y++) {
			sb.append("[");
			int lineBreak = 0;
			for (int x = 0; x < array.length; x++) {
				sb.append(String.format("%.1f", array[x][y]) + "  ");
				if (lineBreak > 13) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
			if (y == (array[0].length - 1)) {
				sb.append("]");
			}
			sb.append("]\n");
		}

		return sb.toString();
	}

}
