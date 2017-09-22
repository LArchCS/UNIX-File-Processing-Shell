package filter.sequential;

import filter.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CmdCAT extends Cmd {
	
	ArrayList<File> files;
	
	CmdCAT(String s) {
		super(s);
		in = false;
		out = true;
		files = new ArrayList<File>();
	}
	
	@Override
	public void process() {
		if (this.prev != null && ((Cmd)this.prev).isValid == true && ((Cmd)this.prev).out == true) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
			isValid = false;
			return;
		}
		String[] split = command.split("\\s+");
		if (split.length <= 1) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
			isValid = false;
			return;
		}
		for (int i = 1; i < split.length; i++) {
			String dir = split[i].trim();
			String absoluteDir = SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + dir;
			File f = new File(absoluteDir);
			if (!f.exists()) {
				System.out.print(Message.FILE_NOT_FOUND.with_parameter(command));
				isValid = false;
				return;
			}
			files.add(f);
		}
		for (File f : files) {
			try {
				Scanner scan = new Scanner(f);
				while (scan.hasNextLine()) {
					processLine(scan.nextLine());
				}
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected String processLine(String line){
		output.offer(line);
		return line;
	}
	
	public String toString() {
		return "cat(" + command + ")";
	}
}
