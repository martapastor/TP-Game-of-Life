package tp.pr3.command;

import tp.pr3.command.Command;
import tp.pr3.command.Step;
import tp.pr3.command.Help;
import tp.pr3.command.Exit;
import tp.pr3.command.Init;
import tp.pr3.command.Clean;
import tp.pr3.command.CreateCell;
import tp.pr3.command.DeleteCell;
import tp.pr3.command.SaveFile;
import tp.pr3.command.LoadFile;
import tp.pr3.command.PlayGame;

import tp.pr3.exceptions.ParseCommandException;
import tp.pr3.exceptions.UnknownCommandException;

public class CommandParser {

	private static Command[] command;

	public CommandParser() {
		command = new Command[10];
		command[0] = new Step();
		command[1] = new Help();
		command[2] = new Exit();
		command[3] = new Init();
		command[4] = new Clean();
		command[5] = new CreateCell();
		command[6] = new DeleteCell();
		command[7] = new SaveFile();
		command[8] = new LoadFile();
		command[9] = new PlayGame();
	}

	/**
	 * Returns the help for all commands.
	 * 
	 * @return the help for all commands
	 */
	static public String helpTextCommands() {
		String help = "AVAILABLE COMMANDS: " + System.getProperty("line.separator");
		for (Command commandList : command) {
			help += commandList.helpText();
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
	static public Command parseCommand(String[] commandString) throws ParseCommandException, UnknownCommandException {
		try {
			int i = 0;
			while (i < command.length) {
				if (command[i].parse(commandString) != null) {
					return command[i].parse(commandString);
				}
				i++;
			}
		} catch (Exception e) {
			throw new ParseCommandException(e.getMessage());
		} throw new UnknownCommandException("ERROR: Invalid command."+ System.getProperty("line.separator"));
	}
}
