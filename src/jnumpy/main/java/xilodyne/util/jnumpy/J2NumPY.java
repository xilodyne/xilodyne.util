package xilodyne.util.jnumpy;

import mikera.arrayz.NDArray;

/**
 * Light weight implementation of *some* NumPY methods.
 * https://docs.scipy.org/doc/numpy/reference/index.html
 * 
 * @author Austin Davis Holiday, aholiday@xilodyne.com
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 * @version 0.1
 */
public class J2NumPY {
	
	public static double[] arange(double dStart, double dEnd, double dStep) {
//		this.initValuesBigDecimal(dStart, dEnd, dStep, this.getDecPlace(dStep));
		int size = determineSize(dStart, dEnd, dStep);
		double[] grid = new double[size];
		createList(size, dStart, dEnd, dStep, grid);
		return grid;
	}
	
	private static int determineSize(double dStart, double dEnd, double dStep) {
		int count = 0;
		for (double x = dStart; x <= (dEnd-dStep); x = x + dStep) {
			count++;
		}
		return count;
	}
	
	private static void createList(int size, double dStart, double dEnd, double dStep, double[] grid) {
			int iX = 0;
			for (double x = dStart; x <= (dEnd-dStep); x = x + dStep) {
				grid[iX] = x;
				iX++;
			}		
	}
	
	public static double[][] meshgrid_getXX(double[] x, double[] y) {
		double[][] returnXX = new double[x.length][y.length];
		for (int iY = 0; iY < y.length; iY++ ) {
			for (int iX = 0; iX < x.length; iX++) {
				returnXX[iX][iY] = x[iX];
			}
		}	
		return returnXX;
	}
	
	public static double[][] meshgrid_getYY(double[] x, double[] y ){
		double[][] returnYY = new double[x.length][y.length];
		for (int iY = 0; iY < y.length; iY++ ) {
			for (int iX = 0; iX < x.length; iX++) {
				returnYY[iX][iY] = y[iY];
			}
		}	
		return returnYY;
	}

	public static double[] ravel(double[][] array) {
		double[] return1D = new double[(array.length * array[0].length)];
		int count = 0;
		for (int y = 0; y < array[0].length; y++ ) {
			for (int x = 0; x < array.length; x++) {
				return1D[count] = array[x][y];
				count++;
			}
		}		
		return return1D;
	}
	
	public static NDArray _c(double[] x, double[] y) {
		NDArray array = NDArray.newArray(x.length, 2);
		for (int iX = 0; iX < x.length; iX++) {
			array.set(iX,0,x[iX]);
			array.set(iX,1,y[iX]);
		}
		
		return array;
	}
	
	public static double[][] shape1D_2_2D(double[] d1D, int dimx, int dimy){
		//assuming dimx * dimy = 1D.size
		double[][] new2D = new double[dimx][dimy];
		int x=0, y=0;
		for (double dvalue: d1D) {
			new2D[x][y] = dvalue;
			x++;
			if (x == dimx ) {
				x = 0;
				y++;
			}
		}
		
		return new2D;
	}
	
}
