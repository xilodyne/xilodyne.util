package xilodyne.util.jpython.pickel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.python.core.PyException;
import org.python.core.PyFile;
import org.python.core.PyList;
import org.python.modules.cPickle;

/**
 * Load python *List* pickle files using the Jython standalone jar.
 * If looking to load a Dictionary pkl file, see:
 * https://github.com/sshipway/dynmap-mcdungeon/blob/master/src/org/steveshipway/dynmap/PickleLoader.java
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 * @version 0.2
 */


public class PickleLoader {

	
	/**
	 * Read in a pkl file of strings
	 * 
	 * @param filename
	 * @return
	 */
	public static String[] getStringData(String filename) {
		String[] data = null;

		File f = new File(filename);
		InputStream fs = null;
		try {
			fs = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			System.out.println("File <" + filename + "> not found");
			return null;
		}

		PyFile picklefile = new PyFile(fs);
		PyList listHash = null;
		try {
			listHash = (PyList) cPickle.load(picklefile);
		} catch (PyException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		data = new String[listHash.size()];
		for (int i = 0; i < listHash.size(); i++) {
			String sVal = listHash.get(i).toString();
			data[i] = sVal;
		}
		return data;
	}

	/**
	 * Read in a pkl file of integers
	 * 
	 * @param filename
	 * @return
	 */
	public static int[] getIntegerData(String filename) {
		int[] data = null;

		File f = new File(filename);
		InputStream fs = null;
		try {
			fs = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			System.out.println("File <" + filename + "> not found");
			return null;
		}

		PyFile picklefile = new PyFile(fs);
		PyList listHash = null;
		try {
			listHash = (PyList) cPickle.load(picklefile);
		} catch (PyException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		data = new int[listHash.size()];
		for (int i = 0; i < listHash.size(); i++) {
			int iVal = Integer.valueOf(listHash.get(i).toString());
			data[i] = iVal;
		}
		return data;
	}

}
