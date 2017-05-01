package xilodyne.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Given max value, create random set of unique ints
 * starting with index = 0
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.2
 * 
 */
public class GetUniqueRandomRange implements Iterator<Integer> {
	private static Logger log = new Logger();

    private ArrayList<Integer> list = new ArrayList<Integer>();
    private int maxSize = 0;
	private int currentIndex = 0;
    
	public GetUniqueRandomRange(int iMaxSize) {
		log.logln_withClassName(G.LOG_INFO,"");

		this.maxSize = iMaxSize;
		log.logln(G.lD, "max size: " + this.maxSize);
		this.genRandomList();
		log.logln(G.lI,  "Random list size: " + list.size());
		log.logln(G.lD, "list: " + ArrayUtils.printListInt(list));
	}
	//http://stackoverflow.com/questions/8115722/generating-unique-random-numbers-in-java
	//author:  Andrew Thompson 
	private void genRandomList() {
        for (int i=0; i<maxSize; i++) {
            list.add(new Integer(i));
        }
         Collections.shuffle(list);
	}
	
	@Override
	public boolean hasNext() {
		boolean hasNext = false;
		if (this.currentIndex < this.maxSize) {
			hasNext = true;
		}
		log.logln(G.lD, "currentIndex: " + this.currentIndex +":"+ this.maxSize +"\thasNext: " + hasNext);
		return hasNext;
	}
	
	@Override
	public Integer next() {
		Integer val = list.get(this.currentIndex);
		log.logln(this.currentIndex +":" + String.valueOf(val));
		this.currentIndex++;
		return val;
	}
	
	
}
