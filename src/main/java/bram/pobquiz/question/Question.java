package bram.pobquiz.question;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Question {

	@XmlElement (name = "Question")
	private String c_question;
	@XmlElement (name = "Answer")
	private List<String> c_answerList;
	@XmlElement (name = "Category")
	private List<String> c_categoryList;
	
	Question() {
		
	}
	
	void setQuestion(String question) {
		c_question = question;
	}
	
	void setAnswerList(List<String> answerList) {
		c_answerList = answerList;
	}
	
	void setCategoryList(List<String> categoryList) {
		c_categoryList = categoryList;
	}
	
	public String getQuestion() {
		return c_question;
	}
	
	public List<String> getAnswers() {
		return c_answerList;
	}
	
	public boolean isCorrect(List<String> givenAnswers) {
		return getMissedAnswers(givenAnswers).size() == 0 && 
				getNonOccurringAnswers(givenAnswers).size() == 0;
	}
	
	public List<String> getMissedAnswers(List<String> givenAnswers) {
		List<String> missedAnswers = new ArrayList<String>();
		for (String answer : c_answerList) {
			boolean foundAnswer = false;
			for (String givenAnswer : givenAnswers) {
				if (isTheSameAnswer(answer, givenAnswer)) {
					foundAnswer = true;
					break;
				}
			}
			if (!foundAnswer) {
				missedAnswers.add(answer);
			}
		}
		return missedAnswers;
	}


	public List<String> getNonOccurringAnswers(List<String> givenAnswers) {
		List<String> nonOccurringAnswers = new ArrayList<String>();
		for (String givenAnswer : givenAnswers) {
			boolean foundAnswer = false;
			for (String answer : c_answerList) {
				if (isTheSameAnswer(answer, givenAnswer)) {
					foundAnswer = true;
					break;
				}
			}
			if (!foundAnswer) {
				nonOccurringAnswers.add(givenAnswer);
			}
		}
		return nonOccurringAnswers;
	}
	
	
	private boolean isTheSameAnswer(String answerA, String answerB) {
		return answerA.toLowerCase().equals(answerB.toLowerCase());
	}
	
	public boolean isSingletonAnswer() {
		return c_answerList.size() == 1;
	}
	
	public List<String> getCategories() {
		return c_categoryList;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Question)) {
			return false;
		}
		Question q = (Question) o;
		return c_question.equals(q.c_question);
	}
	
	@Override
	public int hashCode() {
		return c_question.hashCode();
	}
	
}
