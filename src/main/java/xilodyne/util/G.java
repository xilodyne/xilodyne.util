package xilodyne.util;

import java.util.logging.Level;

/**
 * Global class used to access static variables by all other classes. 
 * Uses {@link java.util.logging.Level} definitions.
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.2 - Add global print var
 *
 */
public class G {

	/** Used for viewing debug statements */
	public static final Level LOG_DEBUG = Level.ALL;
	
	/** Used for following code */
	public static final Level LOG_INFO = Level.INFO;
	
	/** Used for high level messages */
	public static final Level LOG_FINE = Level.FINE;
	
	/** Turns off logging.  Default setting */
	public static final Level LOG_OFF = Level.OFF;
	
	/** Abbreviated LOG_DEBUG */
	public static final Level lD = Level.ALL;
	
	/** Abbreviated LOG_INFO */
	public static final Level lI = Level.INFO;
	
	/** Abbreviated LOG_FINE */
	public static final Level lF = Level.FINE;
	
	/** Abbreviated LOG_OFF */
	public static final Level lO = Level.OFF;
	
	/** Enable log to file */
	public static final boolean LOG_TO_FILE = true;
	public static final boolean LOG_TO_FILE_OFF = false;
	
	/** Updated variables */
	private static boolean SETTING_LOG_DATE_ABBREVIATED = true;
	private static boolean SETTING_LOG_SHOW_DATE = true;
	private static Level SETTING_LOGLEVEL = Level.OFF;
	private static boolean SETTING_LOG_TO_FILE = false;

	public static void setLoggerLevel(Level level) {
		G.SETTING_LOGLEVEL = level;
	}
	
	public static Level getLoggerLevel() {
		return G.SETTING_LOGLEVEL;
	}
	
	public static void setLogToFile(boolean logToFile) {
		G.SETTING_LOG_TO_FILE = logToFile;
	}
	
	public static boolean getLogToFile() {
		return G.SETTING_LOG_TO_FILE;
	}
	
	public static void setLoggerShowTimestamp(boolean value) {
		G.SETTING_LOG_SHOW_DATE = value;
	}
	public static boolean getLoggerShowTimestamp() {
		return G.SETTING_LOG_SHOW_DATE;
	}
	
	public static void setLoggerTimestampAbbreviation(boolean value) {
		G.SETTING_LOG_DATE_ABBREVIATED = value;
	}
	public static boolean getLoggerTimestampAbbreviation() {
		return G.SETTING_LOG_DATE_ABBREVIATED;
	}
}
