package bram.pobquiz.questiongenerator.moviegenerator;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bram.pobquiz.data.movie.MovieList;
import bram.pobquiz.question.QuestionList;

public class MovieQuestionGenerator {

	public static void main(String[] args) {
		MovieList movieList = readMovieListFile();
		QuestionList questionList = QuestionList.getInstance();
		
		MovieNameToYearQuestionGenerator movieNameToYear = new MovieNameToYearQuestionGenerator(movieList, questionList);
		questionList = movieNameToYear.build();

		MovieNameToDirectorQuestionGenerator movieNameToDirector = new MovieNameToDirectorQuestionGenerator(movieList, questionList);
		questionList = movieNameToDirector.build();
		
		DirectorAndYearToMovieNameQuestionGenerator directorAndYearToMovie = new DirectorAndYearToMovieNameQuestionGenerator(movieList, questionList);
		questionList = directorAndYearToMovie.build();
		
		questionList.save();
	}

	private static MovieList readMovieListFile() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MovieList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MovieList movieList = (MovieList) jaxbUnmarshaller.unmarshal(getMoviesFile());
			return movieList;
		} catch (JAXBException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

	private static File getMoviesFile() {
		File file = new File("src/main/resources/data/world/movies/movies.xml");
		return file;
	}
	

}
