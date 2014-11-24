package bram.pobquiz.questiongenerator.history.uspresidents;

import bram.pobquiz.data.history.President;
import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class NumberToPresidentQuestionGenerator {

	private USPresidents c_usPresidents;
	private QuestionList c_questionList;
	
	public NumberToPresidentQuestionGenerator(USPresidents usPresidents,
			QuestionList questionList) {
		c_usPresidents = usPresidents;
		c_questionList = questionList;
	}

	public QuestionList build() {

		for (President president: c_usPresidents) {
			addQuestion(president);
		}
		return c_questionList;
	}



	private void addQuestion(President president) {
		QuestionFactory factory = new QuestionFactory();
		switch (president.number) {
		case 1 : 
			factory.withQuestion("Who was the 1st US president?");
			break;
		case 2 : 
			factory.withQuestion("Who was the 2nd US president?");
			break;
		case 3 : 
			factory.withQuestion("Who was the 3rd US president?");
			break;
		default : 
			factory.withQuestion("Who was the "+ president.number + "th US president?");
			break;
		}

		factory.withAnswer(president.name);
		factory.withCaterorgy("US");
		factory.withCaterorgy("US Presidents");
		factory.withCaterorgy("Number to US President");

		Question question = factory.build();
		c_questionList.addQuestion(question);
		
	}
	
}
