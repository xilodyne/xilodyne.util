package xilodyne.util.metrics;

import java.time.Duration;

import xilodyne.util.G;
import xilodyne.util.Logger;
import xilodyne.util.LoggerCSV;

public class OutputResults {
	public static void getMLStats(Logger log, LoggerCSV logCSV,
			TestResultsDataML resultsML) {
		
		if (log != null) {
		log.logln(G.lF, "Class tested: " + resultsML.getClassMLName());
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

}
