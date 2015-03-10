package bram.pobquiz.questiongenerator.history.ukmonarchy;

import java.util.ArrayList;
import java.util.List;

import bram.pobquiz.data.history.Monarch;
import bram.pobquiz.data.history.UKMonarchs;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class HouseToMonarchsQuestionGenerator {

	private UKMonarchs c_ukMonarchs;
	private QuestionList c_questionList;
	
	public HouseToMonarchsQuestionGenerator(UKMonarchs ukMonarchs,
			QuestionList questionList) {
		c_ukMonarchs = ukMonarchs;
		c_questionList = questionList;
	}

	public QuestionList build() {
		List<QuestionFactory> factoryList = new ArrayList<QuestionFactory>();
		for (Monarch monarch : c_ukMonarchs) {
			addFactory(monarch, factoryList);
		}
		for (QuestionFactory factory : factoryList) {
			c_questionList.addQuestion(factory.build());
		}
		return c_questionList;
	}



	private void addFactory(Monarch monarch, List<QuestionFactory> factoryList) {
		if (monarch.name == null || monarch.house == null) {
			return;
		}
		String question = makeQuestion(monarch);
		QuestionFactory sameFactory = null;
		for (QuestionFactory factory : factoryList) {
			if (factory.getQuestion().equals(question)) {
				sameFactory = factory;
			}
		}
		if (sameFactory == null) {
			QuestionFactory newFactory = new QuestionFactory();
			newFactory.withQuestion(question);
			newFactory.withAnswer(monarch.name);
			newFactory.withCaterorgy("UK");
			newFactory.withCaterorgy("UK Monarchs");
			newFactory.withCaterorgy("House to monarchs");
			factoryList.add(newFactory);
		} else {
			sameFactory.withAnswer(monarch.name);
		}

	}
	
	private String makeQuestion(Monarch monarch) {
		String question = "What members of the house " + monarch.house + " were also King or Queen of " + monarch.monarch_of +"?";
		return question;
	}
	
}
