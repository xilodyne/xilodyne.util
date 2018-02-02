package xilodyne.util.jnumpy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//input x = list of ordered values, size = xx
//input y = list of order value, size = yy
//return xv = array[x,yy] = x list, added yy times
//return yv = array[xx,y] = y list divided by yy, each x array equal one value from y array
/*
     anx, any = (5, 10)
    ax = np.linspace(0, 1, anx)
    ay = np.linspace(0, 1, any)
    axv, ayv = np.meshgrid(ax, ay)
    print 'axv {}'.format(axv)
    print 'ayv {}'.format(ayv)
  axv [[ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]
 [ 0.    0.25  0.5   0.75  1.  ]]
ayv [[ 0.          0.          0.          0.          0.        ]
 [ 0.11111111  0.11111111  0.11111111  0.11111111  0.11111111]
 [ 0.22222222  0.22222222  0.22222222  0.22222222  0.22222222]
 [ 0.33333333  0.33333333  0.33333333  0.33333333  0.33333333]
 [ 0.44444444  0.44444444  0.44444444  0.44444444  0.44444444]
 [ 0.55555556  0.55555556  0.55555556  0.55555556  0.55555556]
 [ 0.66666667  0.66666667  0.66666667  0.66666667  0.66666667]
 [ 0.77777778  0.77777778  0.77777778  0.77777778  0.77777778]
 [ 0.88888889  0.88888889  0.88888889  0.88888889  0.88888889]
 [ 1.          1.          1.          1.          1.        ]]
 
 */
/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class Meshgrid {

	
	List<List<Double>> xList = new ArrayList<List<Double>>();
	List<List<Double>> yList = new ArrayList<List<Double>>();
	
	int xsize = 0;
	int ysize = 0;
	
	public Meshgrid(List<Double> ldX, List<Double> ldY) {
		xsize = ldX.size();
		ysize =ldY.size();
		this.doMesh(ldX, ldY);
	}
	
	private void doMesh(List<Double> ldX, List<Double> ldY) {
	
		for (int a = 0; a < ysize; a++) {
		xList.add(ldX);
		}
		
		Iterator<Double> loop = ldY.iterator();

		while (loop.hasNext()) {
			List<Double> tempList = new ArrayList<Double>();
			double entryValue = loop.next();
		for (int a = 0; a < xsize; a++) {  //create row of length xsize but only one y value
			tempList.add(entryValue);
		}
			yList.add(tempList);
			
		}
		
	//	this.printLists();
	}
	
	public List<List<Double>> getXMeshGrid() {
		return this.xList;
	}
	
	public List<List<Double>> getYMeshgrid() {
		return this.yList;
	}
	
	public void printLists() {
		this.printRowList(xList);
		this.printRowList(yList);
	}
	/*
	public void printListsS() {
		Iterator<List<Double>> xListLoop = xList.iterator();
		System.out.print("[");
		while (xListLoop.hasNext()) {
			List<Double> xRow =  xListLoop.next();
			System.out.print("[");
			
			Iterator<Double> xRowLoop = xRow.iterator();
			while (xRowLoop.hasNext()) {
				System.out.print("\t" + xRowLoop.next());
			}
			if (xListLoop.hasNext()){
			System.out.println("\t]");
			}
			else {
				System.out.println("\t]]");
			}	
		}
	}
	*/
	
	private void printRowList(List<List<Double>> dList) {
		Iterator<List<Double>> dListLoop = dList.iterator();
		System.out.print("[");
		while (dListLoop.hasNext()) {
			List<Double> dRow =  dListLoop.next();
			System.out.print("[");
			
			Iterator<Double> dRowLoop = dRow.iterator();
			while (dRowLoop.hasNext()) {
				System.out.print("\t" + dRowLoop.next());
			}
			if (dListLoop.hasNext()){
			System.out.println("\t]");
			}
			else {
				System.out.println("\t]]");
			}
			
		}
		

	}
}
