package bram.pobquiz.questiongenerator.history.uspresidents;

import java.util.ArrayList;
import java.util.List;

import bram.pobquiz.data.history.President;
import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class PresidentToStartAndEndOfTermQuestionGenerator {

	private USPresidents c_usPresidents;
	private QuestionList c_questionList;
	
	public PresidentToStartAndEndOfTermQuestionGenerator(USPresidents usPresidents,
			QuestionList questionList) {
		c_usPresidents = usPresidents;
		c_questionList = questionList;
	}

	public QuestionList build() {
		List<QuestionFactory> factoryList = new ArrayList<QuestionFactory>();
		for (President president : c_usPresidents) {
			addFactory(president, factoryList);
		}
		for (QuestionFactory factory : factoryList) {
			c_questionList.addQuestion(factory.build());
		}
		return c_questionList;
	}



	private void addFactory(President president, List<QuestionFactory> factoryList) {
		if (president.beginTerm == null || president.endTerm == null) {
			return;
		}
		String question = "From when to when was US president " + president.name + "'s term?";
		QuestionFactory sameFactory = null;
		for (QuestionFactory factory : factoryList) {
			if (factory.getQuestion().equals(question)) {
				sameFactory = factory;
			}
		}
		if (sameFactory == null) {
			QuestionFactory newFactory = new QuestionFactory();
			newFactory.withQuestion(question);
			newFactory.withAnswer(Integer.toString(president.beginTerm));
			newFactory.withAnswer(Integer.toString(president.endTerm));
			newFactory.withCaterorgy("US");
			newFactory.withCaterorgy("US Presidents");
			newFactory.withCaterorgy("President to start and end of term");
			factoryList.add(newFactory);
		} else {
			sameFactory.withAnswer(Integer.toString(president.beginTerm));
			sameFactory.withAnswer(Integer.toString(president.endTerm));
		}

	}
	
}
