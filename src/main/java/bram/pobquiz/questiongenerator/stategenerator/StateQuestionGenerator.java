package bram.pobquiz.questiongenerator.stategenerator;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bram.pobquiz.data.geography.USStates;
import bram.pobquiz.question.QuestionList;

public class StateQuestionGenerator {

	public static void main(String[] args) {
		USStates usStates = readUSStatesFile();
		QuestionList questionList = QuestionList.getInstance();
		
		StatesByStartingLettersQuestionGenerator statesByStartingLetter = new StatesByStartingLettersQuestionGenerator(usStates, questionList);
		questionList = statesByStartingLetter.build();
		
		USStatesToCapitalQuestionGenerator stateToCapital = new USStatesToCapitalQuestionGenerator(usStates, questionList);
		questionList = stateToCapital.build();
		
		CapitalToUSStatesQuestionGenerator capitalToState = new CapitalToUSStatesQuestionGenerator(usStates, questionList);
		questionList = capitalToState.build();
		
		StateToBorderingStatesQuestionGenerator stateToBorderingStates = new StateToBorderingStatesQuestionGenerator(usStates, questionList);
		questionList = stateToBorderingStates.build();
		
		BorderingStatesToStateQuestionGenerator borderingStatesToState = new BorderingStatesToStateQuestionGenerator(usStates, questionList);
		questionList = borderingStatesToState.build();
		
		questionList.save();
	}

	private static USStates readUSStatesFile() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(USStates.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			USStates usStates = (USStates) jaxbUnmarshaller.unmarshal(getUSStatesFile());
			return usStates;
		} catch (JAXBException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

	private static File getUSStatesFile() {
		File file = new File("src/main/resources/data/world/geography/US_States.xml");
		return file;
	}
	

}
