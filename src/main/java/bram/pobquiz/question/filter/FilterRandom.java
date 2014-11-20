package bram.pobquiz.question.filter;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;
import bram.pobquiz.quiz.selector.RandomQuestionSelector;

public class FilterRandom implements QuestionListFilter {

	private final int c_max;
	
	public FilterRandom(int max) {
		c_max = max;
	}
	
	@Override
	public QuestionList filter(QuestionList list) {
		
		if (list.size() <= c_max) {
			return copy(list);
		} else {
			return pickRandomly(list);
		}
	}

	private QuestionList copy(QuestionList list) {
		QuestionList newList = new QuestionList();
		for (QuestionStats stats : list) {
			newList.addQuestion(stats.getQuestion());
		}
		return newList;
	}

	private QuestionList pickRandomly(QuestionList list) {
		QuestionList newList = new QuestionList();
		RandomQuestionSelector selector = new RandomQuestionSelector();
		while(newList.size() < c_max) {
			newList.addQuestion(selector.getQuestion(list).getQuestion());
		}
		return newList;
	}



}
