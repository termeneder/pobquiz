package bram.pobquiz.questiongenerator.trivia;

import bram.pobquiz.question.QuestionList;

public class TriviaQuestionGenerator {

	public static void main(String[] args) {
		QuestionList questionList = QuestionList.getInstance();
		
		TriviaFromFileQuestionGenerator astronomy = new TriviaFromFileQuestionGenerator(questionList, "src/main/resources/data/world/trivia/astronomy.xml");
		questionList = astronomy.build();

		TriviaFromFileQuestionGenerator usState = new TriviaFromFileQuestionGenerator(questionList, "src/main/resources/data/world/trivia/US_State_trivia.xml");
		questionList = usState.build();
		
		TriviaFromFileQuestionGenerator usPresidents = new TriviaFromFileQuestionGenerator(questionList, "src/main/resources/data/world/trivia/US_Presidents_trivia.xml");
		questionList = usPresidents.build();
		
		TriviaFromFileQuestionGenerator country = new TriviaFromFileQuestionGenerator(questionList, "src/main/resources/data/world/trivia/Countries_trivia.xml");
		questionList = country.build();
		
		TriviaFromFileQuestionGenerator ukmonarchy = new TriviaFromFileQuestionGenerator(questionList, "src/main/resources/data/world/trivia/British_monarchs_trivia.xml");
		questionList = ukmonarchy.build();
		
		questionList.save();
	}




	

}
