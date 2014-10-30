package bram.pobquiz.analyse;

import org.apache.commons.lang3.StringUtils;

import bram.pobquiz.question.QuestionStats;

public class WorstRatioQuestionsAnalysis extends RankingAnalysis {

	public WorstRatioQuestionsAnalysis(int top) {
		super(top);
	}

	@Override
	protected String getDescription() {
		
		return "Questions answered correctly with worst ratio";
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
		if (q1.getTimesTested() == 0) {
			return false;
		} else if (q2.getTimesTested() == 0) {
			return true;
		}
		double q1Ratio = (double)q1.getTimesCorrect()/(double)q1.getTimesTested();
		double q2Ratio = (double)q2.getTimesCorrect()/(double)q2.getTimesTested();
		if (q1Ratio < q2Ratio) {
			return true;
		} else if (q1Ratio < q2Ratio) {
			return false;
		} else {
			return q1.getTimesTested() > q2.getTimesTested();
		}
	}
	




	
}
