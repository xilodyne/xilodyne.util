package xilodyne.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.stream.Stream;

/**
 * Given text file, split into multiple files
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.1
 */
public class FileSplitter {

	public static String fileExtTXT = "txt";
	public static String fileExtCSV = "csv";

	private static Logger log = new Logger();

	// adds ".<number>.<extension> to original file name
	// divides original file into # of sub files,
	// remainder of lines from original file added to last file if
	// not equally divisible

	public static int createSubFiles(int desiredNumberOfFiles, String filePath, String fileName, String fileExt)
			throws IOException {

		int numOfLines = getLineCount(filePath, fileName);

		// initial code:
		// http://stackoverflow.com/questions/7152264/java-copy-strings-from-a-file-to-another-without-losing-the-newline-format
		// author Kl4wd
		String file = filePath + "/" + fileName;
		BufferedReader br = new BufferedReader(new FileReader(file));
		File StartingFile = new File(file);
		File EndingFile = null;
		int maxLinesPerFile = (int) numOfLines / desiredNumberOfFiles;

		log.logln_withClassName(G.LOG_INFO, "Total of " + numOfLines + " lines in file " + fileName);
		log.logln(desiredNumberOfFiles + " files of " + maxLinesPerFile + " lines, plus "
				+ Math.floorMod(maxLinesPerFile, desiredNumberOfFiles) + " in last file.");
		log.logln("File #\tLine #\tLine");

		String line;
		for (int fileCount = 0; fileCount < desiredNumberOfFiles; fileCount++) {

			EndingFile = new File(StartingFile.getAbsolutePath() + "." + (fileCount + 1) + "." + fileExt);
			PrintWriter pw = new PrintWriter(new FileWriter(EndingFile));
			for (int lineCount = (maxLinesPerFile * fileCount); lineCount < (maxLinesPerFile * (fileCount + 1)); lineCount++) {
				line = br.readLine();
				pw.println(line);
				log.logln(G.lD, (fileCount + 1) + ":\t" + (lineCount + 1) + ":\t" + line);

			}
			// in cases of mod != 0 read the remaining lines
			if (fileCount == desiredNumberOfFiles - 1) {
				int lineCount = maxLinesPerFile * (fileCount + 1);
				while ((line = br.readLine()) != null) {
					pw.println(line);

					log.logln(G.lD, (fileCount + 1) + ":\t" + (lineCount + 1) + ":\t" + line);

					lineCount++;
				}
			}

			pw.close();
		}
		br.close();
		return maxLinesPerFile;
	}

	public static String getNewFileName(String fileName, int fileNumber, String fileExt) {
		return fileName + "." + fileNumber + "." + fileExt;
	}

	public static int getLineCount(String filePath, String fileName) throws IOException {

		int numOfLines = 0;

		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		Stream<String> lines = Files.lines(path, Charset.defaultCharset());
		numOfLines = (int) lines.count();
		lines.close();
		return numOfLines;
	}

}
