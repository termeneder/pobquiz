package bram.pobquiz.questiongenerator.history.uspresidents;

import bram.pobquiz.data.history.President;
import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class PresidentToBirthQuestionGenerator {

	private USPresidents c_usPresidents;
	private QuestionList c_questionList;
	
	public PresidentToBirthQuestionGenerator(USPresidents usPresidents,
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
		if (president.birth != null) {
			QuestionFactory factory = new QuestionFactory();
			
			factory.withQuestion("When was US president " + president.name + " born?");
	
			factory.withAnswer(Integer.toString(president.birth));
			factory.withCaterorgy("US");
			factory.withCaterorgy("US Presidents");
			factory.withCaterorgy("US President to birth");
	
			Question question = factory.build();
			c_questionList.addQuestion(question);
		}
		
	}
	
}
