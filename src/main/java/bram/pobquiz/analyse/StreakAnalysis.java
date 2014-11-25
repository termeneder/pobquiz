package bram.pobquiz.analyse;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class StreakAnalysis implements Analysis {

	private static final int LEFT_PAD_DISTANCE = 50;
	
	private final int c_goodStreak;
	
	public StreakAnalysis(int goodStreak) {
		c_goodStreak = goodStreak;
	}
	
	@Override
	public String analyse(QuestionList list) {
		String str = "Streak Info: \n\n";
		
		str += StringUtils.leftPad("Positive/negative streaks: ", LEFT_PAD_DISTANCE) + " " + getStreakAbove(list, 0) + "/" + getStreakBelow(list, 0) + "\n";
		str += StringUtils.leftPad("Streaks of " + c_goodStreak + " and above: ", LEFT_PAD_DISTANCE) + " " + getStreakAbove(list, c_goodStreak-1) + "\n";
		return str;
	}

	private int getStreakAbove(QuestionList list, int cutoff) {
		int amount = 0;
		for (QuestionStats stats : list) {
			if (stats.getStreak() > cutoff) {
				amount++;
			}
		}
		return amount;
	}
	
	private int getStreakBelow(QuestionList list, int cutoff) {
		int amount = 0;
		for (QuestionStats stats : list) {
			if (stats.getStreak() < cutoff) {
				amount++;
			}
		}
		return amount;
	}





	
	
}
