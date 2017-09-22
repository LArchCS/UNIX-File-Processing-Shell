package filter.sequential;

import filter.*;
import java.io.*;


public class CmdRED extends Cmd {
	File f;

	CmdRED(String s) {
		super(s);
		in = true;
		out = false;
	}
	
	public void process() {
		if (prev == null || ((Cmd)prev).isValid == false) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("> " + command));
			isValid = false;
			return;
		}
		if (next != null && ((Cmd)next).isValid == true && ((Cmd)next).in == true) {
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter("> " + command));
			isValid = false;
			return;
		}
		String[] split = command.split("\\s+");
		if (split.length < 1 || split[0].equals("")) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter("> " + command));
			isValid = false;
			return;
		}
		// too many parameter
		if (split.length > 2) {
			System.out.print(Message.INVALID_PARAMETER.with_parameter("> " + command));
			isValid = false;
			return;
		}
		// initialize file
		String dir = split[0].trim();
		String absoluteDir = SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + dir;
		f = new File(absoluteDir);
		// print file
		try {
			f.createNewFile();
			PrintStream printFile = new PrintStream(f);
			for (String line : input) {
				printFile.println(line);
			}
			printFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected String processLine(String line){
		return null;
	}
	
	public String toString() {
		return ">(" + command + ")";
	}
}
