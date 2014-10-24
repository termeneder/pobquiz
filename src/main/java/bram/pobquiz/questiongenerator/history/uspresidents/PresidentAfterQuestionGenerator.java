package bram.pobquiz.questiongenerator.history.uspresidents;

import java.util.ArrayList;
import java.util.List;

import bram.pobquiz.data.history.President;
import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class PresidentAfterQuestionGenerator {

	private USPresidents c_usPresidents;
	private QuestionList c_questionList;
	
	public PresidentAfterQuestionGenerator(USPresidents usPresidents,
			QuestionList questionList) {
		c_usPresidents = usPresidents;
		c_questionList = questionList;
	}

	public QuestionList build() {
		List<QuestionFactory> factoryList = new ArrayList<QuestionFactory>();
		for (int nr = 1 ; nr < c_usPresidents.size() ; nr ++) {
			addFactory(c_usPresidents.get(nr-1), c_usPresidents.get(nr), factoryList);
		}
		for (QuestionFactory factory : factoryList) {
			c_questionList.addQuestion(factory.build());
		}
		return c_questionList;
	}



	private void addFactory(President presidentBefore, President president, List<QuestionFactory> factoryList) {
		String question = "Who was US president after " + presidentBefore.name + "?";
		QuestionFactory sameFactory = null;
		for (QuestionFactory factory : factoryList) {
			if (factory.getQuestion().equals(question)) {
				sameFactory = factory;
			}
		}
		if (sameFactory == null) {
			QuestionFactory newFactory = new QuestionFactory();
			newFactory.withQuestion("Who was US president after " + presidentBefore.name + "?");
			newFactory.withAnswer(president.name);
			newFactory.withCaterorgy("US");
			newFactory.withCaterorgy("US Presidents");
			newFactory.withCaterorgy("President after");
			factoryList.add(newFactory);
		} else {
			sameFactory.withAnswer(president.name);
		}
		
	}

	
}
