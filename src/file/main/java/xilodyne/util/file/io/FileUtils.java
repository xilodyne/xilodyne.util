package xilodyne.util.file.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import de.siegmar.fastcsv.reader.CsvParser;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import xilodyne.util.ArrayUtils;
import xilodyne.util.logger.Logger;

/**
 * The Class FileUtils.
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 * @version 0.2b - 6/5/2017 - add mean, standard deviation
 */
public class FileUtils {

	public static final boolean HEADER = true;
	public static final boolean NO_HEADER = false;

	private static Logger log = new Logger("FUtls");

	public FileUtils() {
		log.logln_withClassName(Logger.lF, "");
	}

	public static double getMeanOfCSVColumn(String csvFilename, int column, boolean hasHeader) throws IOException {
		double addedValues = 0;
		long numberOfValues = 0;
		int nullNumb = 0;
		int notANumb = 0;
		int emptyNumb = 0;
		int totalRead = 0;
		int updateConsole = 0;
		double tempValue = 0;
		String strValue = "";
	

		log.logln(Logger.lF, "Getting file info: " + csvFilename);
		int numberOfLines = FileUtils.getLineCount("", csvFilename);
		int divby10 = numberOfLines / 10;

		File file = new File(csvFilename);
		CsvReader csvReader = new CsvReader();

		CsvParser csvParser = csvReader.parse(file, StandardCharsets.UTF_8);

		CsvRow row;
		if (hasHeader) {
			// read the header
			row = csvParser.nextRow();
			List<String> fieldNames = row.getFields();
			log.logln(Logger.lF, "Calculating mean for column: <" + column + ">" + fieldNames.get(column) + ", File: "
					+ csvFilename);
		} else {
			log.logln(Logger.lF, "Calculating mean for column: <" + column + ">, File: " + csvFilename);

		}

		log.log_noTimestamp(Logger.lF, "0% ");
		for (int loop = 0; loop < 10; loop++) {
			log.log_noTimestamp(Logger.lF, " = ");
		}
		log.logln_noTimestamp(Logger.lF, " 100%");
		log.log_noTimestamp(Logger.lF, "   ");
		while ((row = csvParser.nextRow()) != null) {
			totalRead++;
			updateConsole++;
			if (updateConsole > (divby10 - 1)) {
				log.log_noTimestamp(Logger.lF, " . ");
				updateConsole = 0;
			}
			if (row.getField(column) != null) {
				strValue = row.getField(column).trim();
				if (strValue.isEmpty()) {
					emptyNumb++;
				} else {
					try {
						tempValue = Double.valueOf(strValue);
						//tempValue = Integer.valueOf(strValue);
						log.log_noTimestamp(Logger.lD, " v:" + tempValue + " ");
						addedValues += tempValue;
						numberOfValues++;
					} catch (NumberFormatException e) {
						notANumb++;
					}
				}
			} else {
				nullNumb++;
			}
		}

		csvParser.close();
		log.logln_noTimestamp(Logger.lF, "");
		log.logln("Total sum: " + addedValues + ", # lines added: " + numberOfValues);
		log.logln("Total # of lines: " + totalRead + ", # empty: " + emptyNumb +
				", # null: " + nullNumb + ", # not a number: " + notANumb);

		double mean = 0;
		try {
			mean = (double)(addedValues / numberOfValues);
		} catch (ArithmeticException e) {
			System.out.println("Error: no values found to calculate mean.");
			log.logln("Error: no values found to calculate mean.");
			mean = 0;
		}
		log.logln_noTimestamp(Logger.lF, "");
		log.logln("Mean: " + mean );

		return mean;

	}
	


	public static int getLineCount(String filePath, String fileName) throws IOException {

		int numOfLines = 0;

		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		Stream<String> lines = Files.lines(path, Charset.defaultCharset());
		numOfLines = (int) lines.count();
		lines.close();
		return numOfLines;
	}

	public static String getLineAtCount(String fileName, int lineCount) {
		Stream<String> lines = null;
		String line = null;
		try {
			lines = Files.lines(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		line = lines.skip(lineCount).findFirst().get();
		lines.close();
		return line;
	}

	public static void writeToCSV(HashMap<String, Object> map, String filename) {

		File outFile = new File(filename);
		System.out.println("Creating file: " + outFile.getPath());
		BufferedWriter outCSV;
		try {
			outCSV = new BufferedWriter(new FileWriter(outFile));

			// extract key values into lines
			Iterator<String> keys = map.keySet().iterator();

			// get creater header, assuming if key has value then at one line
			// exists
			// List<String> header = new Vector<String>();

			boolean doHeader = true;

			while (keys.hasNext()) {
				String key = keys.next();
				// sb.append("KEY: " + key + "\n");

				if (doHeader) {
					// outCSV.write(Arrays.toString(ArrayUtils.extractKorV((String)
					// map.get(key).toString(),
					// 0, "Name")).replace("[", "").replace("]", "").);
					outCSV.write(ArrayUtils.extractKorV((String) map.get(key).toString(), 0, "Name"));
					outCSV.newLine();
					// outCSV.write(Arrays.toString(ArrayUtils.extractKorV((String)
					// map.get(key).toString(),
					// 1, key)).replace("[", "").replace("]", "").trim());
					outCSV.write(ArrayUtils.extractKorV((String) map.get(key).toString(), 1, key));
					outCSV.newLine();
					doHeader = false;
				} else {
					// outCSV.write(Arrays.toString(ArrayUtils.extractKorV((String)
					// map.get(key).toString(),
					// 1, key)).replace("[", "").replace("]", "").trim());
					outCSV.write(ArrayUtils.extractKorV((String) map.get(key).toString(), 1, key));
					outCSV.newLine();
				}

				// System.out.println("check: " +
				// (Arrays.toString(ArrayUtils.extractKorV((String)
				// map.get(key).toString(),
				// 1, key)).replace("[", "").replace("]", "")));
			}
			outCSV.flush();
			outCSV.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done.");
	}

	@SuppressWarnings("unused")
	private static String cleanData(String[] line) {
		// for each value, remove unwanted characters
		StringBuffer cleaned = new StringBuffer();
		for (String s : line) {
			String temp = s.trim();
			cleaned.append(temp + ",");
		}

		return cleaned.toString();
	}

}
