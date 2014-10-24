package bram.pobquiz.data.history;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "USPresidents")
public class USPresidents implements Iterable<President> {

	@XmlElement(name = "president")
	private List<President> c_presidents;
	
	@Override
	public Iterator<President> iterator() {
		return c_presidents.iterator();
	}

	public int size() {
		return c_presidents.size();
	}

	public President get(int i) {
		return c_presidents.get(i);
	}

}
