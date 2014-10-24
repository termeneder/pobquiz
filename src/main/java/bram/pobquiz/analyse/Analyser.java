package bram.pobquiz.analyse;

import bram.pobquiz.question.QuestionList;

public class Analyser {

	private final QuestionList c_list;
	
	private final Analysis[] c_analysisArray = {
			new GeneralInfoAnalysis(),
			new LearnedQuestionAnalysis(10,100),
			new HardestQuestionAnalysis(3),
			new MostCommonQuestionsAnalysis(3),
			new MostCorrectQuestionsAnalysis(3),
			new MostIncorrectQuestionsAnalysis(3),
			new BestRatioQuestionsAnalysis(3)
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
