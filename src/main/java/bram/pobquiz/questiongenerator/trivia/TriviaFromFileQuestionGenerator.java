package bram.pobquiz.questiongenerator.trivia;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bram.pobquiz.data.trivia.Trivia;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionList;

public class TriviaFromFileQuestionGenerator {

	private QuestionList c_questionList;
	private String c_fileLocation;
	public TriviaFromFileQuestionGenerator(QuestionList questionList, String fileLocation) {
		c_fileLocation = fileLocation;
		c_questionList = questionList;
	}

	public QuestionList build() {

		for (Question trivium : readTriviaList()) {
			c_questionList.addQuestion(trivium);
		}
		return c_questionList;
	}

	
	private Trivia readTriviaList() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Trivia.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Trivia triviaList = (Trivia) jaxbUnmarshaller.unmarshal(getTriviaFile());
			return triviaList;
		} catch (JAXBException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	private File getTriviaFile() {
		File file = new File(c_fileLocation);
		return file;
	}
}
