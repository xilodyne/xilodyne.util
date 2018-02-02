package xilodyne.util.jpython.pickel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import java.util.Vector;


/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class PythonPickleRead {
	//read the file first to get count
	/* sample files
	
	features:
				 (lp0
	S' sbaile2 nonprivilegedpst susan pleas send the forego list to richard thank   enron wholesal servic 1400 smith street eb3801a houston tx 77002 ph 713 8535620 fax 713 6463490'
	p1
	...
	aS'  pleas review and let discuss ss forward by  houect on 04192000 0438 pm edward sacksenron 04192000 0312 pm to susan flynnhouectect  houectect cc traci ngohouectect william s bradfordhouectect tanya rohauerhouectect subject re assign and amend of delamarva light power compani ena isda agreement to connectiv energi suppli inc pleas proceed with the assign referenc abov in term of the isda assign and amend everyth was satisfactori i do howev recommend if this hasnt alreadi been done that legal bless the guaranti languag by conectiv if you should have ani comment or question pleas contact me at x57712 rgds ed sack traci ngoect 04192000 1044 am to susan flynnhouectect cc  houectect edward sackscorpenronenron subject re assign and amend of delamarva light power compani ena isda agreement to connectiv energi suppli inc ed sack is final the review of this today thank for the follow up susan flynn 04192000 1042 am to traci ngohouectect cc  houectect subject assign and amend of delamarva light power compani ena isda agreement to connectiv energi suppli inc traci as we have discuss the cps attorney want to get this assign in place befor the end of april the cps attorney call almost daili to check the status of the assign i know you are busi but could pleas take a look at this document today thank'
	a.
	
	labels:
	(lp0
	I0
	aI0
	...
	aI1
	a
	
				 */
		
	/*
	 first line ignore
	 second line, first char:  I = integer + number
	 				S = string + '
	 				P = line number
	 				. = end of file
	 third line, label? first char,		
	 */
		

	public static double[] readPickleFileFeatures(String sFilename, String sFilepath) {
//		public static void readPickleFileFeatures(String sFilename, String sFilepath, NDArray features, double[] labels) {

		Vector<Integer> data = new Vector<Integer>();
		String filename = sFilepath + File.separator + sFilename;
		File file = new File(filename);
		
		BufferedReader reader;
		System.out.println("Found " + filename +" exists " + file.exists());
		
		String strLine = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			
			//int index = 0; 
			
			//skip the first line
			reader.readLine();
			
			//read the second line to determine type of data
			strLine = reader.readLine();
			
			if (strLine.startsWith("I")) {
				//labels[index] = getInt(strLine);
				data.add(getInt(strLine));
			} else if (strLine.startsWith("S")) {
				
			}
			
			//read the rest of the file
			while ((strLine = reader.readLine()) != null) {
				if (strLine.startsWith("I")) {
				//	labels[index] = getInt(strLine);
					data.add(getInt(strLine));
				} else if (strLine.startsWith("S")) {
					
				}
			}
			 
	//			ndArray.set(index,0,Double.parseDouble(stVar.nextToken()));
	//			ndArray.set(index,1,Double.parseDouble(stVar.nextToken()));
				
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		double[] temp = new double[data.size()];
		Iterator<Integer> loop = data.iterator();
		int i = 0;
		while (loop.hasNext()) {
			int number = loop.next();
			temp[i] = number;
			i++;
		}
		
		return temp;

	}
	
	//unicode: 0 = 48, 9=57, - = 45 (assuming negative is first char)
	//throw exception if no numbers read
	private static int getInt(String line) {
		//get numbers from line
		StringBuffer number = new StringBuffer();
		int unicode = 0;
		for (int index = 0 ; index < line.length(); index++ ) {
			unicode = line.codePointAt(index);
			if (( unicode > 47 && unicode < 58) || (unicode == 45) ) {
				number.append(line.charAt(index));
				System.out.println(line.charAt(index) +":" + unicode)  ;

			}
		}
		System.out.println("Result: " + number.toString());
		return Integer.valueOf(number.toString());		
	}
}
