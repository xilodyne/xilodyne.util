package xilodyne.util.logger;

import java.io.Serializable;
import java.util.logging.Level;

import xilodyne.util.logger.io.LoggerOutput;

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

public class Logger implements Serializable {
	//If log output to file, then only log_fine is printed to console

	/**
	 * 
	 */
	private static final long serialVersionUID = -7126094568907533976L;
	private String className;
	private String methodName;
	private String classAbbreviation;
	private Level currentLevelForThisLogOutput;
	private boolean logFileLocationPrinted; //only print the first time

	/** Used for viewing debug statements */
	public static final Level LOG_DEBUG = Level.ALL;
	
	/** Used for following code */
	public static final Level LOG_INFO = Level.INFO;
	
	/** Used for high level messages */
	public static final Level LOG_FINE = Level.FINE;
	
	/** Turns off logginLogger.  Default setting */
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
		Logger.SETTING_LOGLEVEL = level;
	}
	
	public static Level getLoggerLevel() {
		return Logger.SETTING_LOGLEVEL;
	}
	
	public static void setLogToFile(boolean logToFile) {
		Logger.SETTING_LOG_TO_FILE = logToFile;
	}
	
	public static boolean getLogToFile() {
		return Logger.SETTING_LOG_TO_FILE;
	}
	
	public static void setLoggerShowTimestamp(boolean value) {
		Logger.SETTING_LOG_SHOW_DATE = value;
	}
	public static boolean getLoggerShowTimestamp() {
		return Logger.SETTING_LOG_SHOW_DATE;
	}
	
	public static void setLoggerTimestampAbbreviation(boolean value) {
		Logger.SETTING_LOG_DATE_ABBREVIATED = value;
	}
	public static boolean getLoggerTimestampAbbreviation() {
		return Logger.SETTING_LOG_DATE_ABBREVIATED;
	}
	
	public Logger(String sClassAbbreviation) {
		this.className = this.getCallerClassName();
		this.classAbbreviation = sClassAbbreviation;
		this.setLogFileLocationPrinted(false);
	}
	
	//set to true to print to file instead of console
	public Logger(String sClassAbbreviation, boolean logToFile) {
		this.className = this.getCallerClassName();
		this.classAbbreviation = sClassAbbreviation;
		Logger.setLogToFile(logToFile);
		if (Logger.getLogToFile() ) {
			LoggerOutput.openOutputFile();
			this.setLogFileLocationPrinted(true);
		}
	}
	
	public Logger(String sClassAbbreviation, String fPath , String fName) {
		this.className = this.getCallerClassName();
		this.classAbbreviation = sClassAbbreviation;
		Logger.setLogToFile(Logger.LOG_TO_FILE);
		
		LoggerOutput.openOutputFile(fPath, fName);
		this.setLogFileLocationPrinted(true);
	}

	private void setCurrentLevel(Level level) {
		this.currentLevelForThisLogOutput = level;
	}
	

	/**
	 * @param assignedLevel {@link xilodyne.util.G}
	 * @param output value to be printed
	 */
	public void logln_withClassName(Level assignedLevel, String output) {
		this.setCurrentLevel(assignedLevel);
		this.updateCallMethodName();
		this.logStart_class_output(output);
	}

	public void logln(Level assignedLevel, String output) {
		this.setCurrentLevel(assignedLevel);
		this.updateCallMethodName();
		this.logLN_println(output);
	}

	public void logln(String output) {
		this.updateCallMethodName();
		this.logLN_println(output);
	}

	public void log(Level assignedLevel, String output) {
		this.setCurrentLevel(assignedLevel);
		this.updateCallMethodName();
		this.logLN_print(output);
	}

	public void log(String output) {
		this.updateCallMethodName();
		this.logLN_print(output);
	}

	public void logln_noTimestamp(Level assignedLevel, String output) {
		this.setCurrentLevel(assignedLevel);
		this.logLN_println_noTimestamp(output);
	}

	public void logln_noTimestamp(String output) {
		this.logLN_println_noTimestamp(output);
	}

	public void log_noTimestamp(Level assignedLevel, String output) {
		this.setCurrentLevel(assignedLevel);
		this.log_print_noTimestamp(output);
	}

	public void log_noTimestamp(String output) {
		this.log_print_noTimestamp(output);
	}

	// global level is set
	// if level is off, show nothing
	// if level is fine, only show logs set to fine
	// if level is info, show logs set to fine and info
	// if level is all, show logs set to fine, info and all
	private void logStart_class_output(String output) {
		LoggerOutput.log_printlnClassName(this.currentLevelForThisLogOutput, this.className, Logger.getLoggerLevel().getName());
		LoggerOutput.log_println(this.currentLevelForThisLogOutput, this.methodName, output);
		
		if (Logger.getLoggerShowTimestamp() && this.getLogFileLocationPrinted()) {
			LoggerOutput.log_printlnLogFileLocation(Logger.lF);
			this.setLogFileLocationPrinted(false);
		}
		

	}
/*	private void logStart_class_output(String output) {
		if (Logger.getLoggerLevel() == Logger.LOG_OFF) {
			// do nothing
		}
		if (Logger.getLoggerLevel() == Logger.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE) {
				LoggerOutput.log_printlnClassName(this.className, Logger.getLoggerLevel().getName());
				LoggerOutput.log_println(this.methodName, output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE || this.currentLevelForThisLogOutput == Logger.LOG_INFO) {
				LoggerOutput.log_printlnClassName(this.className, Logger.getLoggerLevel().getName());
				LoggerOutput.log_println(this.methodName, output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_DEBUG) {
			LoggerOutput.log_printlnClassName(this.className, Logger.getLoggerLevel().getName());
			LoggerOutput.log_println(this.methodName, output);
		}
	}
*/
	private void logLN_println(String output) {
		LoggerOutput.log_println(this.currentLevelForThisLogOutput, this.methodName, output);
	}
	/*private void logLN_println(String output) {
		if (Logger.getLoggerLevel() == Logger.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE) {
				LoggerOutput.log_println(this.methodName, output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE || this.currentLevelForThisLogOutput == Logger.LOG_INFO) {
				LoggerOutput.log_println(this.methodName, output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_DEBUG) {
			LoggerOutput.log_println(this.methodName, output);
		}
	}
*/
	private void logLN_print(String output) {
		LoggerOutput.log_print(this.currentLevelForThisLogOutput, this.methodName, output);
	}
	/*private void logLN_print(String output) {
		if (Logger.getLoggerLevel() == Logger.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE) {
				LoggerOutput.log_print(this.methodName, output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE || this.currentLevelForThisLogOutput == Logger.LOG_INFO) {
				LoggerOutput.log_print(this.methodName, output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_DEBUG) {
			LoggerOutput.log_print(this.methodName, output);
		}
	}
*/
	private void logLN_println_noTimestamp(String output) {
		LoggerOutput.log_println_noTimestamp(this.currentLevelForThisLogOutput, output);
	}
/*	private void logLN_println_noTimestamp(String output) {
		if (Logger.getLoggerLevel() == Logger.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE) {
				LoggerOutput.log_println_noTimestamp(output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE || this.currentLevelForThisLogOutput == Logger.LOG_INFO) {
				LoggerOutput.log_println_noTimestamp(output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_DEBUG) {
			LoggerOutput.log_println_noTimestamp(output);
		}
	}
*/

	private void log_print_noTimestamp(String output) {
		LoggerOutput.log_print_noTimestamp(this.currentLevelForThisLogOutput, output);
	}
	/*private void log_print_noTimestamp(String output) {
		if (Logger.getLoggerLevel() == Logger.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE) {
				LoggerOutput.log_print_noTimestamp(output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == Logger.LOG_FINE || this.currentLevelForThisLogOutput == Logger.LOG_INFO) {
				LoggerOutput.log_print_noTimestamp(output);
			}
		}
		if (Logger.getLoggerLevel() == Logger.LOG_DEBUG) {
			LoggerOutput.log_print_noTimestamp(output);
		}
	}
*/
	// http://stackoverflow.com/questions/11306811/how-to-get-the-caller-class-in-java
	// author: Denys Séguret
	private String getCallerClassName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		String callerClassName = null;
		for (int i = 1; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			if (!ste.getClassName().equals(Logger.class.getName())
					&& ste.getClassName().indexOf("java.lanLogger.Thread") != 0) {
				if (callerClassName == null) {
					callerClassName = ste.getClassName();
					return ste.getClassName();
		//		} else if (!callerClassName.equals(ste.getClassName())) {
		//			return ste.getClassName();
				}
			}
		}
		return null;
	}

	// http://stackoverflow.com/questions/29519965/get-method-name-of-my-methods-caller
	// author: AR.3
	// author: Gio MV
	private void updateCallMethodName() {
		// String method_name =
		// Thread.currentThread().getStackTrace()[1].getMethodName());
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = stackTraceElements[3];
		// for (StackTraceElement ste : stackTraceElements) {
		// System.out.println(ste.getMethodName());
		// }
		// return stackTraceElement.getMethodName();
		this.methodName = this.classAbbreviation +":" +stackTraceElement.getMethodName();
	}
	
	private void setLogFileLocationPrinted(boolean value) {
		this.logFileLocationPrinted = value;
	}
	
	private boolean getLogFileLocationPrinted() {
		return this.logFileLocationPrinted;
	}

}
