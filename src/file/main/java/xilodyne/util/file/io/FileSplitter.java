package xilodyne.util.file.io;

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
import java.util.Iterator;
import java.util.stream.Stream;

import xilodyne.util.logger.Logger;
import xilodyne.util.math.GetUniqueRandomRange;

/**
 * Given text file, split into multiple files
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 */
public class FileSplitter {

	public static String fileExtTXT = "txt";
	public static String fileExtCSV = "csv";
	public static String fileExtARFF = "arff";
	public static String fileExtARFF_INFO = "arff_info";
	public static String fileExtARFF_DATA = "arff_data";
	
	public static boolean SHUFFLE = true;
	public static boolean NO_SHUFFLE = false;
	public static boolean HEADER = true;
	public static boolean HEADER_NONE = false;

	private static Logger log = new Logger("fSplt");

	public static int createSubARFF_Shuffle(int desiredNumberOfFiles, String filePath, String fileName,
			String fileExt, boolean randomize) {

		int maxLinesPerFile = 0;
		// split ARFF into info & data,
		// call randomize routine on data
		try {
			FileSplitter.splitARFFIntoInfoData(desiredNumberOfFiles, filePath, fileName, fileExt);
			maxLinesPerFile = FileSplitter.createSubFilesShuffle(desiredNumberOfFiles, filePath, fileName + "."
					+ FileSplitter.fileExtARFF_DATA, FileSplitter.fileExtARFF_DATA, 
					randomize, FileSplitter.HEADER_NONE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return maxLinesPerFile;
	}

	// adds ".<number>.<extension> to original file name
	// divides original file into # of sub files,
	// remainder of lines from original file added to last file if
	// not equally divisible

	

	/*
	 * http://stackoverflow.com/questions/2312756/how-to-read-a-specific-line-using-the-specific-line-number-from-a-file-in-java
	 * author:  aioobe 
	 * 
For small files:
	 String line32 = Files.readAllLines(Paths.get("file.txt")).get(32)
For large files:
try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
    line32 = lines.skip(31).findFirst().get();
}
	 */
	public static int createSubFilesShuffle(int desiredNumberOfFiles, String filePath, String fileName, 
			String fileExt, boolean randomize, boolean removeHeader)
			throws IOException {
		int maxLinesPerFile = 0;
		
		//if not need to be randomized, call normal file splitter
		if (randomize == FileSplitter.NO_SHUFFLE) {
			maxLinesPerFile = createSubFiles(desiredNumberOfFiles, 
					filePath, fileName, fileExt, removeHeader);
		} else {
			//get list of random numbers
			//read line, add to correct new file
			int numOfLines = FileUtils.getLineCount(filePath, fileName);
			
			//if header, subtract first line;
			numOfLines = numOfLines -1;
			
			Iterator<Integer> randomNumb = new GetUniqueRandomRange(numOfLines);
			String file = filePath + "/" + fileName;
			File inFile = new File(file);
			File outFile = null;
			maxLinesPerFile = (int) numOfLines / desiredNumberOfFiles;

			log.logln_withClassName(Logger.LOG_INFO, "Total of " + numOfLines + " lines in file " + fileName);
			log.logln(desiredNumberOfFiles + " files of " + maxLinesPerFile + " lines, plus "
			 + (numOfLines % desiredNumberOfFiles) + " in last file.");
			log.logln("File #\tLine #\tLine");

			String line;
			for (int fileCount = 0; fileCount < desiredNumberOfFiles; fileCount++) {

				outFile = new File(inFile.getAbsolutePath() + "." + (fileCount + 1) + "." + fileExt);
				PrintWriter pw = new PrintWriter(new FileWriter(outFile));
				
				int randNumber = 0;
				
				for (int lineCount = (maxLinesPerFile * fileCount); lineCount < (maxLinesPerFile * (fileCount + 1)); lineCount++) {
					randNumber = randomNumb.next();
					//remove the header
					if (randNumber == 0 && removeHeader) {
						randNumber = randomNumb.next();  //get the next number
					}
					line = FileUtils.getLineAtCount(file, randNumber);
					pw.println(line);
				}
				// in cases of mod != 0 read the remaining lines
				if (fileCount == desiredNumberOfFiles - 1) {
					//int lineCount = maxLinesPerFile * (fileCount + 1);
					while (randomNumb.hasNext() && (line = FileUtils.getLineAtCount(file, randomNumb.next())) != null) {
						pw.println(line);
						//lineCount++;
					}
				}
				pw.close();
			}
		}		
		return maxLinesPerFile;
	}
	
	public static int createSubFiles(int desiredNumberOfFiles, String filePath, String fileName, String fileExt, boolean removeHeader)
			throws IOException {

		int numOfLines = FileUtils.getLineCount(filePath, fileName);

		// initial code:
		// http://stackoverflow.com/questions/7152264/java-copy-strings-from-a-file-to-another-without-losing-the-newline-format
		// author Kl4wd
		String file = filePath + "/" + fileName;
		BufferedReader br = new BufferedReader(new FileReader(file));
		File inFile = new File(file);
		File outFile = null;
		int maxLinesPerFile = (int) numOfLines / desiredNumberOfFiles;

		log.logln_withClassName(Logger.LOG_INFO, "Total of " + numOfLines + " lines in file " + fileName);
		log.logln(desiredNumberOfFiles + " files of " + maxLinesPerFile + " lines, plus "
				+ Math.floorMod(maxLinesPerFile, desiredNumberOfFiles) + " in last file.");
		log.logln("File #\tLine #\tLine");

		String line;
		for (int fileCount = 0; fileCount < desiredNumberOfFiles; fileCount++) {

			outFile = new File(inFile.getAbsolutePath() + "." + (fileCount + 1) + "." + fileExt);
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			
			int calLineNumber = (maxLinesPerFile * fileCount);
			//remove the header 
			if ((calLineNumber == 0 ) && removeHeader) {
				br.readLine();
			}
			
			for (int lineNumber = calLineNumber; lineNumber < (maxLinesPerFile * (fileCount + 1)); lineNumber++) {
				line = br.readLine();
				pw.println(line);
				log.logln(Logger.lD, (fileCount + 1) + ":\t" + (lineNumber + 1) + ":\t" + line);
			}
			
			// in cases of mod != 0 read the remaining lines
			if (fileCount == desiredNumberOfFiles - 1) {
				int lineCount = maxLinesPerFile * (fileCount + 1);
				while ((line = br.readLine()) != null) {
					pw.println(line);

					log.logln(Logger.lD, (fileCount + 1) + ":\t" + (lineCount + 1) + ":\t" + line);

					lineCount++;
				}
			}

			pw.close();
		}
		br.close();
		return maxLinesPerFile;
	}
	
	// If ARFF file, put all non-data (@, %, ' ') in info file (file .info),
	// split up the rest of the data into other files
	private static void splitARFFIntoInfoData(int desiredNumberOfFiles, String filePath, String fileName, String fileExt)
			throws IOException {

		// first split ARFF into .info and .data files
		// .info = file #1
		// .data = file 2, 3...

		int iInfoLines = 0;
		int iDataLines = 0;
		String file = filePath + "/" + fileName;
		BufferedReader br = new BufferedReader(new FileReader(file));
		File FileSource = new File(file);
		File FileInfo = new File(FileSource.getAbsolutePath() + "." + FileSplitter.fileExtARFF_INFO);
		File FileData = new File(FileSource.getAbsolutePath() + "." + FileSplitter.fileExtARFF_DATA);

		log.logln_withClassName(Logger.lF, "Extract " + fileName + " to ." + FileSplitter.fileExtARFF_INFO + " and ."
				+ FileSplitter.fileExtARFF_DATA);

		String line;
		PrintWriter pwInfo = new PrintWriter(new FileWriter(FileInfo));
		PrintWriter pwData = new PrintWriter(new FileWriter(FileData));

		while ((line = br.readLine()) != null) {
			if (line.startsWith("%") || line.startsWith("@") || line.startsWith(" ")) {
				pwInfo.println(line);
				iInfoLines++;
			} else if (!line.isEmpty()) {
				pwData.println(line);
				iDataLines++;
			}
		}
		log.logln(Logger.lF, "Info file size: " + iInfoLines + ", Data file size: " + iDataLines);

		pwInfo.close();
		pwData.close();

		br.close();
	}

	// If ARFF file, put all non-data (@, %, ' ') in info file (file .info),
	// split up the rest of the data into other files
	public static int createSubFilesFromARFF(int desiredNumberOfFiles, String filePath, String fileName, String fileExt)
			throws IOException {

		// first split ARFF into .info and .data files
		// .info = file #1
		// .data = file 2, 3...

		int iInfoLines = 0;
		int iDataLines = 0;
		String file = filePath + "/" + fileName;
		BufferedReader br = new BufferedReader(new FileReader(file));
		File FileSource = new File(file);
		File FileInfo = new File(FileSource.getAbsolutePath() + "." + FileSplitter.fileExtARFF_INFO);
		File FileData = new File(FileSource.getAbsolutePath() + "." + FileSplitter.fileExtARFF_DATA);

		log.logln_withClassName(Logger.lF, "Extract " + fileName + " to ." + FileSplitter.fileExtARFF_INFO + " and ."
				+ FileSplitter.fileExtARFF_DATA);

		String line;
		PrintWriter pwInfo = new PrintWriter(new FileWriter(FileInfo));
		PrintWriter pwData = new PrintWriter(new FileWriter(FileData));

		while ((line = br.readLine()) != null) {
			if (line.startsWith("%") || line.startsWith("@") || line.startsWith(" ")) {
				pwInfo.println(line);
				iInfoLines++;
			} else if (!line.isEmpty()) {
				pwData.println(line);
				iDataLines++;
			}
		}
		log.logln(Logger.lF, "Info file size: " + iInfoLines + ", Data file size: " + iDataLines);

		pwInfo.close();
		pwData.close();

		br.close();
		// call creatSubFiles to split up the data file
		int maxLinesPerFile = FileSplitter.createSubFiles(desiredNumberOfFiles, filePath, 
				fileName + "." + FileSplitter.fileExtARFF_DATA, 
				FileSplitter.fileExtARFF_DATA, FileSplitter.HEADER_NONE);
		
		return maxLinesPerFile;
	}

	public static String getNewFileName(String fileName, int fileNumber, String fileExt) {
		String file = "";
		//if fileNumber = 0 then just return filename
		if (fileNumber > 0) {
			file = fileName + "." + fileNumber + "." + fileExt;
		} else {
			file = fileName;
		}
		
		return file;
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
