package filter.sequential;

import filter.*;
import java.util.*;

public class SequentialREPL {
	static String currentWorkingDirectory;
	static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static void main(String[] args) {
		// Set initial directory
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner CONSOLE = new Scanner(System.in);
		String input = CONSOLE.nextLine();
		System.out.print(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		
		while (!input.equals("exit")) {
			List<SequentialFilter> commands = SequentialCommandBuilder.createFiltersFromCommand(input);
			for (SequentialFilter command : commands) {
				Cmd cmd = (Cmd)command;
				if (cmd instanceof CmdNotFound) {
					System.out.print(cmd.processLine(""));
					break;
				}
				executeCmd(cmd);
			}
			if (commands.size() > 0 && ((Cmd)commands.get(commands.size() - 1)).isValid) {
				printCmd(((Cmd)commands.get(commands.size() - 1)));
			}
			System.out.print(Message.NEWCOMMAND);
			input = CONSOLE.nextLine();
		}
		System.out.print(Message.GOODBYE);
		CONSOLE.close();
	}

	private static void executeCmd(Cmd command) {
		command.process();
	}
	
	private static void printCmd(SequentialFilter command) {
		String res = "";
		for (String s : command.output) {
			res += s + "\n";
		}
		System.out.print(res);
	}
}
