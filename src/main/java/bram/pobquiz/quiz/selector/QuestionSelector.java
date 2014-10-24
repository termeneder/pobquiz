package bram.pobquiz.quiz.selector;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public interface QuestionSelector {

	public QuestionStats getQuestion(QuestionList list);
	
}
