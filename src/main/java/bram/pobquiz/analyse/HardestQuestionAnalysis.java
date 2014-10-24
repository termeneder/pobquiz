package bram.pobquiz.analyse;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.question.QuestionStats;

public class HardestQuestionAnalysis extends RankingAnalysis {
	
	public HardestQuestionAnalysis(int top) {
		super(top);
	}

	@Override
	protected String displayValue(QuestionStats stats) {
		String str = StringUtils.rightPad(stats.getTimesCorrect() + "/" + stats.getTimesTested(), 10);
		str += stats.getQuestion().getQuestion() + "\n";
		str += StringUtils.repeat(' ', 10);
		String answerString = "";
		for (String answer : stats.getQuestion().getAnswers()) {
			if (answerString.length() > 0) {
				answerString += ", ";
			}
			answerString += answer;
		}
		str += "(" + answerString + ")";
		return str;
	}
	
	@Override
	protected boolean comesBefore(QuestionStats q1, QuestionStats q2) {
		if (value(q1) < value(q2)) {
			return true;
		}
		return false;
	}
	
	private int value(QuestionStats q) {
		return q.getTimesCorrect() - (q.getTimesTested()-q.getTimesCorrect());
	}

	@Override
	protected String getDescription() {
		return "Hardest question";
	}






	
	
}
