package bram.pobquiz;

import bram.pobquiz.analyse.Analyser;
import bram.pobquiz.question.QuestionList;

public class RunAnalyser {

	public static void main(String[] args) {
		System.out.println("Analyser\n");
		QuestionList list = QuestionList.getInstance();
		Analyser analyser = new Analyser(list);
		
		String analysis = analyser.getAnalysis();
		System.out.println(analysis);
	}
	
}