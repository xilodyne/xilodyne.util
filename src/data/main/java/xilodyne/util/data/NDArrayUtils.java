package xilodyne.util.data;

import mikera.arrayz.INDArray;
import mikera.arrayz.NDArray;

/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class NDArrayUtils {
	
	public static float[] convertNDArrayEntryToFloatArray(INDArray value) {
		//List<Float> alValues = new ArrayList<Float>();

		//INDArray value = getElement.next();
		float[] floatArray = new float[value.getShape(0)];
		for (int index = 0; index < floatArray.length; index++) {
			floatArray[index] = (float)value.get(index);
		}
		//for (int index = 0; index < sampleValues.getShape(1); index++) {
			//alValues.add((float) value.get(index));
			
		//}
		return floatArray;
	}
	
	public static double[][] dump2D_NDArray(NDArray ndarray) {
		ndarray.toDoubleArray();
		double [][] dumpedArray = new double [2][ndarray.getShape(0)];
		System.out.println("NDArray size:" + ndarray.getShape(0));
		System.out.println("NDArray double size: " + ndarray.toDoubleArray().length);
		//get 1d array from ndarray, split into two arrays
		int index=0;
		//int dimension = 0;
		boolean firstCol = true;
		for (double d : ndarray.toDoubleArray()) {

			if (firstCol) {
				dumpedArray[0][index] = d;
	//			System.out.print("\tindex: " + index +"\tfirstCol: "+ firstCol +"\td:" + d);
	//			System.out.print("["+dumpedArray[0][index]+"]");
				firstCol = false;

			} else {
				dumpedArray[1][index] = d;
	//			System.out.println("\tindex: " + index +"\tfirstCol: "+ firstCol +"\td:" + d);
	//			System.out.println("["+dumpedArray[1][index]+"]");
				firstCol = true;
				index++;

			}

			 
		}
		return dumpedArray;
		
	}


}
