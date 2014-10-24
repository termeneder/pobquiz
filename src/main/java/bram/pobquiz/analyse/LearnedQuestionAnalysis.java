package bram.pobquiz.analyse;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class LearnedQuestionAnalysis implements Analysis {
	
	private int c_threshold;
	private int c_percentage;
	
	
	
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
		return "Amount learned: " + learnedAmount + " (" + learnedAmountPercentage + "%)";
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
