package xilodyne.util;

import java.util.logging.Level;

/**
 * Global class used to access static variables by all other classes. 
 * Uses {@link java.util.logging.Level} definitions.
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.1
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
	
	/** Updated variables */
	private static boolean SETTING_LOG_DATE_ABBREVIATED = true;
	private static boolean SETTING_LOG_SHOW_DATE = true;
	private static Level SETTING_LOGLEVEL = Level.OFF;

	public static void setLoggerLevel(Level level) {
		G.SETTING_LOGLEVEL = level;
	}
	
	public static Level getLoggerLevel() {
		return G.SETTING_LOGLEVEL;
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
