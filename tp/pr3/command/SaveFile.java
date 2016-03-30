package tp.pr3.command;

import tp.pr3.controller.Controller;

public class SaveFile implements Command {

	private String fileName;

	/**
	 * SaveFile constructor (without arguments)
	 */
	public SaveFile() {

	}

	/**
	 * SaveFile constructor (with arguments)
	 * 
	 * @param fileName
	 *            The String corresponding to the name of the file where we want
	 *            to save our game.
	 */
	private SaveFile(String fileName) {
		this.fileName = fileName;
	}

	public void execute(Controller controller) {
		controller.save(this.fileName);
	}

	public Command parse(String[] commandString) {
		if (commandString[0].equalsIgnoreCase("save")) {
			if (commandString.length == 1)
				return new SaveFile();
			else
				return new SaveFile(new String(commandString[1]));
		}
		return null;
	}

	public String helpText() {
		String help = (" SAVE: saves the state of the game in a file." + System.getProperty("line.separator"));
		return help;
	}

}
