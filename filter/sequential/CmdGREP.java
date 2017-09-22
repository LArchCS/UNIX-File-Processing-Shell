package filter.sequential;

import filter.*;

public class CmdGREP extends Cmd {
	
	private String argument;

	CmdGREP(String s) {
		super(s);
		argument = null;
		in = true;
		out = true;
	}
	
	public void process() {
		if (prev != null && ((Cmd)prev).isValid == false) {
			isValid = false;
			return;
		}
		if (prev == null || ((Cmd)prev).isValid == false || ((Cmd)prev).out == false) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(command));
			isValid = false;
			return;
		}
		if (command.length() <= 4) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
			isValid = false;
			return;
		}
		this.argument = command.substring(5).trim();
		while (!input.isEmpty()) {
			String line = input.poll();
			processLine(line);
		}
	}

	protected String processLine(String line){
		if (line.contains(argument)){
			output.offer(line);
			return line;
		}
		return null;
	}
	
	public String toString() {
		return "grep(" + command + ")";
	}
}