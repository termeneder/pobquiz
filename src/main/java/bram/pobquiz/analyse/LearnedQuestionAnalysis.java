package bram.pobquiz.analyse;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class LearnedQuestionAnalysis implements Analysis {
	
	private int c_threshold;
	private int c_percentage;
	
	private static final int FIRST_COLUMN_WIDTH = 23;
	private static final int SECOND_COLUMN_WIDTH = 6;
	private static final int THIRD_COLUMN_WIDTH = 7;
	
	public LearnedQuestionAnalysis(int threshold, int percentage) {
		c_threshold = threshold;
		c_percentage = percentage;
	}

	private String getDescription() {
		return "Learned questions\n(A question is concidered learned if it has been asked at least "+c_threshold+
				" times and is answered correctly "+c_percentage+"%)";
	}

	@Override
	public String analyse(QuestionList list) {
		return getDescription() + "\n\n" + 
				getAnalysis(list);
	}

	private String getAnalysis(QuestionList list) {
		int learnedAmount = 0;
		for (QuestionStats stats : list) {
			if (isLearned(stats)) {
				learnedAmount++;
			}
		}
		int learnedAmountPercentage = (learnedAmount*100)/list.size();
		int notYetLearnedAmount = list.size()-learnedAmount;
		int notYetLearnedAmountPercentage = 100-learnedAmountPercentage;
		return 
			StringUtils.rightPad("Amount learned:", FIRST_COLUMN_WIDTH) + 
			StringUtils.leftPad(Integer.toString(learnedAmount), SECOND_COLUMN_WIDTH) + 
			StringUtils.leftPad("(" + learnedAmountPercentage + "%)" , THIRD_COLUMN_WIDTH)+"\n" + 
			StringUtils.rightPad("Amount not yet learned:", FIRST_COLUMN_WIDTH) + 
			StringUtils.leftPad(Integer.toString(notYetLearnedAmount), SECOND_COLUMN_WIDTH) + 
			StringUtils.leftPad("(" + notYetLearnedAmountPercentage + "%)", THIRD_COLUMN_WIDTH);
	}

	private boolean isLearned(QuestionStats stats) {
		if (stats.getTimesTested() < c_threshold) {
			return false;
		}
		if (stats.getTimesCorrect()*100 < stats.getTimesTested() * c_percentage) {
			return false;
		}
		return true;
	}


	
}
