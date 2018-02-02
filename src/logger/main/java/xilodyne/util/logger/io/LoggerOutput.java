package xilodyne.util.logger.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import xilodyne.util.logger.Logger;


/**
 * Logging output
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 * @version 0.2a - 6/1/2017 - Made Serializable, moved to own package
 * @version 0.2
 */

public class LoggerOutput implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6648364645738472431L;
	private static SimpleDateFormat format_long = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static SimpleDateFormat format_abbr  = new SimpleDateFormat("hh:mm:ss.SSS");
	
	private static File outputFile;
	private static PrintWriter file;
	
	public static void openOutputFile() {
		String fileName = "console-";
		String filePath = "./" ;
		String fileExt = ".log";
		String fileAppendix =  LoggerOutput.getDateID();
		
		String sFile = filePath + fileName + fileAppendix + fileExt;
		
		
		outputFile = new File(sFile);

		try {
			file = new PrintWriter(new FileWriter(outputFile, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void openOutputFile(String fPath, String fName) {
		String fileName = fName + "-";
		String filePath = fPath + "/";
		String fileExt = ".log";
		String fileAppendix =  LoggerOutput.getDateID();
		
		String sFile = filePath + fileName + fileAppendix + fileExt;
		
		
		outputFile = new File(sFile);

		try {
			file = new PrintWriter(new FileWriter(outputFile, true));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void log_printlnLogFileLocation(Level lineLogLevel) {
		StringBuffer sb = new StringBuffer();	
		sb.append(LoggerOutput.getFormattedDate() +" ");
		sb.append("Log file: " + LoggerOutput.outputFile.getAbsolutePath());
		sb.append("\n");
		
		LoggerOutput.outputLogData(lineLogLevel, sb);
	}
	
	//AssignedLevel output when matches current Globals LOGLEVEL settings
	//if Logger.LF, then show in display and print to log
	public static void log_printlnClassName(Level lineLogLevel, String className, String logLevel) {
		StringBuffer sb = new StringBuffer();		

		sb.append("\n");
		sb.append(LoggerOutput.getFormattedDate() +" ");
		sb.append("["+className +"]");
		sb.append(" [Log Level: " +logLevel + "]");
		sb.append("\n");
		
	
		LoggerOutput.outputLogData(lineLogLevel, sb);
		//System.out.println(sb.toString());
		//file.println(sb.toString());
		//file.flush();
	}
	
	public static void log_println (Level lineLogLevel, String methodName, String output) {
		StringBuffer sb = new StringBuffer();
		if (Logger.getLoggerShowTimestamp()) {
		//System.out.print(LoggerOutput.getFormattedDate()+" ");
			sb.append(LoggerOutput.getFormattedDate()+" ");
		}
		//System.out.println("["+ methodName +"] " + output);
		sb.append("["+ methodName +"] " + output);
		sb.append("\n");
		LoggerOutput.outputLogData(lineLogLevel, sb);
	}
	
	public static void log_print(Level lineLogLevel, String methodName, String output) {
		StringBuffer sb = new StringBuffer();
		
		if (Logger.getLoggerShowTimestamp()) {
		//System.out.print(LoggerOutput.getFormattedDate()+" ");
			sb.append(LoggerOutput.getFormattedDate()+" ");
		}
		//System.out.print("["+ methodName +"] " + output);
			sb.append("["+ methodName +"] " + output);
		
		LoggerOutput.outputLogData(lineLogLevel, sb);
	}
	
	public static void log_println_noTimestamp(Level lineLogLevel, String output) {
		StringBuffer sb = new StringBuffer();
		//System.out.println(output);
		sb.append(output);
		sb.append("\n");
		
		LoggerOutput.outputLogData(lineLogLevel, sb);
	}
	
	public static void log_print_noTimestamp(Level lineLogLevel, String output) {
		StringBuffer sb = new StringBuffer();
		//System.out.print(output);
		sb.append(output);
		
		LoggerOutput.outputLogData(lineLogLevel, sb);

	}
	
	//get formatted date
    private static String getFormattedDate() {
    	String value = "";
    	if (Logger.getLoggerTimestampAbbreviation()) {
    		value = format_abbr.format(new Date());
    	} else {
    		value = format_long.format(new Date());
    	}
    	return value;
    }
    
    private static String getDateID() {
     	//String value = format_abbr.format(new Date());
     	String value = format_long.format(new Date());
     	value = value.replaceAll(" ", "_");
     	value = value.replaceAll("-",  "");
     	value = value.replaceAll(":", "");
     	value = value.substring(0, value.indexOf("."));
         	return value;
    }

    private static void outputLogData(Level lineLogLevel, StringBuffer logData) {
		if (Logger.getLoggerLevel() == Logger.LOG_OFF) {
			// do nothing
		}
		if (Logger.getLoggerLevel() == Logger.LOG_FINE) {
			if (lineLogLevel == Logger.LOG_FINE) {
				outputChooseConsoleFile(lineLogLevel, logData);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_INFO) {
			if (lineLogLevel == Logger.LOG_FINE || lineLogLevel == Logger.LOG_INFO) {
				outputChooseConsoleFile(lineLogLevel, logData);			}
		} 
		if (Logger.getLoggerLevel() == Logger.LOG_DEBUG) {
	//	if (lineLogLevel == Logger.LOG_FINE || lineLogLevel == Logger.LOG_INFO || Logger.getLoggerLevel() == Logger.LOG_DEBUG) {
			outputChooseConsoleFile(lineLogLevel, logData);		}
	//	}
	}

    
	private static void outputChooseConsoleFile(Level lineLogLevel, StringBuffer logData) {
		if (Logger.getLogToFile() == Logger.LOG_TO_FILE) {
			LoggerOutput.outputToFile(logData.toString());
			
			//if local line is Level = fine, also print to console
			if (lineLogLevel == Logger.LOG_FINE) {
			LoggerOutput.outputToConsole(logData.toString());
			}
			
		} else {
			LoggerOutput.outputToConsole(logData.toString());
		}
	}

    private static void outputToConsole(String line) {
    	System.out.print(line);
    }
    private static void outputToFile(String line) {
		file.print(line);
		file.flush();

    }
    
}
