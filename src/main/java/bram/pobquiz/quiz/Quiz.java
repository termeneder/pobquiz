package bram.pobquiz.quiz;

import java.util.ArrayList;
import java.util.List;

import bram.pobquiz.inputter.Inputter;
import bram.pobquiz.question.QuestionList;
import bram.pobquiz.question.QuestionStats;
import bram.pobquiz.quiz.questionHandler.AskedQuestionHandler;
import bram.pobquiz.quiz.selector.QuestionSelector;

public class Quiz {

	private QuestionList c_list;
	private QuestionSelector c_selector;
	private Inputter c_inputter;
	private SessionInfo c_sessionInfo;
	
	private boolean c_saveListAfterQuestion; 
	
	private List<AskedQuestionHandler> c_askedQuestionHandler; 
	
	public Quiz(QuestionList list, QuestionSelector selector, Inputter inputter) {
		c_list = list;
		c_selector = selector;
		c_inputter = inputter;
		c_sessionInfo = new SessionInfo();
		c_saveListAfterQuestion = true;
		c_askedQuestionHandler = new ArrayList<AskedQuestionHandler>();
	}
	
	public void askQuestion() {
		QuestionStats question = selectQuestion();
		printQuestion(question);
		List<String> answer = getAnswer();
		handleAnswer(question, answer);
		if (c_saveListAfterQuestion) { // TODO Move to questionhandler
			c_list.save(); 
		}
		
	}

	public void addAskedQuestionHandler(AskedQuestionHandler handler) {
		c_askedQuestionHandler.add(handler);
	}
	
	public void setSaveListAfterQuestion(boolean newSetting) {
		c_saveListAfterQuestion = newSetting;
	}

	private void printQuestion(QuestionStats question) {
		System.out.println(question.getQuestion().getQuestion());// TODO MOVE TO OUTPUTTER
	}

	private QuestionStats selectQuestion() {
		return c_selector.getQuestion(c_list);
	}

	private List<String> getAnswer() {
		return c_inputter.getInput();
	}
	
	
	private void handleAnswer(QuestionStats question, List<String> answer) {
		if (question.getQuestion().isCorrect(answer)) {
			System.out.println("Correct!");// TODO MOVE TO OUTPUTTER
			question.answeredCorrectly();
			c_sessionInfo.addCorrectAnswer();
			for (AskedQuestionHandler handler : c_askedQuestionHandler) {
				handler.handleCorrectQuestion(question);
			}
		} else {
			handleIncorrectAnswer(question, answer);
			for (AskedQuestionHandler handler : c_askedQuestionHandler) {
				handler.handleIncorrectQuestion(question);
			}
		}
		System.out.println("Question statistics: " + question.getTimesCorrect() + "/" + question.getTimesTested() + "\t\t\t\t\t(Session statistics: " 
					+ c_sessionInfo.getQuestionAnsweredCorrectly() + "/" + c_sessionInfo.getQuestionsAsked() + ")");
	}
	
	private void handleIncorrectAnswer(QuestionStats question, List<String> answer) {
		System.out.println("Incorrect!");// TODO MOVE TO OUTPUTTER
		if (question.getQuestion().isSingletonAnswer()){
			System.out.println("Should be: " + question.getQuestion().getAnswers().get(0));// TODO MOVE TO OUTPUTTER
		} else {
			if (question.getQuestion().getMissedAnswers(answer).size() > 0) {
				System.out.println("You missed:");// TODO MOVE TO OUTPUTTER
				for (String missedAnswers : question.getQuestion().getMissedAnswers(answer)) {
					System.out.println(" - " + missedAnswers);// TODO MOVE TO OUTPUTTER
				}
			}
			if (question.getQuestion().getNonOccurringAnswers(answer).size() > 0) {
				System.out.println("You incorrectly gave:");// TODO MOVE TO OUTPUTTER
				for (String nonOccurringAnswer : question.getQuestion().getNonOccurringAnswers(answer)) {
					System.out.println(" - " + nonOccurringAnswer);// TODO MOVE TO OUTPUTTER
				}
			}
		}
		question.answeredIncorrectly();
		
		c_sessionInfo.addIncorrectAnswer();
	}

	public SessionInfo getSessionInfo() {
		return c_sessionInfo;
	}
	
	public QuestionList getQuestionList() {
		return c_list;
	}
}
