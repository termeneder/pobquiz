package bram.pobquiz.questiongenerator.stategenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bram.pobquiz.data.geography.State;
import bram.pobquiz.data.geography.USStates;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class StatesByStartingLettersQuestionGenerator {

	private USStates c_usStates;
	private QuestionList c_questionList;
	private Map<String, List<State>> c_startingLetterMap;
	
	public StatesByStartingLettersQuestionGenerator(USStates usStates,
			QuestionList questionList) {
		c_usStates = usStates;
		c_questionList = questionList;
		c_startingLetterMap = new HashMap<String, List<State>>();
	}

	public QuestionList build() {
		for (State state : c_usStates) {
			addStateToMap(state);
		}
		for (String startingLetter : c_startingLetterMap.keySet()) {
			addQuestion(startingLetter);
		}
		return c_questionList;
	}



	private void addStateToMap(State state) {
		String name = state.getName();
		String startingLetter = name.substring(0, 1);
		if (!c_startingLetterMap.containsKey(startingLetter)) {
			List<State> stateList = new ArrayList<State>();
			c_startingLetterMap.put(startingLetter, stateList);
		}
		List<State> stateStartingWithList = c_startingLetterMap.get(startingLetter);
		stateStartingWithList.add(state);
	}

	private void addQuestion(String startingLetter) {
		QuestionFactory factory = new QuestionFactory();
		factory.withQuestion("Name all US states starting with '"+startingLetter+"'");
		for (State state : c_startingLetterMap.get(startingLetter)) {
			factory.withAnswer(state.getName());
		}
		factory.withCaterorgy("Geography");
		factory.withCaterorgy("US");
		factory.withCaterorgy("US States");
		factory.withCaterorgy("US States by starting letter");
		Question question = factory.build();
		c_questionList.addQuestion(question);
		
	}
	
}
