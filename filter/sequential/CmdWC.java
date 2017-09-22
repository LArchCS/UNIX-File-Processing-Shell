package filter.sequential;

import filter.Message;

public class CmdWC extends Cmd {
	int lines;
	int characters;
	int words;

	CmdWC(String cmd) {
		super(cmd);
		resetCount();
		in = true;
		out = true;
	}

	public void process(){
		resetCount();
		if (prev != null && ((Cmd)prev).isValid == false) {
			isValid = false;
			return;
		}
		if (prev == null || ((Cmd)prev).isValid == false || ((Cmd)prev).out == false) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(command));
			isValid = false;
			return;
		}
		if (prev != null && ((Cmd)prev).isValid == true && isValid == true) {
			while (!input.isEmpty()) {
				String line = input.poll();
				processLine(line);
			}
			output.offer("" + lines + " " + words + " " + characters);
		}
	}

	public void resetCount() {
		lines = 0;
		characters = 0;
		words = 0;
	}

	protected String processLine(String line){
		lines += 1;
		characters += line.length();
		words += line.isEmpty() ? 0 : line.split("\\s+").length;
		return line;
	}
	
	public String toString() {
		return "wc(" + command + ")";
	}
}
