package bram.pobquiz.questiongenerator.history.uspresidents;

import bram.pobquiz.data.history.President;
import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class PresidentToDeathQuestionGenerator {

	private USPresidents c_usPresidents;
	private QuestionList c_questionList;
	
	public PresidentToDeathQuestionGenerator(USPresidents usPresidents,
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
		if (president.death != null) {
			QuestionFactory factory = new QuestionFactory();
			
			factory.withQuestion("When did US president " + president.name + " die?");
	
			factory.withAnswer(Integer.toString(president.death));
			factory.withCaterorgy("US");
			factory.withCaterorgy("US Presidents");
			factory.withCaterorgy("US President to death");
	
			Question question = factory.build();
			c_questionList.addQuestion(question);
		}
		
	}
	
}
