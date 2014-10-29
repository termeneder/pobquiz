package bram.pobquiz.questiongenerator.countrygenerator;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.data.geography.CountriesOfTheWorld;
import bram.pobquiz.data.geography.Country;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class CountriesToCapitalQuestionGenerator {

	private CountriesOfTheWorld c_countriesOfTheWorld;
	private QuestionList c_questionList;
	
	public CountriesToCapitalQuestionGenerator(CountriesOfTheWorld countriesOfTheWorld,
			QuestionList questionList) {
		c_countriesOfTheWorld = countriesOfTheWorld;
		c_questionList = questionList;
	}

	public QuestionList build() {
		for (Country country : c_countriesOfTheWorld) {
			addQuestion(country);
		}
		return c_questionList;
	}



	private void addQuestion(Country country) {
		if (StringUtils.isNotBlank(country.getCapital())) {
			QuestionFactory factory = new QuestionFactory();
			factory.withQuestion("What is the capital of the country " + country.getName() + "?");
			factory.withAnswer(country.getCapital());
			factory.withCaterorgy("Geography");
			factory.withCaterorgy("Countries of the world");
			factory.withCaterorgy("Country to capital");
			Question question = factory.build();
			c_questionList.addQuestion(question);
		}
	}
	
}
