package bram.pobquiz.quiz.selector;

import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;

public class ExcludeCategorySelector implements QuestionSelector {

	private QuestionSelector c_baseSelector;
	private String[] c_categories;
	
	public ExcludeCategorySelector(QuestionSelector baseSelector) {
		c_baseSelector = baseSelector;
		c_categories = new String[0];
	}
	
	public void setCategoriesToExclude(String ... categories) {
		c_categories = categories;
	}
	
	@Override
	public QuestionStats getQuestion(QuestionList list) {
		while (true) {
			QuestionStats question = c_baseSelector.getQuestion(list);
			if (!containExcludedCategory(question)) { 
				return question;
			}
		}
		
	}

	private boolean containExcludedCategory(QuestionStats stats) {
		for (String excludedCategory : c_categories) {
			if (stats.getQuestion().getCategories().contains(excludedCategory)) {
				return true;
			}
		}
		return false;
	}

	
	
}
