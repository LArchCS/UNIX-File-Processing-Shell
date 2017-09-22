package filter.sequential;

import java.util.*;
import filter.Message;

public class CmdUNIQ extends Cmd {
	
	private Set<String> seen;

	CmdUNIQ(String s) {
		super(s);
		in = true;
		out = true;
		seen = new HashSet<String>();
	}
	
	public void process() {
		if (command.length() > 4) {
			System.out.print(Message.INVALID_PARAMETER.with_parameter(command));
			isValid = false;
			return;
		}
		if (prev == null || ((Cmd)prev).isValid == false || ((Cmd)prev).out == false) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(command));
			isValid = false;
			return;
		}
		while (!input.isEmpty()) {
			String line = input.poll();
			processLine(line);
		}
	}
	
	protected String processLine(String line){
		if (seen.contains(line)){
			return line;
		}
		output.offer(line);
		seen.add(line);
		return null;
	}
	
	public String toString() {
		return "uniq(" + command + ")";
	}
}
