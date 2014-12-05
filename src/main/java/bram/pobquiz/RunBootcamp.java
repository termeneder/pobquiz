package bram.pobquiz;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import bram.pobquiz.inputter.CLIInputter;
import bram.pobquiz.inputter.Inputter;
import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;
import bram.pobquiz.question.filter.FilterRandom;
import bram.pobquiz.question.filter.FilterStreakBelow;
import bram.pobquiz.question.filter.FilterWorstSaldo;
import bram.pobquiz.quiz.Quiz;
import bram.pobquiz.quiz.goal.NoQuestionsLeftGoal;
import bram.pobquiz.quiz.goal.QuizGoal;
import bram.pobquiz.quiz.questionHandler.RemoveAboveCorrectQuestion;
import bram.pobquiz.quiz.questionHandler.RemoveAboveSaldoQuestion;
import bram.pobquiz.quiz.selector.QuestionSelector;
import bram.pobquiz.quiz.selector.RandomQuestionSelector;

public class RunBootcamp {

	
	
	public static void main(String[] args) throws CmdLineException {
		Config configs = new Config(args);
		System.out.println("Bootcamp");
		
		QuestionList list = getList(configs);
		
		QuestionSelector baseSelector = new RandomQuestionSelector();
		Inputter inputter = new CLIInputter();
		Quiz quiz = new Quiz(list, baseSelector, inputter);
		quiz.setSaveListAfterQuestion(false);
		
		quiz = setAskedQuestionHandler(quiz, configs);
		
		QuizGoal goal = new NoQuestionsLeftGoal(list.size());
		
		while (!goal.goalReached(quiz)) {
			System.out.println();
			quiz.askQuestion();
			System.out.println("Goal statistics: " + goal.getProgress(quiz));
		}
		System.out.println("\nGoal reached!\n");
		System.out.println(quiz.getSessionInfo()); 

	}
	


	private static Quiz setAskedQuestionHandler(Quiz quiz, Config configs) {
		switch (configs.goalType) {
		case CORRECT:
			quiz.addAskedQuestionHandler(new RemoveAboveCorrectQuestion(quiz, configs.correctGoal));
		case SALDO: 
		default:
			quiz.addAskedQuestionHandler(new RemoveAboveSaldoQuestion(quiz, configs.correctGoal));
			break;
		}
		return quiz;
	}



	private static QuestionList getList(Config configs) {
		QuestionList.setSource(configs.filename);
		QuestionList list = QuestionList.getInstance();
		QuestionList worstList;
		switch(configs.selectOptions) {
		case NEGATIVE_STREAK:
			worstList = new FilterStreakBelow(1).filter(list); 
			System.out.println(worstList.size() + " questions have a negative streak.");
			break;
		case LOWEST_SALDO: 
		default:	
			worstList = new FilterWorstSaldo().filter(list);
			System.out.println(worstList.size() + " questions have been answered correctly " + getLeastCorrect(list) + " times.");
			break;
		}
		QuestionList filteredList;
		
		if (configs.max != null && configs.max < worstList.size()) {
			filteredList = new FilterRandom(configs.max).filter(worstList);
			System.out.println(filteredList.size() + " selected for bootcamp.");
		} else {
			filteredList = worstList;
		}
		return filteredList;
	}

	private static class Config {
		

		@Option(name="-file")
		String filename;
		
		@Option(name="-max")
		Integer max;
		
		@Option(name="-correctgoal")
		int correctGoal = 1;
		
		enum GoalType {SALDO, CORRECT}
		
		@Option(name="-goal") 
		GoalType goalType = GoalType.SALDO;
		
		enum SelectOptions {LOWEST_SALDO, NEGATIVE_STREAK}
		
		@Option(name="-select")
		SelectOptions selectOptions = SelectOptions.LOWEST_SALDO;
		
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