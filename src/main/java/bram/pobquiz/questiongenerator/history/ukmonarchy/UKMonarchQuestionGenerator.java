package bram.pobquiz.questiongenerator.history.ukmonarchy;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bram.pobquiz.data.history.UKMonarchs;
import bram.pobquiz.question.QuestionList;

public class UKMonarchQuestionGenerator {

	public static void main(String[] args) {
		UKMonarchs ukMonarchs = readMonarchListFile();
		QuestionList questionList = QuestionList.getInstance();
		
		
		UKMonarchToStartAndEndOfTermQuestionGenerator  monarchsToStartAndEndTerm = new UKMonarchToStartAndEndOfTermQuestionGenerator(ukMonarchs, questionList);
		questionList = monarchsToStartAndEndTerm.build();
		
		UKMonarchToStartOfTermQuestionGenerator monarchsToStartTerm = new UKMonarchToStartOfTermQuestionGenerator(ukMonarchs, questionList);
		questionList = monarchsToStartTerm.build();
		
		UKMonarchToEndOfTermQuestionGenerator monarchsToEndTerm = new UKMonarchToEndOfTermQuestionGenerator(ukMonarchs, questionList);
		questionList = monarchsToEndTerm.build();
		
		StartAndEndOfTermToUKMonarchQuestionGenerator termToMonarch = new StartAndEndOfTermToUKMonarchQuestionGenerator(ukMonarchs, questionList);
		questionList = termToMonarch.build();
		
		MonarchToHouseQuestionGenerator monarchToHouse = new MonarchToHouseQuestionGenerator(ukMonarchs, questionList);
		questionList = monarchToHouse.build();
		
		HouseToMonarchsQuestionGenerator houseToMonarch = new HouseToMonarchsQuestionGenerator(ukMonarchs, questionList);
		questionList = houseToMonarch.build();
		
		questionList.save();
	}

	private static UKMonarchs readMonarchListFile() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UKMonarchs.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			UKMonarchs ukMonarchs = (UKMonarchs) jaxbUnmarshaller.unmarshal(getMoviesFile());
			return ukMonarchs;
		} catch (JAXBException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

	private static File getMoviesFile() {
		File file = new File("src/main/resources/data/world/history/British_monarchs.xml");
		return file;
	}
	

}
