package bram.pobquiz.quiz.goal;

import bram.pobquiz.quiz.Quiz;

public class CorrectAnswersGoal implements QuizGoal {

	private int c_goal;
	
	public CorrectAnswersGoal(int goal) {
		c_goal = goal;
	}
	
	@Override
	public boolean goalReached(Quiz quiz) {
		return quiz.getSessionInfo().getQuestionAnsweredCorrectly() >= c_goal;
	}

	@Override
	public String getProgress(Quiz quiz) {
		
		return quiz.getSessionInfo().getQuestionAnsweredCorrectly() + "/" + c_goal + " (total " + quiz.getSessionInfo().getQuestionsAsked()+  ")";
	}

}
