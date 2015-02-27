package bram.pobquiz.questiongenerator.moviegenerator;

import bram.pobquiz.data.movie.Movie;
import bram.pobquiz.data.movie.MovieList;
import bram.pobquiz.question.Question;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class MovieNameToDirectorQuestionGenerator {

	private MovieList c_movieList;
	private QuestionList c_questionList;
	
	public MovieNameToDirectorQuestionGenerator(MovieList movieList,
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
		if (movie.getDirectors().size() > 0) {
			QuestionFactory factory = new QuestionFactory();
			factory.withQuestion("Who directed the movie '" + movie.getName() + "'?");
			for (String director : movie.getDirectors()) {
				factory.withAnswer(director);
			}
			factory.withCaterorgy("Movie");
			factory.withCaterorgy("Movie to director");
	
			Question question = factory.build();
			c_questionList.addQuestion(question);
		}
		
	}
	
}
