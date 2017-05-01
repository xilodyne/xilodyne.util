package xilodyne.util.metrics;

import java.time.Duration;
import java.time.Instant;

public class TestResultsDataML {

	private boolean resultCalculated = false; // set to true when calculated

	private String classMLName = "";
	private String classMLNameLastVal = "";

	private int testingDataSize = 0;
	private int trainingDataSize = 0;
	private double accuracy = 0.0;
	private int fold = 0;

	private Instant startData = null;
	private Instant endData = null;
	private Instant startFit = null;
	private Instant endFit = null;
	private Instant startPredict = null;
	private Instant endPredict = null;

	private long dataTimeInMillis = -1;
	private long fitTimeInMillis = -1;
	private long predictTimeInMillis = -1;
	private long dataTimePercentOfTotal = -1;
	private long fitTimePercentOfTotal = -1;
	private long predictTimePercentOfTotal = -1;

	private double dataTimeInSec = 0.0;
	private double fitTimeInSec = 0.0;
	private double predictTimeInSec = 0.0;
	private double totalDurationInSec = 0.0;

	public void setStartData() {
		this.startData = Instant.now();
	}

	public void setEndData() {
		this.endData = Instant.now();
	}

	public void setStartFit() {
		this.startFit = Instant.now();
	}

	public void setEndFit() {
		this.endFit = Instant.now();
	}

	public void setStartPredict() {
		this.startPredict = Instant.now();
	}

	public void setEndPredict() {
		this.endPredict = Instant.now();
	}

	public long getDataTimeInMillis() {
		this.checkCalcTotal();
		return this.dataTimeInMillis;
	}

	public long getFitTimeInMillis() {
		this.checkCalcTotal();
		return this.fitTimeInMillis;
	}

	public long getPredictTimeInMillis() {
		this.checkCalcTotal();
		return this.predictTimeInMillis;
	}

	public double getDataTimeInSec() {
		this.checkCalcTotal();
		return this.dataTimeInSec;
	}

	public double getFitTimeInSec() {
		this.checkCalcTotal();
		return this.fitTimeInSec;
	}

	public double getPredictTimeInSec() {
		this.checkCalcTotal();
		return this.predictTimeInSec;
	}

	public double getTotalDurationInSec() {
		this.checkCalcTotal();
		return this.totalDurationInSec;
	}

	public long getDataTimeAsPercentageOfTotal() {
		this.checkCalcTotal();
		return this.dataTimePercentOfTotal;
	}

	public long getFitTimeAsPercentageOfTotal() {
		this.checkCalcTotal();
		return this.fitTimePercentOfTotal;
	}

	public long getPredictTimeAsPercentageOfTotal() {
		this.checkCalcTotal();
		return this.predictTimePercentOfTotal;
	}

	public void setTrainingDataSize(int size) {
		this.trainingDataSize = size;
	}

	public void setTestingDataSize(int size) {
		this.testingDataSize = size;
	}

	public void setAccuracy(double acc) {
		this.accuracy = acc;
	}

	public int getTrainingDataSize() {
		return this.trainingDataSize;
	}

	public int getTestingDataSize() {
		return this.testingDataSize;
	}

	public double getAccuracy() {
		return this.accuracy;
	}

	public void setClassMLName(String name) {
		this.classMLName = name;
		this.classMLNameLastVal = name.substring(name.lastIndexOf(".") + 1);
	}

	public String getClassMLName() {
		return this.classMLName;
	}

	public String getClassMLNameWithoutDomain() {
		return this.classMLNameLastVal;
	}

	public void setFoldSize(int size) {
		this.fold = size;
	}

	public int getFoldSize() {
		return this.fold;
	}

	private void checkCalcTotal() {
		if (this.resultCalculated == false) {
			this.resultCalculated = true;
			this.checkDataValid();
			this.calcTotal();
		}
	}

	private void checkDataValid() {
		if (this.startData == null)
			this.outputError("start Data");
		if (this.endData == null)
			this.outputError("end Data");
		if (this.startFit == null)
			this.outputError("start Fit");
		if (this.endFit == null)
			this.outputError("end Fit");
		if (this.startPredict == null)
			this.outputError("start Predict");
		if (this.endPredict == null)
			this.outputError("end Predict");
		if (this.accuracy == 0.0)
			this.outputError("accuracy");
		if (this.trainingDataSize == 0)
			this.outputError("training size");
		if (this.testingDataSize == 0)
			this.outputError("testing size");

	}

	private void outputError(String val) {
		System.out.println("Missing value for : " + val);
	}

	private void calcTotal() {
		this.dataTimeInMillis = Duration.between(startData, endData).toMillis();
		try {
			this.fitTimeInMillis = Duration.between(startFit, endFit).toMillis();
			this.fitTimeInSec = this.fitTimeInMillis / (double) 1000;
		} catch (NullPointerException e) {
			System.out.println("*** Fit time data is empty, setting to zero. ***");
			this.fitTimeInSec = 0;
		}

		this.predictTimeInMillis = Duration.between(startPredict, endPredict).toMillis();

		this.dataTimeInSec = this.dataTimeInMillis / (double) 1000;
		this.predictTimeInSec = this.predictTimeInMillis / (double) 1000;

		this.totalDurationInSec = dataTimeInSec + fitTimeInSec + predictTimeInSec;
		this.dataTimePercentOfTotal = Math.round((dataTimeInSec / totalDurationInSec) * 100);
		this.fitTimePercentOfTotal = Math.round((fitTimeInSec / totalDurationInSec) * 100);
		this.predictTimePercentOfTotal = Math.round((predictTimeInSec / totalDurationInSec) * 100);
	}

}
