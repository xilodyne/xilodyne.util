package xilodyne.util.jnumpy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//returns a sequence of values, based upon on numpy.arange
//http://docs.scipy.org/doc/numpy/reference/generated/numpy.arange.html#numpy.arange
//start
//end
//step
//decimal places
//returns List<float>

//using Float for small memory footprint

//public class ARange implements List<Float> {
public class ARange {

	double start, end, step;
	int precision;
	MathContext mc;
	List<BigDecimal> listBigDecimal = new ArrayList<BigDecimal>();
	List<Double> list = new ArrayList<Double>();

	// if we need precision for output, create a BigDecimal List
	public ARange(double dStart, double dEnd, double dStep, int iPrecision) {
		this.initValuesBigDecimal(dStart, dEnd, dStep, iPrecision);
		this.createListBigDecimal();
		this.printListFormatted();
	}

	// default list
	public ARange(double dStart, double dEnd, double dStep) {
		this.initValuesBigDecimal(dStart, dEnd, dStep, this.getDecPlace(dStep));
		this.createList();
	//	this.printList();
	}

	/*
	private void initValues(double dStart, double dEnd, double dStep) {
		this.start = dStart;
		this.end = dEnd;
		this.step = dStep;
		list.clear();
	}
	*/

	private void initValuesBigDecimal(double dStart, double dEnd, double dStep,
			int iPrecision) {
		this.start = dStart;
		this.end = dEnd;
		this.step = dStep;
		this.precision = iPrecision;
		// this.precision = this.precision + 1;
		listBigDecimal.clear();
		mc = new MathContext(precision, RoundingMode.HALF_UP);
	}

	public List<BigDecimal> getListBigDecimal() {
		return listBigDecimal;
	}

	public List<Double> getList() {
		return this.list;
	}

	private void createList() {
		for (double x = this.start; x <= this.end; x = x + this.step) {
			this.list.add(x);

			// use case if STEP is bigger than END value, at least have END
			// value in list
			if (this.step > this.end) {
				this.list.add(this.end);
			}
		}
	}

	private void createListBigDecimal() {

		// System.out.println("Precision: " + precision);
		// System.out.println("mc: " + mc.getPrecision() + ":" +
		// mc.getRoundingMode());
		for (double x = this.start; x <= this.end; x = x + this.step) {
			// System.out.print(x +":" +new BigDecimal(x, this.mc) + ", ");
			this.listBigDecimal.add(new BigDecimal(x, this.mc));
			this.list.add(x);

			// list.add(roundToDecPlace(x, decPlaces));
			// list.add(x);

			// use case if STEP is bigger than END value, at least have END
			// value in list
			if (this.step > this.end) {
				this.listBigDecimal.add(new BigDecimal(this.end, this.mc));
				this.list.add(this.end);
			}

		}
		// System.out.println();
	}

	/*
	 * public static List<Float> fGenerate(Float start, Float end, Float step) {
	 * List<Float> list = new ArrayList<Float>(); list.clear();
	 * 
	 * System.out.println(); System.out.println("**************");
	 * System.out.println("Start:" + start + " end:" + end + " step:" + step);
	 * int decPlaces; // = getDecPlace(step);
	 * 
	 * // System.out.println("Step dec place:" + decPlaces);
	 * 
	 * for (float x = start; x<= end; x=x+step) { // System.out.print(x + ", ");
	 * // list.add(roundToDecPlace(x, decPlaces)); // list.add(x); }
	 * 
	 * // System.out.println("list size: " + list.size()); //use case if STEP is
	 * bigger than END value, at least have END value in list if
	 * (step.compareTo(end) > 0) { list.add(end); }
	 * 
	 * 
	 * 
	 * printList(list);
	 * 
	 * 
	 * 
	 * return list; }
	 */
	// determine the number of decimal places of the STEP value
	// only worry about to the left of zero
	private int getDecPlace(double dStep) {
		double value = dStep;
		double floor = Math.floor(value);

		// only calculate to the left of zero, convert to int rounds down)
		if (floor > 0) {
			// value = step - step.intValue();
			value = (double) (value - floor);
			System.out.println("value: " + value);
		}

		// get the number of decimal places
		// http://stackoverflow.com/questions/2296110/determine-number-of-decimal-place-using-bigdecimal
		// Lawrence Phillips
		return (int) Math.abs((Math.floor(Math.log(value) / Math.log(10))));

	}

	// float can return inexact values, round to nearest value
	// i.e 0.29999998 and not 0.03
	// increase base 10 per decimal place, round, then decrease by base 10 per
	// decimal place

	public static float roundToDecPlace(float value, int decPlace) {
		float newValue = value;

		if (decPlace == 0)
			return value;

		System.out.print("  newValue: " + newValue);

		// multiple by 10 for decimal
		for (int dec = 0; dec < decPlace; dec++)
			newValue = newValue * 10;
		System.out.print("  newValue + 10: " + newValue);

		// remove excess
		newValue = (float) Math.floor(newValue);
		System.out.print("  newValue floor: " + newValue);

		// reset to original dec point
		for (int dec = 0; dec < decPlace; dec++)
			newValue = newValue / 10;
		System.out.println("  newValue / 10: " + newValue);

		return newValue;

	}

	public void printList() {
		Iterator<Double> loop = list.iterator();
		System.out.print("[");
		int count = 0;
		while (loop.hasNext()) {
			count++;
			if (count == 12) {
				count = 0;
				System.out.println();
			}
			System.out.print("\t" + String.format("%.2f",loop.next()));
		}
		System.out.println("\t]");
	}

	public void printListFormatted() {
		String pattern = "#0";
		String output;

		switch (this.precision) {
		case 1:
			pattern = "#0.0";
			break;
		case 2:
			pattern = "#0.00";
			break;
		case 3:
			pattern = "#0.000";
			break;
		case 4:
			pattern = "#0.000";
		}

		DecimalFormat myFormatter = new DecimalFormat(pattern);

		Iterator<BigDecimal> loop = listBigDecimal.iterator();
		System.out.print("[");
		int count = 0;
		while (loop.hasNext()) {
			count++;
			if (count == 10) {
				count = 0;
				System.out.println();
			}
			output = myFormatter.format(loop.next());
			System.out.print("\t" + output);
		}
		System.out.println("]");
	}

}
