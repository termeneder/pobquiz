package bram.pobquiz.question.filter;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class FilterWorstSaldo implements QuestionListFilter {

	@Override
	public QuestionList filter(QuestionList list) {
		int worst = getWorstSaldo(list);
		QuestionList filteredList = new QuestionList();
		for (QuestionStats stats : list) {
			if (stats.getSaldo() == worst) {
				filteredList.addQuestion(stats.getQuestion());
			}
		}
		return filteredList;
	}

	private int getWorstSaldo(QuestionList list) {
		int worst = Integer.MAX_VALUE;
		for (QuestionStats stats : list) {
			if (stats.getSaldo() < worst) {
				worst = stats.getSaldo();
			}
		}
		return worst;
	}

}
