package bram.pobquiz.questiongenerator.history.ukmonarchy;

import java.util.ArrayList;
import java.util.List;

import bram.pobquiz.data.history.Monarch;
import bram.pobquiz.data.history.UKMonarchs;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class MonarchToHouseQuestionGenerator {

	private UKMonarchs c_ukMonarchs;
	private QuestionList c_questionList;
	
	public MonarchToHouseQuestionGenerator(UKMonarchs ukMonarchs,
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
			newFactory.withAnswer(monarch.house);
			newFactory.withCaterorgy("UK");
			newFactory.withCaterorgy("UK Monarchs");
			newFactory.withCaterorgy("Monarch to house");
			factoryList.add(newFactory);
		} else {
			sameFactory.withAnswer(monarch.house);
		}

	}
	
	private String makeQuestion(Monarch monarch) {
		String question = "";
		switch (monarch.gender) {
		case male:
			question += "King";
			break;
		case female:
			question += "Queen";
			break;
		default:
			throw new RuntimeException("UKNOWN GENDER FOR MONARCH");
		}
		question += " " + monarch.name + " was a member of which house?";
		return question;
	}
	
}
