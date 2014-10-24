package bram.pobquiz.questiongenerator.stategenerator;

import bram.pobquiz.data.geography.State;
import bram.pobquiz.data.geography.USStates;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class USStatesToCapitalQuestionGenerator {

	private USStates c_usStates;
	private QuestionList c_questionList;
	
	public USStatesToCapitalQuestionGenerator(USStates usStates,
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
		factory.withQuestion("What is the capital of the US State " + state.getName() + "?");
		factory.withAnswer(state.getCapital());
		factory.withCaterorgy("Geography");
		factory.withCaterorgy("US");
		factory.withCaterorgy("US States");
		factory.withCaterorgy("US States to capital");
		Question question = factory.build();
		c_questionList.addQuestion(question);
		
	}
	
}
