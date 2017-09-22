package filter.sequential;

import filter.*;

public class CmdPWD extends Cmd {

	CmdPWD(String s) {
		super(s);
		in = false;
		out = true;
	}
	
	public void process(){
		if (prev != null && ((Cmd)prev).isValid == true && ((Cmd)prev).out == true) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
			isValid = false;
			return;
		}
		if (command.split("\\s+").length > 1) {
			System.out.print(Message.INVALID_PARAMETER.with_parameter(command));
			isValid = false;
			return;
		}
		output.offer(SequentialREPL.currentWorkingDirectory);
	 }
	
	protected String processLine(String line){
		return null;
	}
	
	public String toString() {
		return "pwd(" + command + ")";
	}
}
