package bram.pobquiz.quiz.selector;

import java.util.Random;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class WeightedRandomQuestionSelector implements QuestionSelector {

	@Override
	public QuestionStats getQuestion(QuestionList list) {
		while (true) {
			QuestionStats question = getRandomQuestion(list);
			if (weightedSelect(question)) {
				return question;
			}
		}
	}

	private boolean weightedSelect(QuestionStats question) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(question.getTimesTested()+1);
		int cutoff = question.getTimesCorrect();
		return cutoff <= randomNumber;
	}

	private QuestionStats getRandomQuestion(QuestionList list) {
		int listSize = list.size();
		Random rand = new Random();
		int randomIndex = rand.nextInt(listSize);
		return list.get(randomIndex);
	}
	
	

}
