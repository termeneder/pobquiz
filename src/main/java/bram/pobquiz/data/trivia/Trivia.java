package bram.pobquiz.data.trivia;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bram.pobquiz.question.Question;

@XmlRootElement(name = "Trivia")
public class Trivia implements Iterable<Question>{

	@XmlElement(name = "Question")
	private List<Question> questionList;

	@Override
	public Iterator<Question> iterator() {
		return questionList.iterator();
	}
	
}
