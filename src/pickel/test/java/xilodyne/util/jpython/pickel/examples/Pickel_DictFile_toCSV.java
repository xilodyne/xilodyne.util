package xilodyne.util.jpython.pickel.examples;

import java.util.HashMap;
import java.util.logging.Logger;


import xilodyne.util.ArrayUtils;
import xilodyne.util.file.io.FileUtils;
import xilodyne.util.jpython.pickel.PickleLoader_SteveShipway;


/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class Pickel_DictFile_toCSV {

	
	//load the pickle file
	//write string to file, under class name folder
	   public static void main(String[] args) {
		   
		 //  String label_file = "data/PickleToArffExample/1.pickle/labels-email_authors.20sample.pkl";
		   String data_file = "data/PickleToArffExample/1.pickle/enron_salary(dict file)/enron_salary.pkl";
		   
		   String output_file_name = "data/PickleToArffExample/2.text/enron_salary.csv";

	     //   int[] labels = PickleLoader.getIntegerData(label_file);
		   
		   PickleLoader_SteveShipway pickle = new PickleLoader_SteveShipway();
		   final Logger log = Logger.getLogger(Pickel_DictFile_toCSV.class.getName());
		   
		   HashMap<String, Object> data = pickle.getDataFileStream(data_file, log);
	        //String[] data = PickleLoader.getStringData(data_file);
	        System.out.println("Hash map size: " + data.size());

	        System.out.println(ArrayUtils.printHashMap(data));
	        //write pickle data as text file
	        FileUtils.writeToCSV(data, output_file_name);
//	        PickleLoader.writeToWeka_TextDirectoryLoader_format(data, labels, labelNames, output_dir_name);
	    }

}
