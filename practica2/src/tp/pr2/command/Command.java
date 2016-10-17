package tp.pr2.command;

import tp.pr2.logic.World;

/**
 * Command class
 * <p>
 * Abstarct class that executes the command introduced via keyboard.
 */
public abstract class Command {

	/**
	 * Executes a command
	 * 
	 * @param world World in which the command is executed
	 */
	public abstract void execute(World world);
	
	/**
	 * Analyzes grammatically the command
	 * 
	 * @param commandString the command as an array of words
	 * @return An object representing the command, or null if the command doesn't match
	 */
	public abstract Command parse(String[ ] commandString);
	
	/**
	 * Returns the help message for this command
	 * 
	 * @return the help message for this command
	 */
	public abstract String helpText();
}
