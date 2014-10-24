package bram.pobquiz.analyse;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class GeneralInfoAnalysis implements Analysis {

	private static final int LEFT_PAD_DISTANCE = 50;
	
	@Override
	public String analyse(QuestionList list) {
		String str = "General Info: \n\n";
		
		str += StringUtils.leftPad("Amount of questions: ", LEFT_PAD_DISTANCE) + " " + getAmountOfQuestions(list) + "\n";
		str += StringUtils.leftPad("Amount of times answered: ", LEFT_PAD_DISTANCE) + " "  + getAmountOfAnswersGiven(list) + "\n";
		str += StringUtils.leftPad("Amount of times answered correctly: ", LEFT_PAD_DISTANCE) + " "  + getAmountOfCorrectAnswersGiven(list) + "\n";
		str += StringUtils.leftPad("Percentage of correct answers: ", LEFT_PAD_DISTANCE) + " "  + getPercentageOfCorrectAnswers(list) + "\n";
		str += StringUtils.leftPad("Amount of questions never asked: ", LEFT_PAD_DISTANCE) + " "  + getAmountOfQuestionsNeverAsked(list) + "\n";
		str += StringUtils.leftPad("Amount of questions never answered correctly: ", LEFT_PAD_DISTANCE) + " " + getAmountOfQuestionsNeverAnsweredCorrectly(list) + "\n";
		return str;
	}

	private int getAmountOfQuestions(QuestionList list) {
		return list.size();
	}

	private int getAmountOfAnswersGiven(QuestionList list) {
		int answersGiven = 0;
		for (QuestionStats stats : list) {
			answersGiven += stats.getTimesTested();
		}
		return answersGiven;
	}

	private int getAmountOfCorrectAnswersGiven(QuestionList list) {
		int correctAnswersGiven = 0;
		for (QuestionStats stats : list) {
			correctAnswersGiven += stats.getTimesCorrect();
		}
		return correctAnswersGiven;
	}

	private String getPercentageOfCorrectAnswers(QuestionList list) {
		int percentage = (getAmountOfCorrectAnswersGiven(list)*100)/(getAmountOfAnswersGiven(list));
		return percentage + "%";
	}

	private int getAmountOfQuestionsNeverAsked(QuestionList list) {
		int haveNeverBeenAsked = 0;
		for (QuestionStats stats : list) {
			if (stats.getTimesTested() == 0) {
				haveNeverBeenAsked++;
			}
		}
		return haveNeverBeenAsked;
	}

	private int getAmountOfQuestionsNeverAnsweredCorrectly(QuestionList list) {
		int haveNeverBeenAnsweredCorrectly = 0;
		for (QuestionStats stats : list) {
			if (stats.getTimesCorrect() == 0) {
				haveNeverBeenAnsweredCorrectly++;
			}
		}
		return haveNeverBeenAnsweredCorrectly;
	}

	
	
}
