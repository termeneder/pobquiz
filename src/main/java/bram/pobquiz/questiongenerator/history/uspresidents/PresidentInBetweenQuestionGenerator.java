package bram.pobquiz.questiongenerator.history.uspresidents;

import bram.pobquiz.data.history.President;
import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class PresidentInBetweenQuestionGenerator {

	private USPresidents c_usPresidents;
	private QuestionList c_questionList;
	
	public PresidentInBetweenQuestionGenerator(USPresidents usPresidents,
			QuestionList questionList) {
		c_usPresidents = usPresidents;
		c_questionList = questionList;
	}

	public QuestionList build() {

		for (int nr = 1 ; nr < c_usPresidents.size() - 1 ; nr ++) {
			addQuestion(c_usPresidents.get(nr-1), c_usPresidents.get(nr), c_usPresidents.get(nr+1));
		}
		return c_questionList;
	}



	private void addQuestion(President presidentBefore, President president, President presidentAfter) {
		QuestionFactory factory = new QuestionFactory();
		factory.withQuestion("Who was US president between " + presidentBefore.name + " and " + presidentAfter.name + "?");
		factory.withAnswer(president.name);
		factory.withCaterorgy("US");
		factory.withCaterorgy("US Presidents");
		factory.withCaterorgy("President in between");

		Question question = factory.build();
		c_questionList.addQuestion(question);
		
	}
	
}
