package xilodyne.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logging output
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.1
 */

class LoggerOutput {
	
	private static SimpleDateFormat format_long = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static SimpleDateFormat format_abbr  = new SimpleDateFormat("hh:mm:ss.SSS");
	
	//AssignedLevel output when matches current Globals LOGLEVEL settings
	
	public static void log_printlnClassName(String className, String output) {
		System.out.println();
		System.out.print(LoggerOutput.getFormattedDate() +" ");
		System.out.println("["+className +"]");
	}
	
	public static void log_println (String methodName, String output) {
		if (G.getLoggerShowTimestamp()) {
		System.out.print(LoggerOutput.getFormattedDate()+" ");
		}
		System.out.println("["+ methodName +"] " + output);
	}
	
	public static void log_print(String methodName, String output) {
		if (G.getLoggerShowTimestamp()) {
		System.out.print(LoggerOutput.getFormattedDate()+" ");
		}
		System.out.print("["+ methodName +"] " + output);
	}
	public static void log_println_noTimestamp(String output) {
		System.out.println(output);
	}
	
	public static void log_print_noTimestamp(String output) {
		System.out.print(output);
	}
	
	//get formatted date
    private static String getFormattedDate() {
    	String value = "";
    	if (G.getLoggerTimestampAbbreviation()) {
    		value = format_abbr.format(new Date());
    	} else {
    		value = format_long.format(new Date());
    	}
    	return value;
    }
    

}
