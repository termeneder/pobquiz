
package bram.pobquiz.quiz.questionHandler;

import bram.pobquiz.question.QuestionStats;

public interface AskedQuestionHandler {

	public void handleCorrectQuestion(QuestionStats question);
	
	public void handleIncorrectQuestion(QuestionStats question);
	
}
