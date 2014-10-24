package bram.pobquiz.questiongenerator.history.uspresidents;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.QuestionList;

public class USPresidentQuestionGenerator {

	public static void main(String[] args) {
		USPresidents usPresidents = readMovieListFile();
		QuestionList questionList = QuestionList.getInstance();
		
		NumberToPresidentQuestionGenerator numberToPresident = new NumberToPresidentQuestionGenerator(usPresidents, questionList);
		questionList = numberToPresident.build();

		PresidentInBetweenQuestionGenerator presidentInBetween = new PresidentInBetweenQuestionGenerator(usPresidents, questionList);
		questionList = presidentInBetween.build();
		
		PresidentBeforeQuestionGenerator presidentBefore = new PresidentBeforeQuestionGenerator(usPresidents, questionList);
		questionList = presidentBefore.build();
		
		PresidentAfterQuestionGenerator presidentAfter = new PresidentAfterQuestionGenerator(usPresidents, questionList);
		questionList = presidentAfter.build();
		
		TermToPresidentQuestionGenerator termToPresident = new TermToPresidentQuestionGenerator(usPresidents, questionList);
		questionList = termToPresident.build();
		
		LifeToPresidentQuestionGenerator lifeToPresident = new LifeToPresidentQuestionGenerator(usPresidents, questionList);
		questionList = lifeToPresident.build();
		
		PresidentToBirthQuestionGenerator presidentToBirth = new PresidentToBirthQuestionGenerator(usPresidents, questionList);
		questionList = presidentToBirth.build();
		
		PresidentToDeathQuestionGenerator presidentToDeath = new PresidentToDeathQuestionGenerator(usPresidents, questionList);
		questionList = presidentToDeath.build();
		
		PresidentToTermQuestionGenerator presidentToTerm = new PresidentToTermQuestionGenerator(usPresidents, questionList);
		questionList = presidentToTerm.build();
		
		
		
		questionList.save();
	}

	private static USPresidents readMovieListFile() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(USPresidents.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			USPresidents usPresidents = (USPresidents) jaxbUnmarshaller.unmarshal(getMoviesFile());
			return usPresidents;
		} catch (JAXBException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

	private static File getMoviesFile() {
		File file = new File("src/main/resources/data/world/history/US_Presidents.xml");
		return file;
	}
	

}
