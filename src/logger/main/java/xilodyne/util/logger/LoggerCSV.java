package xilodyne.util.logger;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import xilodyne.util.logger.io.LoggerOutputCSV;

/**
 * Logging class allowing different levels of output. Logging levels are defined
 * in the global class {@link xilodyne.util.G}.
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 * @version 0.2a - 6/1/2017 - Made Serializable, moved to own package
 * @version 0.2
 *
 */

public class LoggerCSV implements Serializable {
	//If log output to file, then only log_fine is printed to console


	/**
	 * 
	 */
	private static final long serialVersionUID = 1601178239231860004L;
	private SimpleDateFormat format_long = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	
	public LoggerCSV(String fPath , String fName) {
		String delimiter = ",";
		LoggerOutputCSV.openOutputFile(fPath, fName, delimiter, null);
	}

	
	public LoggerCSV(String fPath , String fName, String delimiter, String[] header) {
		LoggerOutputCSV.openOutputFile(fPath, fName, delimiter, header);
	}

	public void log_CSV_Timestamp() {
		LoggerOutputCSV.log_CSV(this.getDateID());
	}

	public void log_CSV_Entry(String output) {
		LoggerOutputCSV.log_CSV(output);
	}
	
	public void log_CSV_EOL() {
		LoggerOutputCSV.log_CSV_EOL();
	}
	
    private String getDateID() {
     	//String value = format_abbr.format(new Date());
     	String value = format_long.format(new Date());
     	value = value.replaceAll(" ", "_");
     	value = value.replaceAll("-",  "");
     	value = value.replaceAll(":", "");
     	value = value.substring(0, value.indexOf("."));
         	return value;
    }
}
