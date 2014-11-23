package bram.pobquiz;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import bram.pobquiz.inputter.CLIInputter;
import bram.pobquiz.inputter.Inputter;
import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;
import bram.pobquiz.question.filter.FilterRandom;
import bram.pobquiz.question.filter.FilterWorstSaldo;
import bram.pobquiz.quiz.Quiz;
import bram.pobquiz.quiz.goal.NoQuestionsLeftGoal;
import bram.pobquiz.quiz.goal.QuizGoal;
import bram.pobquiz.quiz.questionHandler.RemoveCorrectQuestion;
import bram.pobquiz.quiz.selector.QuestionSelector;
import bram.pobquiz.quiz.selector.RandomQuestionSelector;

public class RunBootcamp {

	
	
	public static void main(String[] args) throws CmdLineException {
		Config configs = new Config(args);
		System.out.println("Bootcamp");
		QuestionList.setSource(configs.filename);
		QuestionList list = QuestionList.getInstance();
		QuestionList worstList = new FilterWorstSaldo().filter(list);
		QuestionList filteredList;
		System.out.println(worstList.size() + " questions have been answered correctly " + getLeastCorrect(list) + " times.");
		if (configs.max != null && configs.max < worstList.size()) {
			filteredList = new FilterRandom(configs.max).filter(worstList);
			System.out.println(filteredList.size() + " selected for bootcamp.");
		} else {
			filteredList = worstList;
		}
		
		QuestionSelector baseSelector = new RandomQuestionSelector();
		Inputter inputter = new CLIInputter();
		Quiz quiz = new Quiz(filteredList, baseSelector, inputter);
		quiz.setSaveListAfterQuestion(false);
		
		quiz.addAskedQuestionHandler(new RemoveCorrectQuestion(quiz, configs.correctGoal));
		QuizGoal goal = new NoQuestionsLeftGoal(filteredList.size());
		
		while (!goal.goalReached(quiz)) {
			System.out.println();
			quiz.askQuestion();
			System.out.println("Goal statistics: " + goal.getProgress(quiz));
		}
		System.out.println("\nGoal reached!\n");
		System.out.println(quiz.getSessionInfo()); 

	}

	private static class Config {
		

		@Option(name="-file")
		String filename;
		
		@Option(name="-max")
		Integer max;
		
		@Option(name="-correctgoal")
		int correctGoal = 1;
		
		Config(String[] args) throws CmdLineException {
			CmdLineParser parser = new CmdLineParser(this);
			parser.parseArgument(args);
			
		}
		
	}
	
	private static int getLeastCorrect(QuestionList list) {
		int least = Integer.MAX_VALUE;
		for (QuestionStats stats : list) {
			if (stats.getTimesCorrect() < least) {
				least = stats.getTimesCorrect();
			}
		}
		return least;
	}
}