package xilodyne.util.jnumpy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class Linspace {
	double start, stop;
	int num;
	List<Double> list = new ArrayList<Double>();
	
	public Linspace(double dStart, double dEnd) {
		this.initValues(dStart, dEnd, 50);
		this.createList();
		this.printList();

	}
	
	public Linspace(double dStart, double dEnd, int iNum) {
		this.initValues(dStart, dEnd, iNum);
		this.createList();
		this.printList();

	}
	private void initValues(double dStart, double dEnd, int iNum ) {
		this.start = dStart;
		this.stop = dEnd;
		this.num = iNum;
		list.clear();
	}
	
	public List<Double> getList() {
		return list;
	}
	

	private void createList() {
		//http://stackoverflow.com/questions/6208878/java-version-of-matlabs-linspace
		//algorithm author:Mattias 
  
		   double step = (stop-start)/(num-1);

		   for(int i = 0; i <= num-2; i++)
		   {
		       list.add(start + (i * step));
		   }
		   list.add(stop);

		}  
	
	
	public void printList() {
		Iterator<Double> loop = list.iterator();
		System.out.print("[");
		int count = 0;
		while (loop.hasNext() ) {
			count++;
			if (count == 7) { 
				count = 0;
				System.out.println();
			}
			System.out.print("\t" +loop.next()  );
		}
		System.out.println("\t]");		
	}
	

}
