package bram.pobquiz.questiongenerator.countrygenerator;

import bram.pobquiz.data.geography.CountriesOfTheWorld;
import bram.pobquiz.data.geography.Country;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class CountryToNeighboursQuestionGenerator {

	private CountriesOfTheWorld c_countriesOfTheWorld;
	private QuestionList c_questionList;
	
	public CountryToNeighboursQuestionGenerator(CountriesOfTheWorld countriesOfTheWorld,
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
		if (country.getNeighbours() != null && 0 < country.getNeighbours().size()) {
			QuestionFactory factory = new QuestionFactory();
			factory.withQuestion("On what countries does " + country.getName() + " border?");
			for (String neighbour : country.getNeighbours()) {
				factory.withAnswer(neighbour);
			}
			factory.withCaterorgy("Geography");
			factory.withCaterorgy("Countries of the world");
			factory.withCaterorgy("Country to neighbours");
			Question question = factory.build();
			c_questionList.addQuestion(question);
		}
	}
	
}
