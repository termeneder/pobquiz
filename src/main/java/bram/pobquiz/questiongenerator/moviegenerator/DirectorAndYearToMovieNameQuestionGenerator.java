package bram.pobquiz.questiongenerator.moviegenerator;

import java.util.ArrayList;
import java.util.List;

import bram.pobquiz.data.movie.Movie;
import bram.pobquiz.data.movie.MovieList;
import bram.pobquiz.question.QuestionFactory;
import bram.pobquiz.question.QuestionList;

public class DirectorAndYearToMovieNameQuestionGenerator {

	private MovieList c_movieList;
	private QuestionList c_questionList;
	
	public DirectorAndYearToMovieNameQuestionGenerator(MovieList movieList,
			QuestionList questionList) {
		c_movieList = movieList;
		c_questionList = questionList;
	}

	public QuestionList build() {

		List<QuestionFactory> factoryList = new ArrayList<QuestionFactory>();
		for (Movie movie: c_movieList) {
			addFactory(movie, factoryList);
		}
		for (QuestionFactory factory : factoryList) {
			c_questionList.addQuestion(factory.build());
		}
		return c_questionList;
	}






	private void addFactory(Movie movie, List<QuestionFactory> factoryList) {
		if (movie.getYear() != null && 0 < movie.getDirectors().size() && movie.getName() != null) {
			String question = createQuestionString(movie);
			QuestionFactory sameFactory = null;
			for (QuestionFactory factory : factoryList) {
				if (factory.getQuestion().equals(question)) {
					sameFactory = factory;
				}
			}
			if (sameFactory == null) {
				QuestionFactory newFactory = new QuestionFactory();
				newFactory.withQuestion(question);
				newFactory.withAnswer(movie.getName());
				newFactory.withCaterorgy("Movie");
				newFactory.withCaterorgy("Director and year to movie");
				factoryList.add(newFactory);
			} else {
				sameFactory.withAnswer(movie.getName());
			}
		}
	}

	private String createQuestionString(Movie movie) {
		String question = "What movie was directed by ";
		for (int i = 0 ; i < movie.getDirectors().size() ; i++) {
			if (i == 0) {
				
			} else if (i + 1 == movie.getDirectors().size()) {
				question += " and ";
			} else {
				question += ", ";
			}
			question += movie.getDirectors().get(i);
		}
		question += " in " + movie.getYear() + "?";
		return question;
	}
	
}
