package bram.pobquiz.data.history;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UKMonarchs")
public class UKMonarchs implements Iterable<Monarch> {

	@XmlElement(name = "monarch")
	private List<Monarch> c_monarchs;
	
	@Override
	public Iterator<Monarch> iterator() {
		return c_monarchs.iterator();
	}

	public int size() {
		return c_monarchs.size();
	}

	public Monarch get(int i) {
		return c_monarchs.get(i);
	}

}
