package xilodyne.util.weka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import xilodyne.util.ArrayUtils;
import mikera.arrayz.NDArray;

public class WEKA_ARFF_Utils {
	
	
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
		data = ArrayUtils.dump2D_NDArray(ndArray);
		
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
