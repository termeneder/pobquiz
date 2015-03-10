package bram.pobquiz.questiongenerator.history.ukmonarchy;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bram.pobquiz.data.history.UKMonarchs;
import bram.pobquiz.data.history.USPresidents;
import bram.pobquiz.question.QuestionList;

public class UKMonarchQuestionGenerator {

	public static void main(String[] args) {
		UKMonarchs ukMonarchs = readMonarchListFile();
		QuestionList questionList = QuestionList.getInstance();
		
		
		UKMonarchToStartAndEndOfTermQuestionGenerator  monarchsToStartAndEndTerm = new UKMonarchToStartAndEndOfTermQuestionGenerator(ukMonarchs, questionList);
		questionList = monarchsToStartAndEndTerm.build();
		
		
		questionList.save();
	}

	private static UKMonarchs readMonarchListFile() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(USPresidents.class);
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
