package bram.pobquiz.quiz.goal;

import bram.pobquiz.quiz.Quiz;

public class NoQuestionsLeftGoal implements QuizGoal {
	
	private final int c_initialQuestions;
	
	public NoQuestionsLeftGoal(int initialQuestions) {
		c_initialQuestions = initialQuestions;
	}
	
	@Override
	public boolean goalReached(Quiz quiz) {
		return quiz.getQuestionList().size() == 0;
	}

	@Override
	public String getProgress(Quiz quiz) {
		
		return quiz.getQuestionList().size() + "/" + c_initialQuestions;
	}
	
	

}
