package tp.pr2.command;

import tp.pr2.logic.World;

public class Help extends Command {

	public void execute(World world) {
		world.addNewMessageToConsoleMsg(world.showHelp());
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
		String ayuda = (" HELP: show this help." + System.getProperty("line.separator"));
		return ayuda;
	}
}
