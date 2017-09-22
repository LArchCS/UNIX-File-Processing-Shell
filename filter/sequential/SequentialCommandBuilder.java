package filter.sequential;

import java.util.*;

public class SequentialCommandBuilder {
	static LinkedList<SequentialFilter> lfList = new LinkedList<SequentialFilter>();
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		lfList = new LinkedList<SequentialFilter>();
		// divide into sub-commands
		command = command.trim();
		String[] subs = command.split("\\|");
		for (String s: subs) {
			lfList.addAll(constructFilterFromSubCommand(s.trim()));
		}
		//System.out.println(lfList);
		// link the filters
		SequentialCommandBuilder.linkFilters(lfList);
		return lfList;
	}

	public static LinkedList<SequentialFilter> constructFilterFromSubCommand(String subCommand){
		LinkedList<SequentialFilter> res = new LinkedList<SequentialFilter>();
		String[] cmds = subCommand.split("\\>");
		//System.out.println("sub: " + subCommand);
		//System.out.println(Arrays.toString(cmds));
		if (cmds.length > 0) {
			String curt = cmds[0].split(" ")[0].trim();
			if (cmds.length == 1 && subCommand.substring(0, 1).equals(">")) {
				String newFile = cmds[0].trim();
				res.add(new CmdRED(newFile));
			} else if (curt.equals("cd")) {
				res.add(new CmdCD(cmds[0].trim()));
			} else if (curt.equals("grep")) {
				res.add(new CmdGREP(cmds[0].trim()));
			} else if (curt.equals("ls")) {
				res.add(new CmdLS(cmds[0].trim()));
			} else if (curt.equals("pwd")) {
				res.add(new CmdPWD(cmds[0].trim()));
			} else if (curt.equals("wc")) {
				res.add(new CmdWC(cmds[0].trim()));
			} else if (curt.equals("cat")) {
				res.add(new CmdCAT(cmds[0].trim()));
			} else if (curt.equals("uniq")) {
				res.add(new CmdUNIQ(cmds[0].trim()));
			/*} else if (curt.equals("exit")) {
				res.add(new CmdEXIT(cmds[0].trim()));*/
			} else if (!cmds[0].trim().equals("")){
				res.add(new CmdNotFound(cmds[0].trim()));
			}
		}
		int len = subCommand.length();
		if (cmds.length == 1 && subCommand.substring(len - 1, len).equals(">")) {
			res.add(new CmdRED(""));
		} else if (cmds.length > 1) {
			String newFile = cmds[1].trim();
			res.add(new CmdRED(newFile));
		}
		return res;
	}
	
	private static boolean linkFilters(List<SequentialFilter> filters) {
		for (int i = 0; i < filters.size() - 1; i++) {
			filters.get(i).setNextFilter(filters.get(i + 1));
		}
		return false;
	}
}
