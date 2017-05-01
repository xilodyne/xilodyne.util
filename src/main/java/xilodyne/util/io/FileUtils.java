package xilodyne.util.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import xilodyne.util.ArrayUtils;

public class FileUtils {
	
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

	public static void writeToCSV(HashMap<String, Object> map,
			String filename){

		File outFile = new File(filename);
		System.out.println("Creating file: " + outFile.getPath());
		BufferedWriter outCSV;
		try {
			outCSV = new BufferedWriter(new FileWriter(outFile));
			
			//extract key values into lines
			Iterator<String> keys = map.keySet().iterator();
			
			//get creater header, assuming if key has value then at one line exists
			//List<String> header = new Vector<String>();

			boolean doHeader = true;
			
			while (keys.hasNext() ) {
				String key = keys.next();
	//			sb.append("KEY: " + key + "\n");
				
				if (doHeader) {
					//outCSV.write(Arrays.toString(ArrayUtils.extractKorV((String) map.get(key).toString(), 
					//		0, "Name")).replace("[", "").replace("]", "").);
					outCSV.write(ArrayUtils.extractKorV((String) map.get(key).toString(), 0, "Name"));
					outCSV.newLine();
					//outCSV.write(Arrays.toString(ArrayUtils.extractKorV((String) map.get(key).toString(), 
					//		1, key)).replace("[", "").replace("]", "").trim());
					outCSV.write(ArrayUtils.extractKorV((String) map.get(key).toString(),1, key));
					outCSV.newLine();
					doHeader = false;
				} else {
					//outCSV.write(Arrays.toString(ArrayUtils.extractKorV((String) map.get(key).toString(), 
					//		1, key)).replace("[", "").replace("]", "").trim());
					outCSV.write(ArrayUtils.extractKorV((String) map.get(key).toString(),1, key));
					outCSV.newLine();
				}
				
//				System.out.println("check: " + (Arrays.toString(ArrayUtils.extractKorV((String) map.get(key).toString(), 
//						1, key)).replace("[", "").replace("]", "")));
			}
				outCSV.flush();
				outCSV.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done.");
		}
	
	private static String cleanData(String[] line) {
		//for each value, remove unwanted characters
		StringBuffer cleaned = new StringBuffer();
		for (String s: line) {
			String temp = s.trim();
			cleaned.append(temp + ",");
		}
		
		return cleaned.toString();
	}
	
}
