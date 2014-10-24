package bram.pobquiz.data.geography;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "USStates")
public class USStates implements Iterable<State>{

	@XmlElement(name = "state")
	private List<State> stateList;

	@Override
	public Iterator<State> iterator() {
		
		return stateList.iterator();
	}
	
}
