package filter.sequential;

import java.util.*;

class Cmd extends SequentialFilter{
	String command;
	Boolean in;
	Boolean out;
	Boolean isValid;
	
	Cmd(String cmd) {
		this.command = cmd;
		input = new LinkedList<String>();
		output = new LinkedList<String>();
		isValid = true;
		in = true;
		out = true;
	}
	
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return command;
	}
}
