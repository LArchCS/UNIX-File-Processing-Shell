package filter.sequential;

public class CmdEXIT extends Cmd {

	CmdEXIT(String s) {
		super(s);
		out = false;
		in = false;
		isValid = false;
	}
	
	public String toString() {
		return "exit(" + command + ")";
	}
}
