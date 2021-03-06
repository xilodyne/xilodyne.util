package xilodyne.util.weka_helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Vector;

import mikera.arrayz.NDArray;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.TextDirectoryLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import xilodyne.util.data.NDArrayUtils;


/**
 * Given a Weka TextDirectoryLoader directory structure, read in the data and
 * export to ARFF https://weka.wikispaces.com/Text+categorization+with+WEKA
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 * @version 0.2
 *
 */
public class WekaARFFUtils {

	/**
	 * @param directoryName
	 * @return Instances
	 */
	public static Instances runWekaTextDirectoryLoader(String directoryName) {
		TextDirectoryLoader loader = new TextDirectoryLoader();

		System.out.println("\nCreateARFF...");
		System.out.println("Reading in structure from " + directoryName);

		try {
			loader.setDirectory(new File(directoryName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Instances dataRaw = null;

		try {
			dataRaw = loader.getDataSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("dataRaw: " + dataRaw.toString());

		return dataRaw;

	}

	public static Instances convertStringToNumbers(Instances data) {
		StringToWordVector filter = new StringToWordVector();
		try {
			filter.setInputFormat(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Instances dataFiltered = null;

		try {
			dataFiltered = Filter.useFilter(data, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataFiltered;
	}

	public static void writeToARFF(String filename, Instances data) {

		File outFile = new File(filename);
		System.out.println("Creating file: " + outFile.getPath() + ", " + data.numClasses() + " classes, " + data.size()
				+ " data lines... ");
		BufferedWriter outARFF;
		try {
			outARFF = new BufferedWriter(new FileWriter(outFile));
			outARFF.write(data.toString());
			outARFF.newLine();
			outARFF.flush();
			outARFF.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done.");

	}
	
	public static void wekaWriteARFF(String filename, Instances data) {

		System.out.println("Creating file: " + filename + ", " + data.numClasses() + " classes, " + data.size()
				+ " data lines... ");

		 ArffSaver saver = new ArffSaver();
		 saver.setInstances(data);
		 //System.out.println("ARFF global info: " + saver.globalInfo());
		 //System.out.println("ARFF options: " + ArrayUtils.printArray(saver.getOptions()));
		 try {
			saver.setFile(new File(filename));
			//	 saver.setDestination(new File("./data/test.arff"));   // **not** necessary in 3.5.4 and later
			 saver.writeBatch();

		} catch (IOException e) {
			e.printStackTrace();
		}
			System.out.println("Done.");

	}
	
	/**
	 * Using Weka routines, read ARFF data and return an Instances
	 *
	 * @param filename the filename to read
	 * @return the instances containing the data
	 */
	public static Instances wekaReadARFF(String filename) {
		Instances data = null;
		 try {
			 DataSource source = new DataSource(filename);

			data = source.getDataSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 // setting class attribute if the data format does not provide this information
		 // For example, the XRFF format saves the class attribute information as well
		 if (data.classIndex() == -1)
		   data.setClassIndex(data.numAttributes() - 1);
		 return data;
	}

	
	public static void readARFFdata(NDArray ndArray, double[] labels, String Filename) {
		//read in the data, assuming that more data than size of array
		//not sure how WEKA formats predictions or if this format changes with different filters/classifiers
		
		File file = new File(Filename);
		BufferedReader arff;
		System.out.println("Found " + Filename +" exists " + file.exists());
		
		String strLine = "";
		try {
			arff = new BufferedReader(new FileReader(file));
			
			/*
			 		// create training subset
		for (int loop = 0; loop < StudentMain.split; loop++) {
			X_train.set(loop, 0, grade[loop]);
			X_train.set(loop, 1, bumpy[loop]);
			y_train[loop] = y[loop];
		}
*/
		int index = 0; 
			 
			//if line is blank or begins with % or @, then skip
			while ((strLine = arff.readLine()) != null) {
				System.out.println(strLine);
				if (strLine.startsWith("@") || strLine.isEmpty()) {
					//not used
				} else {
				StringTokenizer stVar = new StringTokenizer(strLine,",");
				ndArray.set(index,0,Double.parseDouble(stVar.nextToken()));
				ndArray.set(index,1,Double.parseDouble(stVar.nextToken()));
				
				if (stVar.nextToken().compareTo("fast") == 0 ) {
					labels[index] = 0.0;
				} else {
					labels[index] = 1.0;
				}
				
				index++;
				}
			}
			arff.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static Instances convertToTdidfVector(Instances wordData) {
		String[] options = new String[1];
	      options[0] = "-L"; 
	//      options[1] = "-R <First>";
//		String[] options = new String[]{"-L -R <1,2>"};
//		String[] options = new String[]{"-L"};
		

		StringToWordVector filter = new StringToWordVector();
		Instances newData = null;
		
		try {
			System.out.println("att indicies: " + filter.getAttributeIndices());
			System.out.println("att prefix: " + filter.getAttributeNamePrefix());

			filter.setOptions(options);
			filter.setInputFormat(wordData);
			newData = Filter.useFilter(wordData,  filter);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	//	System.out.println("filter: " + filter.toString());
	//	System.out.println("new data: " + newData);
		return newData;
	}
	
	/**
	 * Write the feature_data and the label_data in a directory structure
	 * to match:  https://weka.wikispaces.com/Text+categorization+with+WEKA
	 * 
	 * @param data
	 * @param labels
	 * @param newDirectoryName
	 */
	public static void writeToWeka_TextDirectoryLoader_format(String[] data, 
			int[] labels, Vector<String> labelNames, String newDirectoryName) {
		// open data and label pickel files.
		// assuming data are lines of strings
		// assuming labels are lines of integers
		// assuming label data is sequential
		// assuming label data aligns with data by line number
		

		boolean useLabelNames = false;
		if (labelNames != null) {
			int uniqueLabels = Arrays.stream(labels).distinct().toArray().length;

			if (labelNames.size() == uniqueLabels) {
				useLabelNames = true;
			} else {
				System.out.println("# of label names [" + labelNames.size() + "] not equal to unqiue label count of ["
						+ uniqueLabels +"]");
			}
		}
		     
		// System.out.println("passing in: " + ArrayUtils.print1DArray(data));
		// create the directory
		File dir = new File(newDirectoryName);
		dir.mkdir();
		System.out.println("\nPickleLoader...");
		System.out.println("Creating directory: " + dir.getPath());

		// get unique class names
		// create a folder for each class
		HashSet<Integer> uniqueInt = new HashSet<Integer>();
		// hashset only adds unique
		
		for (int i : labels) {
			if (!uniqueInt.contains(i)) {
				uniqueInt.add(i);
				String sLabelName = "label-" + i;
				if (useLabelNames) {
					sLabelName = labelNames.get(uniqueInt.size()-1);
				}
				//File subdir = new File(newDirectoryName + File.separator + "class" + i);
				File subdir = new File(newDirectoryName + File.separator + sLabelName);
				subdir.mkdir();
				
				System.out.println("Creating directory: " + subdir.getPath());
			}
		}

		// for each string, create file in appropriate class
		System.out.println("Creating " + data.length + " .txt files...");
		for (int index = 0; index < data.length; index++) {
			int i = labels[index];
			// System.out.println("writing... " + data[index]);

			String sLabelName = "label-" + i;
			//File file = new File(newDirectoryName + File.separator + "class" + i + File.separator + "text-" + index
			//		+ ".txt");
			if (useLabelNames) {
				sLabelName = labelNames.get(i);
			}
			
			File file = new File(newDirectoryName + File.separator + sLabelName + File.separator + "text-" + index
					+ ".txt");
			try {
				BufferedWriter textWriter = new BufferedWriter(new FileWriter(file));
				textWriter.write(data[index]);
				textWriter.newLine();
				textWriter.flush();
				textWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Done.");
	}

	//give ndarray and file name
	public static void writeNDArrayToARFF(NDArray ndArray, double[] labels, String Filename) {
		File file = new File(Filename);
		BufferedWriter arff;
		
		try {
			arff = new BufferedWriter(new FileWriter(file));
			
			makeHeader(arff);
			dumpData(arff, ndArray, labels);
			
			
			arff.flush();
			arff.close();

		} catch (IOException e) {
			System.err.println(e);
		}

	}
		private static void makeHeader(BufferedWriter arff) throws IOException {
			/* 
			 @RELATION iris

@ATTRIBUTE sepallength	REAL
@ATTRIBUTE sepalwidth 	REAL
@ATTRIBUTE petallength 	REAL
@ATTRIBUTE petalwidth	REAL
@ATTRIBUTE class 	{Iris-setosa,Iris-versicolor,Iris-virginica}
			 */
			
			arff.append("@RELATION terrain");
			arff.newLine();
			arff.newLine();
			arff.append("@ATTRIBUTE bumpy REAL");
			arff.newLine();
			arff.append("@ATTRIBUTE grade REAL");
			arff.newLine();
			
			arff.append("@ATTRIBUTE class {fast, slow}");
			arff.newLine();
		}

	private static void dumpData(BufferedWriter arff, NDArray ndArray, double[] labels)
	throws IOException {

		arff.append("@DATA");
		arff.newLine();
		
		double[][] data = new double[2][labels.length];
		data = NDArrayUtils.dump2D_NDArray(ndArray);
		
		for (int index = 0; index < labels.length; index++) {
			arff.append(String.valueOf(data[0][index]));
			arff.append(",");
			arff.append(String.valueOf(data[1][index]));
			arff.append(",");
			
			if (labels[index] == 0 ) {
				arff.append("fast");
			} else {
				arff.append("slow");
			}
			arff.newLine();
		}
	}
}
