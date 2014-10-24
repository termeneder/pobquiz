package bram.pobquiz.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CLIInputter implements Inputter {

	public List<String> getInput() {
		List<String> input = new ArrayList<String>();
		while(true) {
			String line = getLine();
			if (line.length() > 0) {
				input.add(line);
			} else {
				return input;		
			}
		}
		
	}
	
	public String getLine() {
		System.out.print("> ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try {
			input = br.readLine();
		} catch (IOException e) {
			System.out.println("WHOAAAA, AN ERROR OCCURRED AND YOU DID NOT WRITE SOMETHING FOR IT");
			throw new RuntimeException(e);
		}
		return input;
	}
	
}
