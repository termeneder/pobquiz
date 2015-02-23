package bram.pobquiz.analyse;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class StreakAnalysis implements Analysis {

	private static final int LEFT_PAD_DISTANCE = 50;
	
	private final int[] c_goodStreaks;
	
	public StreakAnalysis(int ... goodStreaks) {
		c_goodStreaks = goodStreaks;
	}
	
	@Override
	public String analyse(QuestionList list) {
		String str = "Streak Info: \n\n";
		
		str += StringUtils.leftPad("Positive/negative streaks: ", LEFT_PAD_DISTANCE) + " " + getStreakAbove(list, 0) + "/" + getStreakBelow(list, 0) + "\n";
		for (int streak : c_goodStreaks) {
			str += StringUtils.leftPad("Streaks of " + streak + " and above: ", LEFT_PAD_DISTANCE) + " " + getStreakAbove(list, streak-1) + "\n";
		}
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
