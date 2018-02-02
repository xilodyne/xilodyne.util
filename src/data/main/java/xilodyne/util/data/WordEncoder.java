package xilodyne.util.data;

import java.util.TreeMap;


//given new word, add to map, get next integer id, add to map
//word will be trimmed and move to lower case
//assumes one word at a time
/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class WordEncoder {
	private int count = 1;
	private TreeMap<String, Integer> encoded = new TreeMap<String,Integer>();
	

	//load map
	
	public int getEncodeID(String wordToCheck) {
		int result = 0;
		//if word in map, get id
		//if not, add to map, get id
		String word = wordToCheck.trim().toLowerCase();
		if (encoded.containsKey(word)) {
			result = encoded.get(word); 
		} else {
			encoded.put(word, count);
			result = count;
			count++;
		}
		
		return result;
	}
}
