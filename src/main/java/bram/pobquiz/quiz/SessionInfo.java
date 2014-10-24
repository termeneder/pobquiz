package bram.pobquiz.quiz;


public class SessionInfo {

	private int questionsAsked;
	private int questionAnsweredCorrectly;
	private int currentStreak;
	private int longestUpstreak;
	private int longestDownstreak;
	
	public SessionInfo() {
		questionsAsked = 0;
		questionAnsweredCorrectly = 0;
		currentStreak = 0;
		longestUpstreak = 0;
		longestDownstreak = 0;
	}
	
	public void addCorrectAnswer() {
		questionsAsked++;
		questionAnsweredCorrectly++;
		currentStreak = Math.max(1, currentStreak+1);
		longestUpstreak = Math.max(currentStreak, longestUpstreak);
	}
	
	public void addIncorrectAnswer() {
		questionsAsked++;
		currentStreak = Math.min(-1, currentStreak-1);
		longestDownstreak = Math.min(currentStreak, longestDownstreak);
	}
	
	public int getQuestionsAsked() {
		return questionsAsked;
	}
	
	public int getPercentage() {
		if (questionsAsked == 0) {
			return 0;
		}
		double percentage = ((double)questionAnsweredCorrectly/(double)questionsAsked)*100d;
		return (int) percentage;
	}
	
	public int getQuestionAnsweredCorrectly() {
		return questionAnsweredCorrectly;
	}
	
	public int getCurrentStreak() {
		return currentStreak;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "Questions asked: " + questionsAsked;
		str += "\nQuestions answered correctly: " + questionAnsweredCorrectly;
		str += "\nPercentage correct: " + getPercentage() + "%";
		str += "\nLongest upstreak: " + longestUpstreak;
		str += "\nLongest downstreak: " + longestDownstreak;
		str += "\nCurrent streak: " + currentStreak;
		return str;
	}
	
}
