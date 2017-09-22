package filter.sequential;

import filter.*;
import java.io.File;

public class CmdLS extends Cmd {

	CmdLS(String s) {
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
		File dirFolder = new File(SequentialREPL.currentWorkingDirectory);
		if (!dirFolder.exists()){
			System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(command));
			isValid = false;
			return;
		}
		File[] content = dirFolder.listFiles();
		if (content != null && content.length > 0){
			for (File f : content) {
				output.offer(f.getName());
			}
		}
	}
	
	protected String processLine(String line){
		return null;
	}
	
	public String toString() {
		return "ls(" + command + ")";
	}
}
