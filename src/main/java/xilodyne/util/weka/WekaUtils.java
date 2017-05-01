package xilodyne.util.weka;

import java.util.Enumeration;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import xilodyne.util.Logger;

public class WekaUtils {
	
	public static boolean ClassAtFront = true;
	public static boolean ClassAtEnd = false;
	
	public static void printInstancesLabels(Instances tempInstance, Logger log) {
		log.logln("# of Labels: " + tempInstance.numInstances());
		log.log("[");
		int lineBreak = 0;
		for (int index = 0; index < tempInstance.numInstances(); index++) {
				log.log_noTimestamp(String.format("%.1f", tempInstance.instance(index).classValue()) + "  ");
				lineBreak++;
				if (lineBreak > 13) {
					log.log_noTimestamp("\n");
					log.log("");
					lineBreak = 0;
				}
		}
		log.logln_noTimestamp("]");
	}
	
	public static void printInstancesLabelsAndData(Instances tempInstance, Logger log) {
		log.logln("class - 'data' (total size: " + tempInstance.numInstances() + ")");
		log.log("[");
		Enumeration<Instance> enumInstances = tempInstance.enumerateInstances();

		int lineBreak = 0;
		int labelIndex = 0;
		while (enumInstances.hasMoreElements()){
			log.log_noTimestamp(String.format("%.1f", tempInstance.instance(labelIndex).classValue()) + "  ");
			log.log_noTimestamp("-");
			Instance instance = enumInstances.nextElement();
			//assuming only one line of data
			log.log_noTimestamp(instance.toString().substring(0, instance.toString().indexOf(',')) + ",");
			lineBreak++;
			labelIndex++;
			if (lineBreak > 0) {
				log.log_noTimestamp("\n");
				log.log("");
				lineBreak = 0;
			}
		}
		log.logln_noTimestamp("]");
	}

	public static void printInstancesData(Instances tempInstance, Logger log) {
		log.logln("# of data lines: " + tempInstance.numInstances());
		log.log("[");
		Enumeration<Instance> enumInstances = tempInstance.enumerateInstances();

		int lineBreak = 0;
		while (enumInstances.hasMoreElements()){
			Instance instance = enumInstances.nextElement();
			//assuming only one line of data
			//	log.log_noTimestamp(instance.toString().substring(0, instance.toString().indexOf(',')) + ",");
				log.log_noTimestamp(instance.toString() + ",");
			lineBreak++;
			if (lineBreak > 0) {
				log.log_noTimestamp("\n");
				log.log("");
				lineBreak = 0;
			}
		}


		log.logln_noTimestamp("]");
	}

	public static double[] loadLabelsFromWekaData(Instances tempInstance, Logger log) {
		double[] labels = new double[tempInstance.numInstances()];
		for (int index = 0; index < labels.length; index++) {
			try {
				labels[index] = tempInstance.instance(index).classValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return labels;
	}

	//assumes that a weka data line = string
	public static String[] loadLinesFromWekaData(Instances tempInstance) {
		String[] data = new String[tempInstance.numInstances()];
		
		Enumeration<Instance> enumInstances = tempInstance.enumerateInstances();
		int count = 0;
		while (enumInstances.hasMoreElements()){
			Instance instance = enumInstances.nextElement();
			//load the string but remove ' at front and end
			data[count] = instance.toString().substring(1, instance.toString().length()-1);
			count++;
		}
		return data;
	}
	
	//assumes that a weka data line = string
	public static String[] loadStringFromWekaData(Instances tempInstance) {
		String[] data = new String[tempInstance.numInstances()];
		
		for (int index = 0; index < data.length; index++) {
			try {
				data[index] = tempInstance.instance(index).stringValue(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	
	
	
	//extract instances labels object from the classifier
	public static double[] getPredictedLabels(Instances data, Classifier cls) {
		int dataSize = data.numInstances();
		 double[] labels = new double[dataSize];
		for (int index = 0; index < dataSize; index++) {
			try {
				labels[index] = cls.classifyInstance(data.instance(index));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return labels;
	}
	
	public static String[] getLabelNames(Instances data) {
		String[] sResults = null;
		//assuming first instance has same # of classes as other instances
		Enumeration<Object> listOfAtt = data.firstInstance().classAttribute().enumerateValues();
		int listSize = data.firstInstance().classAttribute().numValues();
		sResults = new String[listSize];
		int iResultIndex = 0;
		while (listOfAtt.hasMoreElements()) {
			sResults[iResultIndex] = listOfAtt.nextElement().toString();
			iResultIndex++;
			}
	return sResults;
	}

	public static String[] getFeatureNames(Instances data) {
		String[] sResults = null;
		Enumeration<Attribute> listOfAtt = data.enumerateAttributes();
		int listSize = data.firstInstance().dataset().numAttributes();
		sResults = new String[listSize - 1];
		int iResultIndex = 0;
		while (listOfAtt.hasMoreElements()) {
			Attribute att = listOfAtt.nextElement();
			if (att != null) {
				sResults[iResultIndex] = att.name();
				iResultIndex++;
			}
		}
		return sResults;
	}


	//assuming classes/labels are defined at beginning or end attribute list
	//get the nominal type = 1
	public static String[] getLabelNamesOld(Instances data, boolean location) {
		String[] sResults = null;

		//Enumeration<Attribute> attributes = data.enumerateAttributes();
		int index = data.numAttributes() - 1;
		//location at beginning
		if (location == WekaUtils.ClassAtFront) { index = 0; }

			Attribute att = data.attribute(index);
			//int count = 1;
			Enumeration<Object> listOfAtt = att.enumerateValues();
			int listSize = att.numValues();
			sResults = new String[listSize];
			int iResultIndex = 0;
			while (listOfAtt.hasMoreElements()) {
				//avoid printing a lot of data
				sResults[iResultIndex] = listOfAtt.nextElement().toString(); 
				iResultIndex++;
				}
		return sResults;
	}

	//assuming class/labels are defined at beginning or end attribute list
	public static String[] getFeatureNames(Instances data, boolean location) {
		String[] sResults = null;

		//Enumeration<Attribute> attributes = data.enumerateAttributes();
		int index = data.numAttributes() - 1;
		//location at beginning
		if (location) { index = 0; }

		if (location == WekaUtils.ClassAtFront) {
			
		}
		
			Attribute att = data.attribute(index);
			//int count = 1;
			Enumeration<Object> listOfAtt = att.enumerateValues();
			int listSize = att.numValues();
			sResults = new String[listSize];
			int iResultIndex = 0;
			while (listOfAtt.hasMoreElements()) {
				//avoid printing a lot of data
				sResults[iResultIndex] = listOfAtt.nextElement().toString(); 
				iResultIndex++;
				}
			
		
				
		return sResults;
		
		
	}

	public static String getInstanceDetails(Instances data) {
		StringBuffer sbHold = new StringBuffer();
		sbHold.append("\n***********************************************");
		sbHold.append("\n**WekaUtils** Instance Details");
		//Enumeration<Attribute> attributes = data.enumerateAttributes();
		sbHold.append("\nData info:");
		sbHold.append("\n# of Attributes: " + data.numAttributes() + "\n");
		
		for (int index = 0; index < data.numAttributes(); index++ ){

			Attribute att = data.attribute(index);
			sbHold.append("\nindex: " + att.index());
			sbHold.append("\ttype: " + Attribute.typeToString(att.type()));
			sbHold.append("/" + att.type());
			sbHold.append("\tname: " + att.toString());
			int count = 1;
			Enumeration<Object> listOfAtt = att.enumerateValues();
			int listSize = att.numValues();
			sbHold.append("\n# of values: " + listSize + "\n");
			while (listOfAtt.hasMoreElements()) {
				//avoid printing a lot of data
				if (listSize > 10 ) {
					sbHold.append("\t1\tname: " + listOfAtt.nextElement().toString());
					sbHold.append("\t...");
					sbHold.append("\t"+listSize+"\tname: " + att.value(listSize-1).toString() + "\n");
					break;
					
				} else { 
					sbHold.append("\t"+count+"\tname: " + listOfAtt.nextElement().toString() + "\n");
				count++;
				}
			}
		}
				
		return sbHold.toString();
	}

	public static void printInstanceDetails(String dataSetName, Instances data, Logger log) {
		log.logln(" ");
		log.logln("*** Output '" + dataSetName + "' Attribute Info ****");
		log.logln("\t# of Attributes: " + data.numAttributes());

		for (int index = 0; index < data.numAttributes(); index++) {

			Attribute att = data.attribute(index);
			log.log("\tindex: " + att.index());
			log.log_noTimestamp("\ttype: " + Attribute.typeToString(att.type()));
			log.log_noTimestamp("/" + att.type());
			log.log_noTimestamp("\tname: " + att.toString());
			int count = 1;
			Enumeration<Object> listOfAtt = att.enumerateValues();
			int listSize = att.numValues();
			log.logln_noTimestamp("");
			log.logln("\t# of values: " + listSize);
			if (listSize > 0) {
				while (listOfAtt.hasMoreElements()) {
					// avoid printing a lot of data
					if (listSize > 10) {
						log.logln("\t1\tname: " + listOfAtt.nextElement().toString());
						log.logln("\t...");
						log.logln("\t" + listSize + "\tname: " + att.value(listSize - 1).toString());
						break;

					} else {
						log.logln("\t" + count + "\tname: " + listOfAtt.nextElement().toString());
						count++;
					}
				}
			}
		}
	}

	public static int getCountFromInstancesByClass(Instances data, double dClassIndex) {
		int count = 0;
		for (int index = 0; index < data.size(); index++) {
			if (data.instance(index).classValue() == dClassIndex)
				count++;
		}
		return count;
	}
	
	public static int getCountFromInstances(Instances data) {
		return data.size();
	}
}

