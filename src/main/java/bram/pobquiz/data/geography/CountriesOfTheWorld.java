package bram.pobquiz.data.geography;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "countries")
public class CountriesOfTheWorld implements Iterable<Country>{

	@XmlElement(name = "country")
	private List<Country> countryList;

	@Override
	public Iterator<Country> iterator() {
		
		return countryList.iterator();
	}
}

	

