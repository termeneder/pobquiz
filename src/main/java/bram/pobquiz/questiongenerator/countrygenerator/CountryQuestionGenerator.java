package bram.pobquiz.questiongenerator.countrygenerator;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bram.pobquiz.data.geography.CountriesOfTheWorld;
import bram.pobquiz.question.QuestionList;

public class CountryQuestionGenerator {

	public static void main(String[] args) {
		CountriesOfTheWorld countriesOfTheWorld = readCountriesOfTheWorld();
		QuestionList questionList = QuestionList.getInstance();
		
		CountriesToCapitalQuestionGenerator stateToCapital = new CountriesToCapitalQuestionGenerator(countriesOfTheWorld, questionList);
		questionList = stateToCapital.build();
		
		CapitalToCountryQuestionGenerator capitalToState = new CapitalToCountryQuestionGenerator(countriesOfTheWorld, questionList);
		questionList = capitalToState.build();

		BorderingCountriesToCountryQuestionGenerator neighbourToCountry = new BorderingCountriesToCountryQuestionGenerator(countriesOfTheWorld, questionList);
		questionList = neighbourToCountry.build();
		
			
		questionList.save();
	}

	private static CountriesOfTheWorld readCountriesOfTheWorld() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CountriesOfTheWorld.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CountriesOfTheWorld countriesOfTheWorld = (CountriesOfTheWorld) jaxbUnmarshaller.unmarshal(readCountriesOfTheWorldFile());
			return countriesOfTheWorld;
		} catch (JAXBException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

	private static File readCountriesOfTheWorldFile() {
		File file = new File("src/main/resources/data/world/geography/Countries.xml");
		return file;
	}
	

}
