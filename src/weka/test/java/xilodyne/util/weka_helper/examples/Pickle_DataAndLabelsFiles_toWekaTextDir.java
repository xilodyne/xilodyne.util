package xilodyne.util.weka_helper.examples;

import java.util.Vector;

import xilodyne.util.jpython.pickel.PickleLoader;
import xilodyne.util.weka_helper.WekaARFFUtils;

/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class Pickle_DataAndLabelsFiles_toWekaTextDir {
	
	//load the pickle file
	//write string to file, under class name folder
	   public static void main(String[] args) {
		   
		   String label_file = "data/PickleToArffExample/1.pickle/enron_emails(data and label files)/labels-email_authors.20sample.pkl";
		   String data_file = "data/PickleToArffExample/1.pickle/enron_emails(data and label files)/features-word_data.20sample.pkl";
		   
		   String output_dir_name = "data/PickleToArffExample/2.text/enron_20sample";

	        int[] labels = PickleLoader.getIntegerData(label_file);
	        String[] data = PickleLoader.getStringData(data_file);

	        Vector<String> labelNames = null;
	        
	        //if the label names are know, add them in the same order as they appear in the label file
	        labelNames = new Vector<String>();
	        labelNames.add("sara");
	        //labelNames.add("chris");
	        //write pickle data as text file
	        WekaARFFUtils.writeToWeka_TextDirectoryLoader_format(data, labels, labelNames, output_dir_name);
	    }
}
