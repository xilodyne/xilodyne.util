package xilodyne.util.jnumpy;

import java.util.ArrayList;
import java.util.List;

//http://stackoverflow.com/questions/6208878/java-version-of-matlabs-linspace
	
	//author:Mattias 

/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class Linspace2 {
	
	public static List<Double> linspace1(double start, double stop, int n)
	{
	   List<Double> result = new ArrayList<Double>();

	   double step = (stop-start)/(n-1);

	   for(int i = 0; i <= n-2; i++)
	   {
	       result.add(start + (i * step));
	   }
	   result.add(stop);

	   return result;
	}
	
	
	//author:  tman_elite 
	public static double[] linspace(double min, double max, int points) {  
	    double[] d = new double[points];  
	    for (int i = 0; i < points; i++){  
	        d[i] = min + i * (max - min) / (points - 1);  
	    }  
	    return d;  
	}  

}
