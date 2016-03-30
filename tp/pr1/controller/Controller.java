//System.getProperty("line.separator")

package tp.pr1.controller;

import tp.pr1.logic.World;
import tp.pr1.utils.EclipseTools;

import java.util.*;

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
		boolean finished = false;

		while (!finished) {
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

			switch (commandWord[0]) {
			// Execute a step simulation:
			case "step":
				System.out.print(world.evolve());
				break;

			// Clean the surface and then initialize it:
			case "init":
				world.cleanWorld();
				world.initWorld();
				System.out.println("Initializing the world...");
				break;

			// Clean the surface, turning all cells intro null:
			case "clean":
				world.cleanWorld();
				System.out.println("Cleaning the world...");
				break;

			// Creates a new cell in a given position:
			case "create":
				if (commandWord.length == 3) {
					int r = Integer.parseInt(commandWord[1]);
					int c = Integer.parseInt(commandWord[2]);
					int maxRows = World.getROWS();
					int maxColumns = World.getCOLUMNS();

					if (r < 0 || maxRows <= r || c < 0 || maxColumns <= c) {
						System.err.println("ERROR: The position introduced doesn't exist in this world.");
					} else {
						boolean cellCreated = world.createCell(r, c);

						if (!cellCreated) {
							System.err.println("ERROR: The position introduced is already occupied.");
						} else {
							System.out.println("New cell created at (" + r + ", " + c + ")");
						}
					}
				} else {
					System.err.println("ERROR: The position introduced is invalid.");
				}
				break;

			// Deletes a cell from a given position:
			case "delete":
				if (commandWord.length == 3) {
					int r = Integer.parseInt(commandWord[1]);
					int c = Integer.parseInt(commandWord[2]);
					int maxRows = World.getROWS();
					int maxColumns = World.getCOLUMNS();

					if (r < 0 || maxRows <= r || c < 0 || maxColumns <= c) {
						System.err.println("ERROR: The position introduced doesn't exist in this world.");
					} else {
						boolean cellDeleted = world.deleteCell(r, c);

						if (!cellDeleted) {
							System.err.println("ERROR: The position has no cell to delete.");
						} else {
							System.out.println("Cell deleted from (" + r + ", " + c + ")");
						}
					}
				} else {
					System.err.println("ERROR: The position introduced is invalid.");
				}
				break;

			// Show a help menu that explains the available commands:
			case "help":
				showHelp();
				break;

			// Finishes and exits the game:
			case "exit":
				finished = true;
				System.out.println("Thanks for playing!");
				break;

			// If the command introduced is not valid, shows an error message:
			default:
				System.err.println("ERROR: the command introduced is invalid.");
				break;
			}
		}
	}

	/**
	 * Prints in screen a menu showing the available commands.
	 */
	public void showHelp() {
		System.out.println("AVAILABLE COMMANDS: ");
		System.out.println("	STEP: executes a simulation step.");
		System.out.println("	HELP: show this help.");
		System.out.println("	EXIT: exit the game.");
		System.out.println("	INIT: restart the game.");
		System.out.println("	CLEAN: delete all the cells.");
		System.out.println("	CREATE R C: create a new cell at position (r,c)");
		System.out.println("	DELETE R C: delete the cell at position (r,c)");
	}
}
