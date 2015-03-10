package bram.pobquiz;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;




import bram.pobquiz.question.QuestionList;
import bram.pobquiz.questiongenerator.countrygenerator.CountryQuestionGenerator;
import bram.pobquiz.questiongenerator.history.ukmonarchy.UKMonarchQuestionGenerator;
import bram.pobquiz.questiongenerator.history.uspresidents.USPresidentQuestionGenerator;
import bram.pobquiz.questiongenerator.moviegenerator.MovieQuestionGenerator;
import bram.pobquiz.questiongenerator.stategenerator.StateQuestionGenerator;
import bram.pobquiz.questiongenerator.trivia.TriviaQuestionGenerator;

public class RunQuestionGenerators {

	public static void main(String[] args) throws CmdLineException {
		Config configs = new Config(args);
		QuestionList.setSource(configs.filename);
		StateQuestionGenerator.main(args);
		MovieQuestionGenerator.main(args);
		TriviaQuestionGenerator.main(args);
		USPresidentQuestionGenerator.main(args);
		CountryQuestionGenerator.main(args);
		UKMonarchQuestionGenerator.main(args);
		System.out.println("Question generators ran");
	}
	
	private static class Config {

		@Option(name="-file")
		String filename;
		
		Config(String[] args) throws CmdLineException {
			CmdLineParser parser = new CmdLineParser(this);
			parser.parseArgument(args);
			
		}
		
	}
}
