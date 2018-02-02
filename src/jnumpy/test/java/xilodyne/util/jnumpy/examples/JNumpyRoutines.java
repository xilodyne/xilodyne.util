package xilodyne.util.jnumpy.examples;


import xilodyne.util.jnumpy.ARange;
import xilodyne.util.jnumpy.Linspace;
import xilodyne.util.jnumpy.Meshgrid;

public class JNumpyRoutines {

	public static void main(String[] args) {
		
/*		ARange arange = new ARange();
		arange.generateList(0.0, 20, 1);
		arange.generateList(0.0, 9.5, .5);
		arange.generateList(0.0, 20, 1.6, 1);
		arange.generateList(1.0, 18.0, .2, 2);
		arange.generateList(0.0, 1.0, .01, 2);  
*/		
		Linspace linspaceX = new Linspace(0.0, 1.0,5);
		Linspace linspaceY = new Linspace(0.0, 1.0,10);
/*		linspace.generateList(0.0, 1.0);
		linspace.generateList(0.0, 1.0,3);
		linspace.generateList(0.0, 1.0,2); */
		
		Meshgrid meshgridLin = new Meshgrid(linspaceX.getList(), linspaceY.getList());
		meshgridLin.printLists();

		ARange arangeX = new ARange(0.0, 1.0, .01);
		
		Meshgrid Ar_meshgrid = new Meshgrid(arangeX.getList(),arangeX.getList());
		Ar_meshgrid.printLists();
	}

}
