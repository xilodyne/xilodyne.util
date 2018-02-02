package xilodyne.util.weka_helper.examples;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import xilodyne.util.weka_helper.WekaARFFUtils;

import java.io.File;

//from https://weka.wikispaces.com/Converting+CSV+to+ARFF

/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class CSV_ToARFF {

	public static void main(String[] args) throws Exception {

		String inputFile = "data/PickleToArffExample/2.text/enron_salary.csv";
		String outputFile = "data/PickleToArffExample/3.arff/enron_salary.arff";

		// load CSV
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(inputFile));
		
		loader.setStringAttributes("first");  //name is string
		
		Instances data = loader.getDataSet();
		data.setClassIndex(8);   //poi is what we are looking for

		// save ARFF
		WekaARFFUtils.wekaWriteARFF(outputFile,  data);
		     
	//	ArffSaver saver = new ArffSaver();
	//	saver.setInstances(data);
	//	saver.setFile(new File(outputFile));
	//	saver.writeBatch();
	}
}
