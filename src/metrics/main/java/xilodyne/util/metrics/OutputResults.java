package xilodyne.util.metrics;

import xilodyne.util.logger.Logger;
import xilodyne.util.logger.LoggerCSV;

/**
 * @author Austin Davis Holiday (aholiday@xilodyne.com)
 * @version 0.4 - 1/30/2018 - reflect xilodyne util changes
 *
 */
public class OutputResults {
	public static void getMLStats(Logger log, LoggerCSV logCSV,
			TestResultsDataML resultsML) {
		
		if (log != null) {
		log.logln(Logger.lF, "Class tested: " + resultsML.getClassMLName());
		log.logln("Accuracy: " + resultsML.getAccuracy() + "%");
		log.logln("Total lines training: " + resultsML.getTrainingDataSize());
		log.logln("Total lines predicted: " + resultsML.getTestingDataSize());
		log.logln("Activity\tTime (in seconds)\t% of Total Duration");
		log.logln("--------\t-----------------\t-------------------");
		log.logln("Data setup\t" + resultsML.getDataTimeInSec() + "\t\t\t" + resultsML.getDataTimeAsPercentageOfTotal() + "%");
		log.logln("Fit\t\t" + resultsML.getFitTimeInSec() + "\t\t\t" + resultsML.getFitTimeAsPercentageOfTotal() + "%");
		log.logln("Predict\t" + resultsML.getPredictTimeInSec() + "\t\t\t" + resultsML.getPredictTimeAsPercentageOfTotal() + "%" );
		log.logln("Total Time\t" + resultsML.getTotalDurationInSec());
		} else {
			System.out.println("Class tested: " + resultsML.getClassMLName());
			System.out.println("Accuracy: " + resultsML.getAccuracy() + "%");
			System.out.println("Total lines training: " + resultsML.getTrainingDataSize());
			System.out.println("Total lines predicted: " + resultsML.getTestingDataSize());
			System.out.println("Activity\tTime (in seconds)\t% of Total Duration");
			System.out.println("--------\t-----------------\t-------------------");
			System.out.println("Data setup\t" + resultsML.getDataTimeInSec() + "\t\t\t" + resultsML.getDataTimeAsPercentageOfTotal() + "%");
			System.out.println("Fit\t\t" + resultsML.getFitTimeInSec() + "\t\t\t" + resultsML.getFitTimeAsPercentageOfTotal() + "%");
			System.out.println("Predict\t" + resultsML.getPredictTimeInSec() + "\t\t\t" + resultsML.getPredictTimeAsPercentageOfTotal() + "%" );
			System.out.println("Total Time\t" + resultsML.getTotalDurationInSec());
	
		}
		
		if (logCSV != null) {
		logCSV.log_CSV_Entry(String.valueOf(resultsML.getAccuracy()));
		logCSV.log_CSV_Entry(String.valueOf(resultsML.getFoldSize()));
		logCSV.log_CSV_Entry(String.valueOf(resultsML.getTrainingDataSize()));
		logCSV.log_CSV_Entry(String.valueOf(resultsML.getTestingDataSize()));
		logCSV.log_CSV_Entry(String.valueOf(resultsML.getDataTimeInSec()));
		logCSV.log_CSV_Entry(String.valueOf(resultsML.getFitTimeInSec()));
		logCSV.log_CSV_Entry(String.valueOf(resultsML.getPredictTimeInSec()));
		logCSV.log_CSV_Entry(String.valueOf(resultsML.getTotalDurationInSec()));
		}

		// double acc = (double)
		// ArrayUtils.getNumberOfCorrectMatches(predictResults,
		// labels)/predictResults.size();
		// System.out.println("Accuracy: " +
		// ArrayUtils.getAccuracyOfLabels(predictResults, labels));

	}
	
	//assuming arrays are same length
	//https://en.wikipedia.org/wiki/Mean_absolute_error
	public static double getMeanAbsoluteError(double[] predicted, double[] observed) {
		double err, abserr, mea = 0;
		double predVal, obsVal, sumval = 0;
		System.out.println("Err\t\tAbs err\t\tArray 1\t\tArray2");
		for (int index = 0; index < predicted.length; index++){
			predVal = predicted[index];
			obsVal = observed[index];
			err = predVal - obsVal;
			abserr = Math.abs(err);
			System.out.println(err +"\t\t" + abserr+ "\t\t" + predVal +"\t\t" + obsVal);
			sumval = sumval + abserr;		
		}
		mea = sumval / predicted.length;
		System.out.println("MEA: " + mea);
		
		return mea;
	}
	
	public static double getRootMeanSquaredError(double[] predicted, double[] observed) {
		double result = 0;
		double errorVal = 0;
		double errorValSqr = 0;
		
		//log.logln(G.lD, "ypred\ty obs\terror val\terror val sqr\tsum");
		for (int index = 0; index < predicted.length; index++ ){
			errorVal = predicted[index] - observed[index];
			errorValSqr = Math.pow(errorVal,  2);
			
			result += errorValSqr;
			//log.logln(G.lD, predicted[index] +"\t" + observed[index] +"\t" +errorVal +"\t" + errorValSqr +"\t" + result);
			}
		result = Math.sqrt(result / predicted.length);
		//log.logln(G.lD, "RMSE: " + result);

		return result;
	}

	

}
