package xilodyne.util;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import mikera.arrayz.INDArray;
import mikera.arrayz.NDArray;


/**
 * Routines for working with NDArray, double[], double[][]
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.2
 */
public class ArrayUtils {


	

	/**
	 * @param array
	 *            double[]
	 * @return String [value value value ...]
	 */
	public static String printArray(double[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (array == null) {
			System.out.println(" null array ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < array.length; x++) {
				sb.append(String.format("%.1f", array[x]) + "  ");
				lineBreak++;
				if (lineBreak > 13) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		sb.append("]");

		return sb.toString();
	}

	/**
	 * @param array
	 *            double[]
	 * @return String [value value value ...]
	 */
	public static String printArrayShowFull(double[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (array == null) {
			System.out.println(" null array ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < array.length; x++) {
//				sb.append(String.format("%.1f", array[x]) + "  ");
				sb.append(array[x]+ "  ");
				lineBreak++;
				if (lineBreak > 13) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		sb.append("]");

		return sb.toString();
	}
	
	/**
	 * @param array
	 *            int[]
	 * @return String [value value value ...]
	 */
	public static String printArray(int[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (array == null) {
			System.out.println(" null array ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < array.length; x++) {
				sb.append(array[x] + "  ");
				lineBreak++;
				if (lineBreak > 13) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		sb.append("]");

		return sb.toString();
	}

	/**
	 * @param array
	 *            int[]
	 * @return String [value value value ...]
	 */
	public static String printArray(float[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (array == null) {
			System.out.println(" null array ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < array.length; x++) {
				sb.append(array[x] + "  ");
				lineBreak++;
				if (lineBreak > 13) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		sb.append("]");

		return sb.toString();
	}

	/**
	 * @param array
	 *            String[]
	 * @return String ['value...','value...','value ...]
	 */
	public static String printArray(String[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (array == null) {
			System.out.println(" null array ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < array.length; x++) {
				sb.append("'" + array[x] + "',");
				lineBreak++;
				if (lineBreak > 30) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		//remove last comma
		sb.deleteCharAt(sb.length()-2);
		sb.append("]");

		return sb.toString();
	}

	/**
	 * @param array
	 *            double[][]
	 * @return String
	 */
	public static String printArray(double[][] array) {
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
			sb.append("]");
		}

		return sb.toString();
	}

	/**
	 * @param array
	 *            String[]
	 * @return String ['value...','value...','value ...]
	 */
	public static String printArray(List<String> array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (array == null) {
			System.out.println(" null array ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < array.size(); x++) {
				sb.append("'" + array.get(x) + "',");
				lineBreak++;
				if (lineBreak > 20) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		//remove last comma
		sb.deleteCharAt(sb.length()-2);
		sb.append("]");

		return sb.toString();
	}


	/**
	 * @param array
	 *            String[]
	 * @return String ['value...','value...','value ...]
	 */
	public static String printArrayVertcal(List<String> array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (array == null) {
			System.out.println(" null array ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < array.size(); x++) {
				sb.append("'" + array.get(x) + "',");
				lineBreak++;
				if (lineBreak > 0) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		//remove last comma
		sb.deleteCharAt(sb.length()-2);
		sb.append("]");

		return sb.toString();
	}

	/**
	 * @param array
	 *            String[]
	 * @return String ['value...','value...','value ...]
	 */
	public static String printListInt(List<Integer> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (list == null) {
			System.out.println(" null list ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < list.size(); x++) {
				sb.append(list.get(x) + ", ");
				lineBreak++;
				if (lineBreak > 13) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		//remove last comma
		sb.deleteCharAt(sb.length()-2);
		sb.append("]");

		return sb.toString();
	}
	/**
	 * @param array
	 *            String[]
	 * @return String ['value...','value...','value ...]
	 */
	
	public static String printListFloat(ArrayList<Float> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (list == null) {
			System.out.println(" null list ");
		} else {
			int lineBreak = 0;
			for (int x = 0; x < list.size(); x++) {
				sb.append(list.get(x) + ", ");
				lineBreak++;
				if (lineBreak > 13) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		//remove last comma
		sb.deleteCharAt(sb.length()-2);
		sb.append("]");

		return sb.toString();
	}

	/**
	 * @param array
	 *            String[]
	 * @return String ['value...','value...','value ...]
	 */
/*	public static String printKeysSortMap(ArrayList<SortedMap<Float, int[]>> smap) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (smap == null) {
			System.out.println(" null list ");
		} else {
			int lineBreak = 0;
			Iterator<SortedMap<Float, int[]>> loop = smap.iterator();
			while (loop.hasNext()) {
				
				float key = loop.next();
				sb.append(key);
			}
			for (int x = 0; x < list.size(); x++) {
				sb.append(list.get(x) + ", ");
				lineBreak++;
				if (lineBreak > 13) {
					sb.append("\n");
					lineBreak = 0;
				}
			}
		}
		//remove last comma
		sb.deleteCharAt(sb.length()-2);
		sb.append("]");

		return sb.toString();
	}
*/
	public static String printHashMap(HashMap<String, Object> map){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (map == null) {
			System.out.println(" null list ");
		} else {
			Iterator<String> keys = map.keySet().iterator();
			
			//get creater header, assuming if key has value then at one line exists
			//List<String> header = new Vector<String>();
		//	List<String[]> lines = new Vector<String[]>();
			List<String> lines = new Vector<String>();

			boolean doHeader = true;
			
			while (keys.hasNext() ) {
				String key = keys.next();
	//			sb.append("KEY: " + key + "\n");
				
				if (doHeader) {
					lines.add(ArrayUtils.extractKorV((String) map.get(key).toString(), 
							0, "Name"));
					lines.add(ArrayUtils.extractKorV((String) map.get(key).toString(), 
							1, key));
					doHeader = false;
				} else {
					lines.add(ArrayUtils.extractKorV((String) map.get(key).toString(), 
							1, key));
				}
				//System.out.println("list: " + ArrayUtils.printListString(lines));
				/*
	//			String keyValue = (String) map.get(key).toString();
				System.out.println("keyvalue: " + keyValue);
				keyValue = keyValue.replaceAll("\\{", "");
				keyValue = keyValue.replaceAll("}", "");
				
				List<String> keyValueList = Arrays.asList(keyValue.split(","));
				//create the head and lines
				System.out.println(ArrayUtils.printListString(keyValueList));
				System.out.println(keyValueList.size());
				//sb.append(ArrayUtils.printListString(keyValue));
				Iterator<String> values = keyValueList.iterator();
				
				//sample line
				//KEY: GRAMM WENDY L
				//['{from_poi_to_this_person=NaN, other=NaN, director_fees=119292, to_messages=NaN, total_stock_value=NaN, bonus=NaN, total_payments=119292, poi=false, salary=NaN, from_this_person_to_poi=NaN, loan_advances=NaN, deferred_income=NaN, email_address=NaN, shared_receipt_with_poi=NaN, restricted_stock=NaN, from_messages=NaN, long_term_incentive=NaN, deferral_payments=NaN, restricted_stock_deferred=NaN, exercised_stock_options=NaN, expenses=NaN}]
				while (values.hasNext()) {
					String extract = values.next();
					String[] extractList = extract.split("=");

					System.out.println("extract: " + extract);
					System.out.println(ArrayUtils.printArray(extractList));
					System.out.println(extractList[0]);
					header.add(extractList[0]);
					lines.add(extractList[1]);
				}
				sb.append("Header: " + ArrayUtils.printListString(header)+"\n");
				sb.append("line: " + ArrayUtils.printListString(lines));
				
				
				//get all values for key
		//		sb.append("val: " + map.get(val));
		//		sb.append("\n");
*/
			}
			
			System.out.println("lines size: " + lines.size());
			Iterator<String> loop = lines.iterator();
			while (loop.hasNext()) {
				//sb.append(ArrayUtils.printArray(loop.next()));
				sb.append(loop.next());
			}
			
			//return ArrayUtils.printListString(lines);

		}
		//remove last comma
		//sb.deleteCharAt(sb.length()-2);
		sb.append("]");

		return sb.toString();
	}
	
	//assuming that first value is either the key or the key header name
	//index to get, 0 = header, 1 = value
	public static String extractKorV(String line, int indexToGet,
			String firstValue) {
		List<String> list = new Vector<String>();
		list.add(firstValue);
		
		String keyValue = line;
		//System.out.println("keyvalue: " + keyValue);
		keyValue = keyValue.replaceAll("\\{", "");
		keyValue = keyValue.replaceAll("}", "");
//		keyValue = keyValue.replaceAll(", ", ",");
//		keyValue = keyValue.replaceAll(" ,", ",");
//		keyValue = keyValue.replaceAll("\\[", "");
//		keyValue = keyValue.replaceAll("]", "");
		
		List<String> keyValueList = Arrays.asList(keyValue.split(","));
		//create the head and lines
		//System.out.println(ArrayUtils.printListString(keyValueList));
		//System.out.println(keyValueList.size());
		//sb.append(ArrayUtils.printListString(keyValue));
		Iterator<String> values = keyValueList.iterator();
		
		//sample line
		//KEY: GRAMM WENDY L
		//['{from_poi_to_this_person=NaN, other=NaN, director_fees=119292, to_messages=NaN, total_stock_value=NaN, bonus=NaN, total_payments=119292, poi=false, salary=NaN, from_this_person_to_poi=NaN, loan_advances=NaN, deferred_income=NaN, email_address=NaN, shared_receipt_with_poi=NaN, restricted_stock=NaN, from_messages=NaN, long_term_incentive=NaN, deferral_payments=NaN, restricted_stock_deferred=NaN, exercised_stock_options=NaN, expenses=NaN}]
		while (values.hasNext()) {
			String extract = values.next();
			String[] extractList = extract.split("=");

		//	System.out.println("extract: " + extract);
		//	System.out.println(ArrayUtils.printArray(extractList));
		//	System.out.println(extractList[0]);
			list.add(extractList[indexToGet].trim());
			//header.add(extractList[0]);
			//lines.add(extractList[1]);
		}
		//sb.append("Header: " + ArrayUtils.printListString(header)+"\n");
		//sb.append("line: " + ArrayUtils.printListString(lines));
		
		
		//get all values for key
//		sb.append("val: " + map.get(val));
//		sb.append("\n");

//		Iterator loop = list.iterator();
//		while (loop.hasNext()){
//			System.out.print(loop.next().toString()+"-");
//		}
//		System.out.println();
//		System.out.println("listarray: " + ArrayUtils.printListString(list));
		String data = list.stream().map(Object::toString).collect(Collectors.joining(","));
	//	(Arrays.toString(ArrayUtils.extractKorV((String) map.get(key).toString(), 
	//			0, "Name")).replace("[", "").replace("]", "")
	//	return list.toArray(new String[0]);
		return data;
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


	public static String[] getWordsFromString(String sentence) {
		// http://docs.oracle.com/javase/6/docs/api/java/text/BreakIterator.html#getWordInstance%28%29
		ArrayList<String> words = new ArrayList<String>();
		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(sentence.substring(0, sentence.length()-4));
		int start = boundary.first();
		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
			//System.out.print(sentence.substring(start, end)+":");
			String word = sentence.substring(start, end);
			if (!word.matches(" ") && !word.matches("  ") && !word.isEmpty()) {
				words.add(word);
			}
			
		}
		String returnData[] = words.toArray(new String[words.size()]);
		//System.out.println(ArrayUtils.printArray(returnData));
		return returnData;
		
	}

	public static ArrayList<String> getWordsListFromString(String sentence) {
		// http://docs.oracle.com/javase/6/docs/api/java/text/BreakIterator.html#getWordInstance%28%29
		ArrayList<String> words = new ArrayList<String>();
		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(sentence.substring(0, sentence.length()-4));
		int start = boundary.first();
		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
			//System.out.print(sentence.substring(start, end)+":");
			String word = sentence.substring(start, end);
			if (!word.matches(" ") && !word.matches("  ") && !word.isEmpty()) {
				words.add(word);
			}
			
		}
		return words;
	}
	
	public static int getNumberOfCorrectMatches(ArrayList<String> predicted,
			ArrayList<String> testData) {
		int result = 0;
		
		if (predicted.size() == testData.size() ) {
			for (int index = 0; index < predicted.size() ; index++ ) {
				if (predicted.get(index).equals(testData.get(index))) {
					result++;
				}
			}
		} else {
			result = -1;
		}
		return result;
	}
	
	public static int getNumberOfCorrectMatches(double[] predicted,
			double[] testData) {
		int result = 0;
		
		if (predicted.length == testData.length ) {
			for (int index = 0; index < predicted.length ; index++ ) {
				if (predicted[index] == testData[index]) {
					result++;
				}
			}
		} else {
			result = -1;
		}
		return result;
	}
	
	public static double getAccuracyOfLabels(ArrayList<String> predicted,
			ArrayList<String> testData) {
		double results = 0.0;
		
		int countMatch = ArrayUtils.getNumberOfCorrectMatches(predicted, testData);
		
		if (countMatch >= 0) {
			results = countMatch / (double) predicted.size();
		} else {
			results = -1;
		}
		return results;
	}

	public static double getAccuracyOfLabels(double[] predicted,
			double[] testData) {
		double results = 0.0;
		
		int countMatch = ArrayUtils.getNumberOfCorrectMatches(predicted, testData);
		
		if (countMatch >= 0) {
			results = countMatch / (double) predicted.length;
		} else {
			results = -1;
		}
		return results;
	}
	
	public static float[] convertListToFloatArray(List<Float> list) {
		int index = 0; 
		float[] floatArray = new float[list.size()];
		for (float val : list){
			floatArray[index] = val;
			index++;
		}
	return floatArray;

	}
	
	public static double[] convertFloatToDoubleArray(float[] floatArray) {
		double[] newArray = new double[floatArray.length];
		for (int index = 0; index < floatArray.length; index++ ){
			newArray[index] = (double)floatArray[index];
		}
		
		return newArray;
	}
	
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
	
}
