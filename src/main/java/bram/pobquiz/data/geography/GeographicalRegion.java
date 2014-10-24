package bram.pobquiz.data.geography;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public abstract class GeographicalRegion<R extends GeographicalRegion<R>> {

	@XmlElement(name = "name")
	private String c_name;
	@XmlElement(name = "capital")
	private String c_capital;
	@XmlElement(name = "neighbour")
	private List<String> c_neighbours;

	
	public String getName() {
		return c_name;
	}
	
	public String getCapital() {
		return c_capital;
	}
	
	public List<String> getNeighbours() {
		return c_neighbours;
	}
	
	public abstract RegionType getRegionType();
	
	@Override
	public String toString() {
		return c_name;
	}
	
}
