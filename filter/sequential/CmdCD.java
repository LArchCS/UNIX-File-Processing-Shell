package filter.sequential;

import java.io.*;
import filter.*;

public class CmdCD extends Cmd {

	CmdCD(String s) {
		super(s);
		in = false;
		out = false;
	}
	
	public void process() {
		if (prev != null && ((Cmd)prev).isValid == true && ((Cmd)prev).out == true) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
			isValid = false;
			return;
		}
		if (next != null && ((Cmd)next).isValid == true && ((Cmd)next).in == true) {
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(command));
			isValid = false;
			return;
		}
		String[] split = command.split("\\s+");
		if (split.length <= 1 && command.length() <= 2) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cd"));
			isValid = false;
			return;
		}
		String dir = split[1].trim();
		String absoluteDir = SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + dir;
		File file = new File(absoluteDir);
		if (dir.equals("..")) {
			int loc = SequentialREPL.currentWorkingDirectory.lastIndexOf(Filter.FILE_SEPARATOR);
			if (loc > 0){
				SequentialREPL.currentWorkingDirectory = SequentialREPL.currentWorkingDirectory.substring(0, loc);
			}
		} else if (dir.equals(".")) {
			// this means what??
			return;
		} else if (!file.exists()) {
			System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(command));
			isValid = false;
		} else {
			SequentialREPL.currentWorkingDirectory = absoluteDir;
		}
	}
	
	protected String processLine(String line) {
		return null;
	}
	
	public String toString() {
		return "cd(" + command + ")";
	}
}
