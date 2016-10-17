package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.ErrorWhileCreatingCellException;
import tp.pr3.exceptions.ErrorWhileDeletingCellException;
import tp.pr3.exceptions.InitialisationException;
import tp.pr3.exceptions.PositionDoesNotExistException;
import tp.pr3.exceptions.UnknownFileException;
import tp.pr3.exceptions.UnknownWorldTypeException;

import java.io.IOException;

/**
 * Command class
 * <p>
 * Abstarct class that executes the command introduced via keyboard.

 */
public interface Command {

	/**
	 * Executes a command
	 * 
	 * @param world World in which the command is executed
	 */
	public abstract void execute(Controller controller) throws PositionDoesNotExistException, UnknownWorldTypeException, UnknownFileException, ErrorWhileCreatingCellException, ErrorWhileDeletingCellException, IOException;
	
	/**
	 * Analyzes grammatically the command
	 * 
	 * @param commandString the command as an array of words
	 * @return An object representing the command, or null if the command doesn't match
	 * @throws InitialisationException 
	 * @throws UnknownWorldTypeException 
	 */
	public abstract Command parse(String[ ] commandString) throws UnknownWorldTypeException, InitialisationException;
	
	/**
	 * Returns the help message for this command
	 * 
	 * @return the help message for this command
	 */
	public abstract String helpText();
}
