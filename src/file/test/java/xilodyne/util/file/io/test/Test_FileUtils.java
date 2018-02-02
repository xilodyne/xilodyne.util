package xilodyne.util.file.io.test;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


import xilodyne.util.file.io.FileUtils;
import xilodyne.util.logger.Logger;

public class Test_FileUtils {

	private Logger log = new Logger("fu");
	private String csvInFile = "data/FileUtils/train_data.csv";
	private int columnPropSizeSqFeet = 27;

	
	public Test_FileUtils() {
		// Logger.setLoggerLevel(Logger.LOG_OFF);
		Logger.setLoggerLevel(Logger.LOG_FINE);
		// Logger.setLoggerLevel(Logger.LOG_INFO);
		//Logger.setLoggerLevel(Logger.LOG_DEBUG);
	}
	
	@Test
	public void calcMean() throws IOException {
		log.logln_withClassName(Logger.lF,"");	
			
		//setup
		System.out.println();
		System.out.println("*** TEST *** Check Mean");
		System.out.println();

		double mean = FileUtils.getMeanOfCSVColumn(csvInFile, columnPropSizeSqFeet, FileUtils.HEADER);
		System.out.println ("Mean: " + mean);
		assertEquals(25636.758227848102, mean, 0);

	}

}
