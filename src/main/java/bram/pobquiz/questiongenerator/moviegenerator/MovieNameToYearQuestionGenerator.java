package bram.pobquiz.questiongenerator.moviegenerator;

import bram.pobquiz.data.movie.Movie;
import bram.pobquiz.data.movie.MovieList;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class MovieNameToYearQuestionGenerator {

	private MovieList c_movieList;
	private QuestionList c_questionList;
	
	public MovieNameToYearQuestionGenerator(MovieList movieList,
			QuestionList questionList) {
		c_movieList = movieList;
		c_questionList = questionList;
	}

	public QuestionList build() {

		for (Movie movie : c_movieList) {
			addQuestion(movie);
		}
		return c_questionList;
	}



	private void addQuestion(Movie movie) {
		QuestionFactory factory = new QuestionFactory();
		factory.withQuestion("In what year was the movie '" + movie.getName() + "' released?");
		factory.withAnswer(Integer.toString(movie.getYear()));
		factory.withCaterorgy("Movie");
		factory.withCaterorgy("Movie to year");

		Question question = factory.build();
		c_questionList.addQuestion(question);
		
	}
	
}
