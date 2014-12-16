package bram.pobquiz.data.geography;

import javax.xml.bind.annotation.XmlElement;

public class Country extends GeographicalRegion<Country>{
	@XmlElement(name = "continent")
	private String continent;
	
	@Override
	public RegionType getRegionType() {
		return RegionType.COUNTRY;
	}

	public String getContinent() {
		return continent;
	}
	
	
	
}
