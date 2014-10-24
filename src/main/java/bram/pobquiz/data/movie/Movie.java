package bram.pobquiz.data.movie;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Movie {

	@XmlElement(name = "name")
	private String c_name;
	@XmlElement(name = "year")
	private Integer c_year;
	@XmlElement(name = "director")
	private List<String> c_directorList;
	@XmlElement(name = "actor")
	private List<String> c_actorList;
	
	public String getName() {
		return c_name;
	}
	
	public Integer getYear() {
		return c_year;
	}
}
