package bram.pobquiz.questiongenerator.countrygenerator;

import java.util.ArrayList;
import java.util.List;

import bram.pobquiz.data.geography.CountriesOfTheWorld;
import bram.pobquiz.data.geography.Country;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class BorderingCountriesToCountryQuestionGenerator {

	private CountriesOfTheWorld c_countriesOfTheWorld;
	private QuestionList c_questionList;
	
	public BorderingCountriesToCountryQuestionGenerator(CountriesOfTheWorld countriesOfTheWorld,
			QuestionList questionList) {
		c_countriesOfTheWorld = countriesOfTheWorld;
		c_questionList = questionList;
	}

	public QuestionList build() {

		List<QuestionFactory> factoryList = new ArrayList<QuestionFactory>();
		for (Country country : c_countriesOfTheWorld) {
			addFactory(country, factoryList);
		}
		for (QuestionFactory factory : factoryList) {
			c_questionList.addQuestion(factory.build());
		}
		return c_questionList;
	}






	private void addFactory(Country country, List<QuestionFactory> factoryList) {
		if (0 < country.getNeighbours().size()) {
			String question = createQuestionString(country);
			QuestionFactory sameFactory = null;
			for (QuestionFactory factory : factoryList) {
				if (factory.getQuestion().equals(question)) {
					sameFactory = factory;
				}
			}
			if (sameFactory == null) {
				QuestionFactory newFactory = new QuestionFactory();
				newFactory.withQuestion(question);
				newFactory.withAnswer(country.getName());
				newFactory.withCaterorgy("Geography");
				newFactory.withCaterorgy("Countries of the world");
				newFactory.withCaterorgy("Neighbours to country");
				factoryList.add(newFactory);
			} else {
				sameFactory.withAnswer(country.getName());
			}
		}
	}

	private String createQuestionString(Country country) {
		String question = "What country borders (solely) on ";
		for (int i = 0 ; i < country.getNeighbours().size() ; i++) {
			if (i == 0) {
				
			} else if (i + 1 == country.getNeighbours().size()) {
				question += " and ";
			} else {
				question += ", ";
			}
			question += country.getNeighbours().get(i);
		}
		question += "?";
		return question;
	}
	
}
