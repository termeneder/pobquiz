package bram.pobquiz.analyse;

import bram.pobquiz.analyse.DistributionAnalysis.Group;
import bram.pobquiz.question.QuestionList;

public class Analyser {

	private final QuestionList c_list;
	
	private final Analysis[] c_analysisArray = {
			new GeneralInfoAnalysis(),
			new LearnedQuestionAnalysis(10,80),
			new StreakAnalysis(5),
			new DistributionAnalysis(Group.ASKED),
			new DistributionAnalysis(Group.CORRECT),
			new DistributionAnalysis(Group.SALDO),
			new DistributionAnalysis(Group.STREAK),
			new HardestQuestionAnalysis(5),
			new MostCommonQuestionsAnalysis(3),
			new MostCorrectQuestionsAnalysis(3),
			new MostIncorrectQuestionsAnalysis(3),
			new BestRatioQuestionsAnalysis(3),
			new WorstRatioQuestionsAnalysis(3)
	};
	
	public Analyser(QuestionList list) {
		c_list = list;
	}

	public String getAnalysis() {
		String str = "";
		for (Analysis analysis : c_analysisArray) {
			str += analysis.analyse(c_list);
			str += "\n__________________________________________________________________\n\n";
		}
		return str;
	}

}
