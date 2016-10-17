package tp.pr3.command;

import tp.pr3.controller.Controller;

public class Clean implements Command {

	public void execute(Controller controller) {
		controller.cleanWorld();
	}

	public Command parse(String[] commandString) {
		Command clean = new Clean();
		if (commandString.length != 1) {
			return null;
		} else {
			if (commandString[0].equals("clean")) {
				return clean;
			}
		}

		return null;
	}

	public String helpText() {
		String help = (" CLEAN: delete all the cells." + System.getProperty("line.separator"));
		return help;
	}

}
