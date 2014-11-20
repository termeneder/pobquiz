package bram.pobquiz.quiz.goal;

import bram.pobquiz.quiz.Quiz;

public interface QuizGoal {

	public boolean goalReached(Quiz quiz);
	
	public String getProgress(Quiz quiz);
	
}
