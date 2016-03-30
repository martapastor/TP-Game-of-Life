package tp.pr3.command;

import java.io.IOException;

import tp.pr3.controller.Controller;

public class LoadFile implements Command {

	private String fileName;
	
	/**
	 * LoadFile constructor (without arguments)
	 */
	public LoadFile(){
		
	}
	
	/**
	 * LoadFile constructor (with arguments)
	 * 
	 * @param fileName
	 *            The String corresponding to the name of the file we want to
	 *            load.
	 */
	private LoadFile(String fileName) {
		this.fileName = fileName;
	}
	
	public void execute(Controller controller) throws IOException {
		controller.load(this.fileName);
	}

	public Command parse(String[] commandString) {
		if (commandString[0].equalsIgnoreCase("load")) {
			if (commandString.length == 1)
				return new LoadFile();
			else
				return new LoadFile(new String(commandString[1]));
		}
		return null;
	}

	public String helpText() {
		String help =(" LOAD: loads a file with the savings of the game." + System.getProperty("line.separator"));
		return help; 
	}

}
