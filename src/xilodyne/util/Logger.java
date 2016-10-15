package xilodyne.util;

import java.util.logging.Level;

/**
 * Logging class allowing different levels of output. Logging levels are defined
 * in the global class {@link xilodyne.util.G}.
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.1
 *
 */

public class Logger {

	private String className;
	private String methodName;
	private Level currentLevelForThisLogOutput;

	public Logger() {
		this.className = this.getCallerClassName();
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
		if (G.getLoggerLevel() == G.LOG_OFF) {
			// do nothing
		}
		if (G.getLoggerLevel() == G.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE) {
				LoggerOutput.log_printlnClassName(this.className, output);
				LoggerOutput.log_println(this.methodName, output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE || this.currentLevelForThisLogOutput == G.LOG_INFO) {
				LoggerOutput.log_printlnClassName(this.className, output);
				LoggerOutput.log_println(this.methodName, output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_DEBUG) {
			LoggerOutput.log_printlnClassName(this.className, output);
			LoggerOutput.log_println(this.methodName, output);
		}
	}

	private void logLN_println(String output) {
		if (G.getLoggerLevel() == G.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE) {
				LoggerOutput.log_println(this.methodName, output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE || this.currentLevelForThisLogOutput == G.LOG_INFO) {
				LoggerOutput.log_println(this.methodName, output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_DEBUG) {
			LoggerOutput.log_println(this.methodName, output);
		}
	}

	private void logLN_print(String output) {
		if (G.getLoggerLevel() == G.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE) {
				LoggerOutput.log_print(this.methodName, output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE || this.currentLevelForThisLogOutput == G.LOG_INFO) {
				LoggerOutput.log_print(this.methodName, output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_DEBUG) {
			LoggerOutput.log_print(this.methodName, output);
		}
	}

	private void logLN_println_noTimestamp(String output) {
		if (G.getLoggerLevel() == G.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE) {
				LoggerOutput.log_println_noTimestamp(output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE || this.currentLevelForThisLogOutput == G.LOG_INFO) {
				LoggerOutput.log_println_noTimestamp(output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_DEBUG) {
			LoggerOutput.log_println_noTimestamp(output);
		}
	}

	private void log_print_noTimestamp(String output) {
		if (G.getLoggerLevel() == G.LOG_FINE) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE) {
				LoggerOutput.log_print_noTimestamp(output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_INFO) {
			if (this.currentLevelForThisLogOutput == G.LOG_FINE || this.currentLevelForThisLogOutput == G.LOG_INFO) {
				LoggerOutput.log_print_noTimestamp(output);
			}
		}
		if (G.getLoggerLevel() == G.LOG_DEBUG) {
			LoggerOutput.log_print_noTimestamp(output);
		}
	}

	// http://stackoverflow.com/questions/11306811/how-to-get-the-caller-class-in-java
	// author: Denys Séguret
	private String getCallerClassName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		String callerClassName = null;
		for (int i = 1; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			if (!ste.getClassName().equals(Logger.class.getName())
					&& ste.getClassName().indexOf("java.lang.Thread") != 0) {
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
		this.methodName = stackTraceElement.getMethodName();
	}

}
