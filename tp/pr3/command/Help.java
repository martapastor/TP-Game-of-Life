package tp.pr3.command;

import tp.pr3.controller.Controller;
//import tp.pr3.logic.World;

public class Help implements Command {

	public void execute(Controller controller) {
		controller.showHelp();
	}

	public Command parse(String[] commandString) {
		Command help = new Help();
		if (commandString.length != 1) {
			return null;
		} else {
			if (commandString[0].equals("help"))
				return help;
		}

		return null;
	}

	public String helpText() {
		String help = (" HELP: show this help." + System.getProperty("line.separator"));
		return help;
	}
}
