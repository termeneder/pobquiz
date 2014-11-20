package bram.pobquiz.question;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "QuestionList")
public class QuestionList implements Iterable<QuestionStats> {
	
	@XmlElement(name = "QuestionStats")
	private List<QuestionStats> c_questionStatList;

	public QuestionList() {
		c_questionStatList = new ArrayList<QuestionStats>();
	}
	
	public void addQuestion(Question question) {
		if (!isAlreadyAdded(question)) {
			QuestionStats newQs = new QuestionStats(question, 0, 0);
			c_questionStatList.add(newQs);
		}
	}
	
	public void removeQuestion(QuestionStats question) {
		c_questionStatList.remove(question);
		
	}
	
	private boolean isAlreadyAdded(Question question) {
		boolean isAlreadyAdded = false;
		for (QuestionStats qs : c_questionStatList) {
			if (question.equals(qs.getQuestion())) {
				isAlreadyAdded = true;
			}
		}
		return isAlreadyAdded;
	}
	
	public int size() {
		return c_questionStatList.size();
		
	}
	
	public QuestionStats get(int index) {
		return c_questionStatList.get(index);
	}
	
	public void save() {
		writeQuestionFile(this);
	}
	
	@Override
	public Iterator<QuestionStats> iterator() {
		return c_questionStatList.iterator();
	}
	
	public static void setSource(String location) {
		INSTANCE = null;
		SOURCE = location;
	}
	
	private static QuestionList INSTANCE;
	private static String SOURCE;
	public static QuestionList getInstance() {
		if (INSTANCE == null) {
			INSTANCE = readQuestionFile();
		}
		return INSTANCE;
	}
	
	private static QuestionList readQuestionFile() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(QuestionList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			QuestionList questionList = (QuestionList) jaxbUnmarshaller.unmarshal(getQuestionFile());
			return questionList;
		} catch (JAXBException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void writeQuestionFile(QuestionList questionList) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(QuestionList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.marshal(questionList, getQuestionFile());
			
		} catch (JAXBException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private static File getQuestionFile() {
		if (SOURCE == null) {
			throw new RuntimeException("No source set.");
		}
		File file = new File(SOURCE);
		return file;
	}








}
