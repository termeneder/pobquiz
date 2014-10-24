package bram.pobquiz.question;

import java.util.ArrayList;
import java.util.List;

public class QuestionFactory {

	private String c_question;
	private List<String> c_answerList;
	private List<String> c_categoryList;
	
	public QuestionFactory() {
		c_answerList = new ArrayList<String>();
		c_categoryList = new ArrayList<String>();
	}
	
	public QuestionFactory withQuestion(String question) {
		c_question = question;
		return this;
	}
	
	public QuestionFactory withAnswer(String answer) {
		c_answerList.add(answer);
		return this;
	}
	
	public QuestionFactory withCaterorgy(String category) {
		c_categoryList.add(category);
		return this;
	}
	
	public Question build() {
		Question question = new Question();
		question.setQuestion(c_question);
		question.setAnswerList(c_answerList);
		question.setCategoryList(c_categoryList);
		return question;
	}

	public Object getQuestion() {
		return c_question;
	}
	
}
