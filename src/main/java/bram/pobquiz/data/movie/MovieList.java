package bram.pobquiz.data.movie;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Movies")
public class MovieList implements Iterable<Movie>{

	@XmlElement(name = "Movie")
	private List<Movie> c_movieList;

	@Override
	public Iterator<Movie> iterator() {
		return c_movieList.iterator();
	}
	
	
}
