package bram.pobquiz;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import bram.pobquiz.analyse.Analyser;
import bram.pobquiz.question.QuestionList;

public class RunAnalyser {

	public static void main(String[] args) throws CmdLineException {
		Config configs = new Config(args);
		System.out.println("Analyser\n");
		QuestionList.setSource(configs.filename);
		QuestionList list = QuestionList.getInstance();
		Analyser analyser = new Analyser(list);
		
		String analysis = analyser.getAnalysis();
		System.out.println(analysis);
	}
	
	private static class Config {

		@Option(name="-file")
		String filename;

		Config(String[] args) throws CmdLineException {
			CmdLineParser parser = new CmdLineParser(this);
			parser.parseArgument(args);
		}
	}
}