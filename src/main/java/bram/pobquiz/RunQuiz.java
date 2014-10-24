package bram.pobquiz;

import bram.pobquiz.inputter.CLIInputter;
import bram.pobquiz.inputter.Inputter;
import bram.pobquiz.question.QuestionList;
import bram.pobquiz.quiz.CorrectAnswersGoal;
import bram.pobquiz.quiz.Quiz;
import bram.pobquiz.quiz.QuizGoal;
import bram.pobquiz.quiz.selector.BalanceAmountTestedQuestionSelector;
import bram.pobquiz.quiz.selector.ExcludeCategorySelector;
import bram.pobquiz.quiz.selector.QuestionSelector;
import bram.pobquiz.quiz.selector.WeightedRandomQuestionSelector;

public class RunQuiz {

	public static void main(String[] args) {
		System.out.println("Quiz");
		QuestionList list = QuestionList.getInstance();
		QuestionSelector baseSelector = new WeightedRandomQuestionSelector();
		
		ExcludeCategorySelector excludeSelector = new ExcludeCategorySelector(baseSelector);
		//selector.setCategoriesToExclude("US States to capital", "State to neighbours");
		
		BalanceAmountTestedQuestionSelector balanceSelector = new BalanceAmountTestedQuestionSelector(excludeSelector, 5);
		
		Inputter inputter = new CLIInputter();
		Quiz quiz = new Quiz(list, balanceSelector, inputter);
		QuizGoal goal = new CorrectAnswersGoal(100);
		while (!goal.goalReached(quiz)) {
			System.out.println();
			quiz.askQuestion();
		}
		System.out.println("\nGoal reached!\n");
		System.out.println(quiz.getSessionInfo());
	}
	
}