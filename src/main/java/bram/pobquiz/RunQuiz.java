package bram.pobquiz;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import bram.pobquiz.inputter.CLIInputter;
import bram.pobquiz.inputter.Inputter;
import bram.pobquiz.question.QuestionList;
import bram.pobquiz.quiz.Quiz;
import bram.pobquiz.quiz.goal.CorrectAnswersGoal;
import bram.pobquiz.quiz.goal.QuizGoal;
import bram.pobquiz.quiz.selector.BalanceAmountTestedQuestionSelector;
import bram.pobquiz.quiz.selector.QuestionSelector;
import bram.pobquiz.quiz.selector.RandomQuestionSelector;
import bram.pobquiz.quiz.selector.WeightedQuestionSelector;

public class RunQuiz {

	
	
	public static void main(String[] args) throws CmdLineException {
		Config configs = new Config(args);
		System.out.println("Quiz to " + configs.correctAnswerGoal);

		QuestionList.setSource(configs.filename);
		QuestionList list = QuestionList.getInstance();
		QuestionSelector baseSelector = new RandomQuestionSelector();
		QuestionSelector weightedSelector = new WeightedQuestionSelector(baseSelector);
		BalanceAmountTestedQuestionSelector balanceSelector = new BalanceAmountTestedQuestionSelector(weightedSelector, 2);
		
		Inputter inputter = new CLIInputter();
		Quiz quiz = new Quiz(list, balanceSelector, inputter);
		QuizGoal goal = new CorrectAnswersGoal(configs.correctAnswerGoal);
		while (!goal.goalReached(quiz)) {
			System.out.println();
			quiz.askQuestion();
			
		}
		System.out.println("\nGoal reached!\n");
		System.out.println(quiz.getSessionInfo());
	}

	private static class Config {
		
		@Option(name="-goal")
		int correctAnswerGoal = 100;
		
		@Option(name="-file")
		String filename;
		
		
		Config(String[] args) throws CmdLineException {
			CmdLineParser parser = new CmdLineParser(this);
			parser.parseArgument(args);
			
		}
		
	}
	
}