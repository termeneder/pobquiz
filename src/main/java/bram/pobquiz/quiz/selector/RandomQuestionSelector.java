package bram.pobquiz.quiz.selector;

import java.util.Random;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class RandomQuestionSelector implements QuestionSelector {

	@Override
	public QuestionStats getQuestion(QuestionList list) {
		int listSize = list.size();
		Random rand = new Random();
		int randomIndex = rand.nextInt(listSize);
		return list.get(randomIndex);
	}


	

}
