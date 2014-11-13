package bram.pobquiz.quiz.selector;

import java.util.Random;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class WeightedQuestionSelector implements QuestionSelector {
	
	private QuestionSelector c_baseSelector;
	
	public WeightedQuestionSelector(QuestionSelector baseSelector) {
		c_baseSelector = baseSelector;
	}
	
	@Override
	public QuestionStats getQuestion(QuestionList list) {
		while (true) {
			QuestionStats question = c_baseSelector.getQuestion(list);
			if (weightedSelect(question)) {
				return question;
			}
		}
	}

	private boolean weightedSelect(QuestionStats question) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(question.getTimesTested()+1);
		int cutoff = question.getTimesCorrect();
		return cutoff <= randomNumber;
	}

}
