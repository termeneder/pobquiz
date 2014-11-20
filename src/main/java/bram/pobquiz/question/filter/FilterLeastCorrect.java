package bram.pobquiz.question.filter;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class FilterLeastCorrect implements QuestionListFilter {

	@Override
	public QuestionList filter(QuestionList list) {
		int least = getLeastCorrect(list);
		QuestionList filteredList = new QuestionList();
		for (QuestionStats stats : list) {
			if (stats.getTimesCorrect() == least) {
				filteredList.addQuestion(stats.getQuestion());
			}
		}
		return filteredList;
	}

	private int getLeastCorrect(QuestionList list) {
		int least = Integer.MAX_VALUE;
		for (QuestionStats stats : list) {
			if (stats.getTimesCorrect() < least) {
				least = stats.getTimesCorrect();
			}
		}
		return least;
	}

}
