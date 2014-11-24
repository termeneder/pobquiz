package bram.pobquiz.question;

import javax.xml.bind.annotation.XmlElement;

public class QuestionStats {

	@XmlElement (name = "Question")
	private Question c_question;
	@XmlElement (name = "TimesTested")
	private int c_timesTested;
	@XmlElement (name = "TimesCorrect")
	private int c_timesCorrect;
	@XmlElement (name = "Streak")
	private int c_streak;
	
	public QuestionStats(Question question, int timesTested, int timesCorrect, int streak) {
		c_question = question;
		c_timesTested = timesTested;
		c_timesCorrect = timesCorrect;
		c_streak = streak;
	}
	
	public QuestionStats() {
		
	}

	public Question getQuestion() {
		return c_question;
	}
	
	public int getTimesTested() {
		return c_timesTested;
	}
	
	public int getTimesCorrect() {
		return c_timesCorrect;
	}
	
	public int getTimesIncorrect() {
		return getTimesTested() - getTimesCorrect();
	}
	
	public int getSaldo() {
		return getTimesCorrect() - getTimesIncorrect();
	}
	
	public void answeredCorrectly() {
		c_timesCorrect++;
		c_timesTested++;
		c_streak = Math.max(c_streak + 1, 1);
	}
	
	public void answeredIncorrectly() {
		c_timesTested++;
		c_streak = Math.min(c_streak - 1, -1);
	}
	
	public int getStreak() {
		return c_streak;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof QuestionStats)) {
			return false;
		}
		QuestionStats q = (QuestionStats) o;
		return c_question.equals(q.c_question);
	}
	
	@Override
	public int hashCode() {
		return c_question.hashCode();
	}


}
