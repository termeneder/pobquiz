package bram.pobquiz;

import bram.pobquiz.questiongenerator.history.uspresidents.USPresidentQuestionGenerator;
import bram.pobquiz.questiongenerator.moviegenerator.MovieQuestionGenerator;
import bram.pobquiz.questiongenerator.stategenerator.StateQuestionGenerator;
import bram.pobquiz.questiongenerator.trivia.TriviaQuestionGenerator;

public class RunQuestionGenerators {

	public static void main(String[] args) {
		StateQuestionGenerator.main(args);
		MovieQuestionGenerator.main(args);
		TriviaQuestionGenerator.main(args);
		USPresidentQuestionGenerator.main(args);
	}
	
}
