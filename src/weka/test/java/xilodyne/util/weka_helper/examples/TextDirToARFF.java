package xilodyne.util.weka_helper.examples;

import weka.core.Instances;
import xilodyne.util.weka_helper.WekaARFFUtils;

/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class TextDirToARFF {
	//load weka text file
	//conver td idf
	//write arff file
	   public static void main(String[] args) {
		   
		   String output_dir_name = "data/PickleToArffExample/2.text/enron_20sample";
		   String output_arff_asText = "data/PickleToArffExample/3.arff/enron_text_20sample.arff";
	        
	        Instances newData = WekaARFFUtils.runWekaTextDirectoryLoader(output_dir_name);
	        //WekaARFFUtils.writeToARFF(output_arff_asText,  newData);
	        WekaARFFUtils.wekaWriteARFF(output_arff_asText,  newData);
	        
	//        Instances dataFiltered = CreateARFF.convertStringToNumbers(newData);
	//        CreateARFF.writeToARFF(output_arff_asTdIDF,  dataFiltered);
	    }

	   


}
