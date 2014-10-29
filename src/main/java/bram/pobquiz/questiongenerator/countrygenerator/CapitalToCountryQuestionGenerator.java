package bram.pobquiz.questiongenerator.countrygenerator;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.data.geography.CountriesOfTheWorld;
import bram.pobquiz.data.geography.Country;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class CapitalToCountryQuestionGenerator {

	private CountriesOfTheWorld c_countriesOfTheWorld;
	private QuestionList c_questionList;
	
	public CapitalToCountryQuestionGenerator(CountriesOfTheWorld countriesOfTheWorld,
			QuestionList questionList) {
		c_countriesOfTheWorld = countriesOfTheWorld;
		c_questionList = questionList;
	}

	public QuestionList build() {

		for (Country country: c_countriesOfTheWorld) {
			addQuestion(country);
		}
		return c_questionList;
	}



	private void addQuestion(Country country) {
		if (StringUtils.isNotBlank(country.getCapital())) {
			QuestionFactory factory = new QuestionFactory();
			factory.withQuestion("What country has the capital " + country.getCapital() + "?");
			factory.withAnswer(country.getName());
			factory.withCaterorgy("Geography");
			factory.withCaterorgy("Countries of the world");
			factory.withCaterorgy("Capital to country");
			Question question = factory.build();
			c_questionList.addQuestion(question);
		}
		
	}
	
}
