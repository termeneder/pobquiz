package bram.pobquiz.quiz.questionHandler;

import bram.pobquiz.question.QuestionStats;
import bram.pobquiz.quiz.Quiz;

public class RemoveCorrectQuestion implements AskedQuestionHandler {

	private Quiz c_quiz;
	private int c_correctGoal;
	
	public RemoveCorrectQuestion(Quiz quiz, int correctGoal) {
		c_quiz = quiz;
		c_correctGoal = correctGoal;
	}
	
	@Override
	public void handleCorrectQuestion(QuestionStats question) {
		if (question.getTimesCorrect() >= c_correctGoal) {
			c_quiz.getQuestionList().removeQuestion(question);
		}

	}

	@Override
	public void handleIncorrectQuestion(QuestionStats question) {
		

	}

}
