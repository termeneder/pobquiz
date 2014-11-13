package bram.pobquiz.analyse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class DistributionAnalysis implements Analysis {

	private static final int LEFT_PAD_DISTANCE = 50;
	
	public enum Group {ASKED, CORRECT, INCORRECT};
	
	private final Group group;
	
	public DistributionAnalysis(Group group) {
		this.group = group;
	}
	
	@Override
	public String analyse(QuestionList list) {
		String str = "Distribution Info ("+group+"): \n\n";
		List<Integer> data = getData(list);
		str += StringUtils.leftPad("Average: ", LEFT_PAD_DISTANCE) + " " + getAverage(data) + "\n";
		str += StringUtils.leftPad("Median: ", LEFT_PAD_DISTANCE) + " " + getMedian(data) + "\n";
		str += StringUtils.leftPad("Lowest: ", LEFT_PAD_DISTANCE) + " "  + getLowest(data) + "\n";
		str += StringUtils.leftPad("Amount of lowest: ", LEFT_PAD_DISTANCE) + " "  + getAmountOfLowest(data) + "\n";
		str += StringUtils.leftPad("Highest: ", LEFT_PAD_DISTANCE) + " "  + getHighest(data) + "\n";
		str += StringUtils.leftPad("Amount of highest: ", LEFT_PAD_DISTANCE) + " "  + getAmountOfHighest(data) + "\n";
		str += StringUtils.leftPad("Mode: ", LEFT_PAD_DISTANCE) + " "  + getMode(data) + "\n";
		str += StringUtils.leftPad("Amount of mode: ", LEFT_PAD_DISTANCE) + " "  + getAmountOfMode(data) + "\n";
		return str;
	}

	private int getLowest(List<Integer> data) {
		return data.get(0);
	}

	private int getHighest(List<Integer> data) {
		return data.get(data.size()-1);
	}
	
	private List<Integer> getData(QuestionList list) {
		List<Integer> data = new ArrayList<Integer>();
		for (QuestionStats stat : list) {
			switch (group) {
			case ASKED : 
				data.add(stat.getTimesTested());
				break;
			case CORRECT :
				data.add(stat.getTimesCorrect());
				break;
			case INCORRECT : 
				data.add(stat.getTimesTested() - stat.getTimesCorrect());
				break;
			}
		}
		Collections.sort(data);
		return data;
	}

	private double getAverage(List<Integer> data) {
		
		return (double)getSum(data)/(double)data.size();
	}
	
	private double getMedian(List<Integer> data) {
		int size = data.size();
		if (size%2==0) {
			return ((double)data.get(size/2 - 1) + (double)data.get(size/2))/2; 
		} else {
			return (double)data.get((size-1)/2);
		}
		
	}
	
	private int getSum(List<Integer> data) {
		int sum = 0;
		for (Integer i : data) {
			sum+=i;
		}
		return sum;
	}

	private int getAmountOfLowest(List<Integer> data) {
		return getAmountOf(data, getLowest(data));
	}

	private int getAmountOfHighest(List<Integer> data) {
		return getAmountOf(data, getHighest(data));
	}
	
	private int getAmountOfMode(List<Integer> data) {
		int sum = 0;
		for (int mode : getModeList(data)) {
			sum += getAmountOf(data, mode);
		}
		return sum;
	}
	
	private int getAmountOf(List<Integer> hayStack, int needle) {
		int amount = 0;
		for (int hay : hayStack) {
			if (hay == needle) {
				amount++;
			}
		}
		return amount;
	}
	
	private double getMode(List<Integer> data) {
		return getAverage(getModeList(data));
	}
	
	private List<Integer> getModeList(List<Integer> data) {
		List<Integer> allModes = new ArrayList<Integer>();
		int highest = -1;
		for (int possibleMode = getLowest(data) ; possibleMode < getHighest(data) ; possibleMode++) {
			int amount = getAmountOf(data, possibleMode);
			if (highest < amount) {
				allModes = new ArrayList<Integer>();
				allModes.add(possibleMode);
				highest = amount;
			} else if(highest == amount) {
				allModes.add(possibleMode);
			}
		}
		return allModes;
	}
}
