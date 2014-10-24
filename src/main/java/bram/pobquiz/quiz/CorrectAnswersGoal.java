package bram.pobquiz.quiz;

public class CorrectAnswersGoal implements QuizGoal {

	private int c_goal;
	
	public CorrectAnswersGoal(int goal) {
		c_goal = goal;
	}
	
	@Override
	public boolean goalReached(Quiz quiz) {
		// TODO Auto-generated method stub
		return quiz.getSessionInfo().getQuestionAnsweredCorrectly() >= c_goal;
	}

}
