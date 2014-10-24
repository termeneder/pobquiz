package bram.pobquiz.questiongenerator.stategenerator;

import bram.pobquiz.data.geography.State;
import bram.pobquiz.data.geography.USStates;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class StateToBorderingStatesQuestionGenerator {

	private USStates c_usStates;
	private QuestionList c_questionList;
	
	public StateToBorderingStatesQuestionGenerator(USStates usStates,
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
		factory.withQuestion("What US States border on " + state.getName() + "?");
		for (String borderState : state.getNeighbours()) {
			factory.withAnswer(borderState);
		}
		factory.withCaterorgy("Geography");
		factory.withCaterorgy("US");
		factory.withCaterorgy("US States");
		factory.withCaterorgy("State to neighbours");
		Question question = factory.build();
		c_questionList.addQuestion(question);
		
	}
	
}
