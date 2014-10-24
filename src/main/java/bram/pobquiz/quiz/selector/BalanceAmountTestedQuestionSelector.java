package bram.pobquiz.quiz.selector;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

/**
 * When selecting a question this selector uses the given base selector to use
 * n (where n is defined by the sample size) questions and returns the one that
 * has been asked the fewest.
 */
public class BalanceAmountTestedQuestionSelector implements QuestionSelector {

	private QuestionSelector c_baseSelector;
	private int c_sampleSize;
	
	public BalanceAmountTestedQuestionSelector(QuestionSelector baseSelector, int sampleSize) {
		c_baseSelector = baseSelector;
		c_sampleSize = sampleSize;
	}
	
	@Override
	public QuestionStats getQuestion(QuestionList list) {
		QuestionStats lowestAmountTestedQuestion = null;
		int lowestAmountTested = Integer.MAX_VALUE;
		for (int i = 0 ; i < c_sampleSize ; i++) {
			QuestionStats selected = c_baseSelector.getQuestion(list);
			if (selected.getTimesTested() < lowestAmountTested) {
				lowestAmountTestedQuestion = selected;
				lowestAmountTested = selected.getTimesTested();
			}
		}
		return lowestAmountTestedQuestion;
	}


	
	

}
