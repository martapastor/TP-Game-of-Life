package tp.pr3.controller;

import tp.pr3.logic.cell.Cell;
import tp.pr3.logic.world.SimpleWorld;
import tp.pr3.logic.world.ComplexWorld;
import tp.pr3.logic.world.World;
import tp.pr3.utils.EclipseTools;
import tp.pr3.utils.Position;

import java.util.*;

import tp.pr3.command.Command;
import tp.pr3.command.CommandParser;
import tp.pr3.exceptions.ParseCommandException;
import tp.pr3.exceptions.UnknownCommandException;
import tp.pr3.exceptions.ErrorWhileCreatingCellException;
import tp.pr3.exceptions.ErrorWhileDeletingCellException;
import tp.pr3.exceptions.FileFormatException;
import tp.pr3.exceptions.PositionDoesNotExistException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;

/**
 * Controller class
 * <p>
 * Controls the simulation of the game and interacts with the world and the
 * user.
 */
public class Controller {
	// Creates a World object where the simulation is going to take place and a
	// Scanner object that will read the commands we will type via keyboard:
	private World world;
	private Scanner in;

	boolean simulationTerminated = false;

	/**
	 * Controller constructor
	 * <p>
	 * It initialises a simple world with 3 rows, 4 columns and 3 simple cells
	 * randomly located.
	 */
	public Controller() {
		this.world = new SimpleWorld(3, 4, 3);
		this.in = new Scanner(System.in);
	}

	/**
	 * Method that returns if there is a cell in a given position.
	 * 
	 * @param pos
	 *            The position we want to check if it is occupied.
	 * @return If the position pos is occupied or not.
	 */
	public boolean getCell(Position pos) {
		return world.getCell(pos);
	}

	/**
	 * Accesor method that returns the number of rows our world has.
	 * 
	 * @return The number of rows of the world
	 */
	public int getRows() {
		return world.getRows();
	}

	/**
	 * Accesor method that returns the number of columns our world has.
	 * 
	 * @return The number of columns of the world
	 */
	public int getColumns() {
		return world.getColumns();
	}

	/**
	 * Initialises a new game in the current type of world.
	 * <p>
	 * That depends on the type of world (simple or complex) because
	 * world.initialiseWorld() is an abstract method of the abstract class
	 * World.
	 */
	public void initGame() {
		world.initialiseWorld();
	}

	/**
	 * Cleans the current type of world.
	 * <p>
	 * The method world.clean() calls the method surface.clean() which creates a
	 * new empty board.
	 */
	public void cleanWorld() {
		world.clean();
	}

	/**
	 * Creates a given cell in a given position.
	 * 
	 * @param pos
	 *            The position where we want to create the cell.
	 * @param cell
	 *            The cell we want to insert in the position.
	 * @return If the cell has been successfully inserted.
	 */
	public boolean createCell(Position pos, Cell cell) {
		return world.createCell(pos, cell);
	}

	/**
	 * Deletes a cell from a given position.
	 * 
	 * @param pos
	 *            The position from where we want to delete the cell.
	 * @return If the cell has been successfully deleted.
	 */
	public boolean deleteCell(Position pos) {
		return world.deleteCell(pos);
	}

	/**
	 * Executes a simulation step.
	 */
	public void evolve() {
		world.evolve();
	}

	/**
	 * Starts a new game in a given type of world.
	 * <p>
	 * The world passed as parameter is kept on the atributte world of this
	 * class.
	 * 
	 * @param world
	 *            The type of world where we want to play
	 */
	public void play(World world) {
		this.world = world;
	}

	/**
	 * Loads a game from a file.
	 * 
	 * @param fileName
	 *            The name of the file where the game is saved.
	 * @throws IOException
	 */
	public void load(String fileName) throws IOException {
		File file = new File(fileName);
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new BufferedReader(new FileReader(file)));
			// We read the complexity of the world we want to load in the game
			World worldToLoad = this.getComplexity(fileIn);
			// Once we know in which type we world we are playing, we load the
			// surface and the cells
			worldToLoad.load(fileIn);
			// We set the world loaded as this one in the controller
			this.world = worldToLoad;
			// If any exception has been thrown, a message confirm us the world
			// has been successfully loaded
			System.out.print("File successfully loaded." + System.getProperty("line.separator"));
		} catch (NumberFormatException e1) {
			System.err.println(e1);
		} catch (IndexOutOfBoundsException e2) {
			System.err.println(e2);
		} catch (FileNotFoundException e3) { // We print a String and not the
												// exception e3 because this
												// kind of exception is already
												// included in Java
			System.err.println("ERROR: File does not found.");
		} catch (FileFormatException e4) {
			System.err.println(e4);
		} finally {
			if (fileIn != null)
				fileIn.close();
		}
	}

	/**
	 * Saves a game in a file.
	 * 
	 * @param fileName
	 *            The name of the file where the game is going to be saved.
	 */
	public void save(String fileName) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			this.world.save(file);
			System.out.print("File successfully saved." + System.getProperty("line.separator"));
		} catch (IndexOutOfBoundsException e1) {
			System.err.println(e1);
		} catch (IOException e2) {
			System.err.println(e2);
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}

	/**
	 * Shows the help with all the available commands.
	 * <p>
	 * Each command prints its self help text and this method prints all at
	 * once.
	 */
	public void showHelp() {
		String help = CommandParser.helpTextCommands();
		System.out.println(help);
	}

	/**
	 * Sets the simulation as termined as we have ask the program to exit the
	 * game.
	 */
	public void simulationTerminated() {
		this.simulationTerminated = true;
	}

	/**
	 * Executes all the simulation of the game.
	 * <p>
	 * Asks the user to introduce commands and instructions via keyboard and it
	 * evolves itself.
	 * 
	 * @throws UnknownCommandException
	 * @throws ParseCommandException
	 */
	public void executeSimulation() {
		System.out.println("Welcome to the game!");
		initGame();

		// The simulation doesn't stop until we type the command "exit":
		while (!this.simulationTerminated) {
			EclipseTools.fixConsole();

			clearConsoleMsg();

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
			command = readInputFromConsole();
			commandWord = command.split(" ");

			// We convert the textual representation of a command as an array or
			// String into the object representation of the same command:
			new CommandParser();
			Command commandAux;
			try {
				commandAux = CommandParser.parseCommand(commandWord);
				commandAux.execute(this);
			} catch (ParseCommandException | UnknownCommandException | NumberFormatException
					| ErrorWhileCreatingCellException | ErrorWhileDeletingCellException
					| PositionDoesNotExistException e) {
				System.err.print(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.print(getConsoleMessage());

			world.toString();
		}

		System.out.println("Thanks for playing!");
	}

	/**
	 * Method that returns the type of world where we are playing.
	 * 
	 * @return True if it is a simple world, false if it is a complex world.
	 */
	public boolean isSimpleWorld() {
		return this.world.getClass() == SimpleWorld.class;
	}

	/**
	 * Method that loads the complexity of the game from a file and creates a
	 * new world of that complexity.
	 * 
	 * @param file
	 *            The input to read from the file.
	 * @return A new created world of the complwxity loaded from the file.
	 * @throws FileFormatException
	 */
	private World getComplexity(Scanner file) throws FileFormatException {
		String complejidad = file.nextLine();
		if (complejidad.equalsIgnoreCase("simple")) {
			return new SimpleWorld();
		} else if (complejidad.equalsIgnoreCase("complex")) {
			return new ComplexWorld();
		} else {
			throw new FileFormatException();
		}
	}

	/**
	 * Add a new instruction to the string that is going to show all the
	 * instructions executed.
	 * 
	 * @param steps
	 *            The string where we are keeping the message that is going to
	 *            be printed in console.
	 */
	public void addNewMessageToConsoleMsg(String steps) {
		world.addNewMessageToConsoleMsg(steps);
	}

	/**
	 * Clears the message string.
	 */
	public void clearConsoleMsg() {
		world.clearConsoleMsg();
	}

	/**
	 * Returns the complete message string.
	 * 
	 * @return The string with all the instructions executed in that step.
	 */
	public String getConsoleMessage() {
		return world.getConsoleMessage();
	}

	/**
	 * Auxiliar method created to be able to print from another class without
	 * using System.out.print(String) in there.
	 * 
	 * @param msg
	 *            The String we want to print through the console.
	 */
	public void printMessage(String msg) {
		System.out.print(msg);
	}

	/**
	 * Auxiliar method created to read an input String typed in the console.
	 * 
	 * @return The String read typed from the console.
	 */
	public String readInputFromConsole() {
		String command;
		command = in.nextLine().toLowerCase();
		return command;
	}

}
