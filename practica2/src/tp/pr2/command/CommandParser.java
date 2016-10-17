package tp.pr2.command;

import tp.pr2.command.Command;
import tp.pr2.command.Step;
import tp.pr2.command.Help;
import tp.pr2.command.Exit;
import tp.pr2.command.Init;
import tp.pr2.command.Clean;
import tp.pr2.command.CreateSimpleCell;
import tp.pr2.command.CreateComplexCell;
import tp.pr2.command.DeleteCell;

public class CommandParser {

	private static Command[] command;

	public CommandParser() {
		command = new Command[8];
		command[0] = new Step();
		command[1] = new Help();
		command[2] = new Exit();
		command[3] = new Init();
		command[4] = new Clean();
		command[5] = new CreateSimpleCell();
		command[6] = new CreateComplexCell();
		command[7] = new DeleteCell();
	}

	/**
	 * Returns the help for all commands.
	 * 
	 * @return the help for all commands
	 */
	static public String helpTextCommands() {
		String help = "AVAILABLE COMMANDS: " + System.getProperty("line.separator");
		for (int i = 0; i < command.length && command[i] != null; i++) {
			help += command[i].helpText();
		}
		return help;
	}

	/**
	 * Returns an object representing the command, or null if the command
	 * doesn't exist.
	 * 
	 * @param commandString
	 *            the command as an array of words
	 * @return an object representing the command, or null if the command
	 *         doesn't exist
	 */
	static public Command parseCommand(String[] strings) {
		int i = 0;
		while (i < command.length) {
			if (command[i].parse(strings) != null) {
				return command[i].parse(strings);
			}
			i++;
		}
		return null;
	}
}
