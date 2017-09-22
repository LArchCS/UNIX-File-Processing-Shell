package filter.sequential;

import filter.*;

public class CmdNotFound extends Cmd {
	
	public CmdNotFound(String s) {
		super(s);
		this.in = false;
		this.out = false;
		isValid = false;
	}
	
	protected  String processLine(String line){
		String s = Message.COMMAND_NOT_FOUND.with_parameter(this.command);
		return s;
	}
	
	public String toString() {
		return "notFound(" + command + ")";
	}
}
