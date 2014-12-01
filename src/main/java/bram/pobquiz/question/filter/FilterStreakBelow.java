package bram.pobquiz.question.filter;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class FilterStreakBelow implements QuestionListFilter {

	private final int c_cutoff;
	
	public FilterStreakBelow(int cufoff) {
		c_cutoff = cufoff;
	}
	
	@Override
	public QuestionList filter(QuestionList list) {
		QuestionList filteredList = new QuestionList();
		for (QuestionStats stats : list) {
			if (stats.getStreak() < c_cutoff) {
				filteredList.addQuestion(stats.getQuestion());
			}
		}
		return filteredList;
	}



}
