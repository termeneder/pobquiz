package bram.pobquiz.questiongenerator.stategenerator;

import bram.pobquiz.data.geography.State;
import bram.pobquiz.data.geography.USStates;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class CapitalToUSStatesQuestionGenerator {

	private USStates c_usStates;
	private QuestionList c_questionList;
	
	public CapitalToUSStatesQuestionGenerator(USStates usStates,
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
		QuestionFactory factory = new QuestionFactory();
		factory.withQuestion("What US State has the capital " + state.getCapital() + "?");
		factory.withAnswer(state.getName());
		factory.withCaterorgy("Geography");
		factory.withCaterorgy("US");
		factory.withCaterorgy("US States");
		factory.withCaterorgy("Capital to US State");
		Question question = factory.build();
		c_questionList.addQuestion(question);
		
	}
	
}
