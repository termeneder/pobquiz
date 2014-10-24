package bram.pobquiz.data.history;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class President {

	public Integer number;
	public String name;
	public Integer birth;
	public Integer death;
	public String party;
	public Integer beginTerm;
	public Integer endTerm;
	@XmlElement(name = "vice-president")
	public List<String> vicePresidents;
	
	
}
