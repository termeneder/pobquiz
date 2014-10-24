package bram.pobquiz.analyse;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public abstract class RankingAnalysis implements Analysis {

	private final int c_top;
	
	public RankingAnalysis(int top) {
		c_top = top;
	}
	
	@Override
	public String analyse(QuestionList list) {
		QuestionStats[] top = makeTop(list);
		String analysisString = makeAnalysisString(top);
		return analysisString;
	}
	
	private String makeAnalysisString(QuestionStats[] top) {
		String str = "";
		for (QuestionStats stats : top) {
			if (str.length() != 0) {
				str += "\n";
			}
			str += displayValue(stats);
		}
		return getDescription() + "\n\n" + str;
	}

	protected abstract String getDescription();

	private QuestionStats[] makeTop(QuestionList list) {
		QuestionStats[] top = new QuestionStats[c_top];
		for (QuestionStats stats : list) {
			for (int index = c_top - 1 ; index >= 0 ; index--) {
				if (top[index] == null || comesBefore(stats, top[index])) {
					moveDown(top, index);
					top[index] = stats;
				}
			}
		}
		return top;
	}
	
	private void moveDown(QuestionStats[] top, int index) {
		QuestionStats itemAtIndex = top[index];
		if (index < c_top - 1) {
			top[index+1] = itemAtIndex;
		}
		
	}

	protected abstract String displayValue(QuestionStats stats);
	
	protected abstract boolean comesBefore(QuestionStats q1, QuestionStats q2);
	
}
