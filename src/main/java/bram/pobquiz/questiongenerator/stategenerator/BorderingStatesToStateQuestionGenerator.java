package bram.pobquiz.questiongenerator.stategenerator;

import bram.pobquiz.data.geography.State;
import bram.pobquiz.data.geography.USStates;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class BorderingStatesToStateQuestionGenerator {

	private USStates c_usStates;
	private QuestionList c_questionList;
	
	public BorderingStatesToStateQuestionGenerator(USStates usStates,
			QuestionList questionList) {
		c_usStates = usStates;
		c_questionList = questionList;
	}

	public QuestionList build() {

		for (State state : c_usStates) {
			addQuestion(state);
		}
		return c_questionList;
	}



	private void addQuestion(State state) {
		if (state.getNeighbours() == null || state.getNeighbours().size() == 0) {
			return ; // no neighbours, no question
		}
		QuestionFactory factory = new QuestionFactory();
		String borderingStates = "";
		for (int stateIndex = 0 ; stateIndex < state.getNeighbours().size() ; stateIndex++) {
			if (stateIndex == 0) {
				
			} else if (stateIndex == state.getNeighbours().size() - 1) {
				borderingStates += " and ";
			} else {
				borderingStates += ", ";
			}
			borderingStates += state.getNeighbours().get(stateIndex);
		}
		factory.withQuestion("What US State borders on (solely) " + borderingStates + "?");
		factory.withAnswer(state.getName());
		factory.withCaterorgy("Geography");
		factory.withCaterorgy("US");
		factory.withCaterorgy("US States");
		factory.withCaterorgy("Neighbours to state");
		Question question = factory.build();
		c_questionList.addQuestion(question);
		
	}
	
}
