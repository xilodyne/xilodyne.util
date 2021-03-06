package xilodyne.util.logger.io;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;



/**
 * Logging output
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 * @version 0.2a - 6/1/2017 - Made Serializable, moved to own package
 * @version 0.2
 */

public class LoggerOutputCSV implements Serializable {
	/**
	 * 
	 */
	//if file already open, just add data
	//if file not opened or exists, add header
	//if no header, then skip writting header
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8461264062141532354L;
	private static File outputFile;
	private static PrintWriter file;
	private static boolean csvfileexists = false;
	private static String delimiter;
	
	public static void openOutputFile(String fPath, String fName, String sDelimiter, String[] header) {
		String fileName = fName;
		String filePath = fPath + "/";
		String fileExt = ".csv";
		LoggerOutputCSV.delimiter = sDelimiter;
		
		String sFile = filePath + fileName + fileExt;
		
		
		outputFile = new File(sFile);
		//does file exist?
		if (outputFile.exists() ) {
			csvfileexists = true;
		}

		try {
			
			file = new PrintWriter(new FileWriter(outputFile, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//if new csv file, open up and write header, if header given
		if (!csvfileexists) {
			csvfileexists = true;
			if ((header != null) && (header.length > 0)) {
				for (String s : header) {
				LoggerOutputCSV.log_CSV(s);
			}
				LoggerOutputCSV.log_CSV_EOL();
		}

	}
	}



	
	public static void log_CSV(String output) {
		
		LoggerOutputCSV.writeToLog(output);
		LoggerOutputCSV.writeToLog(LoggerOutputCSV.delimiter);
	
	}
	
	//end of line
	public static void log_CSV_EOL() {
		LoggerOutputCSV.writeToLog("\n");
	}
	
	
	

    private static void writeToLog(String line) {
		file.print(line);
		file.flush();

    }
    

}
