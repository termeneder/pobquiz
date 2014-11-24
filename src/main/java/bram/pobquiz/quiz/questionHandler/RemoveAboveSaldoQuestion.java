package bram.pobquiz.quiz.questionHandler;

import bram.pobquiz.question.QuestionStats;
import bram.pobquiz.quiz.Quiz;

public class RemoveAboveSaldoQuestion implements AskedQuestionHandler {

	private Quiz c_quiz;
	private int c_cutoffSaldo;
	
	public RemoveAboveSaldoQuestion(Quiz quiz, int cutoffSaldo) {
		c_quiz = quiz;
		c_cutoffSaldo = cutoffSaldo;
	}
	
	@Override
	public void handleCorrectQuestion(QuestionStats question) {
		if (question.getSaldo() >= c_cutoffSaldo) {
			c_quiz.getQuestionList().removeQuestion(question);
		}

	}

	@Override
	public void handleIncorrectQuestion(QuestionStats question) {
		

	}

}
