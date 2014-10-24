package bram.pobquiz.questiongenerator.history.uspresidents;

import bram.pobquiz.data.history.President;
import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class TermToPresidentQuestionGenerator {

	private USPresidents c_usPresidents;
	private QuestionList c_questionList;
	
	public TermToPresidentQuestionGenerator(USPresidents usPresidents,
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
		if (president.beginTerm != null && president.endTerm != null) {
			QuestionFactory factory = new QuestionFactory();
			
			factory.withQuestion("Who whas US president from " + president.beginTerm + " to " + president.endTerm + "?");
	
			factory.withAnswer(president.name);
			factory.withCaterorgy("US");
			factory.withCaterorgy("US Presidents");
			factory.withCaterorgy("Term to US President");
	
			Question question = factory.build();
			c_questionList.addQuestion(question);
		}
		
	}
	
}
