package tp.pr2.controller;

import tp.pr2.logic.World;
import tp.pr2.utils.EclipseTools;

import java.util.*;

import tp.pr2.command.Command;
import tp.pr2.command.CommandParser;;

/**
 * Controller class
 * <p>
 * Controls the simulation of the game and interacts with the world and the
 * user.
 */
public class Controller {

	private World world;
	private Scanner in;

	/**
	 * Controller constructor
	 */
	public Controller() {
		this.world = new World();
		this.in = new Scanner(System.in);
	}

	/**
	 * Executes all the simulation of the game.
	 * <p>
	 * Asks the user to introduce commands and instructions via keyboard.
	 */
	public void executeSimulation() {

		while (!world.isSimulationFinished()) {
			EclipseTools.fixConsole();

			String command;
			String[] commandWord;

			// Draw the surface:
			System.out.print(world);

			// Asks us for a command through the screen:
			System.out.print("Command -> ");

			// Reads the request of command we have typed and ignore capital
			// letters. If we introduce more than 1 word (create 2 3), the
			// instruction split separates the words as if we had a
			// String array:
			command = in.nextLine().toLowerCase();
			commandWord = command.split(" ");

			// We convert the textual representation of a command as an array or
			// String into the object representation of the same command:
			new CommandParser();
			Command commandAux = CommandParser.parseCommand(commandWord);
			if (commandAux != null) {
				commandAux.execute(this.world);
			} else {
				System.err.println("ERROR: the command introduced is invalid." + System.getProperty("line.separator"));
			}

			System.err.print(world.getErrMsgToPrint());
			world.clearErrMsg();

			System.out.print(world.getMessageToPrint());
			world.clearConsoleMsg();

			world.toString();
		}

		System.out.println("Thanks for playing!");
	}

}
