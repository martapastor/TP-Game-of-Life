package tp.pr2.logic;

import tp.pr2.command.CommandParser;
import tp.pr2.utils.Position;
import tp.pr2.utils.PosList;

/**
 * World class
 * <p>
 * Controls the simulation of the game and interacts with the surface.
 */
/**
 * @author Marta
 *
 */
public class World {

	/*
	 * INI_NUM_CELLS is the number of cells that should appear in randoms
	 * positions when we initialize the world.
	 * 
	 * ROWS is the number of rows that is going to have our world.
	 * 
	 * COLUMNS is the number of columns that is going to have our world.
	 */
	public static final int NUM_SIMPLE_CELLS = 3;
	public static final int NUM_COMPLEX_CELLS = 2;

	private static final int ROWS = 3;
	private static final int COLUMNS = 4;

	private Surface surface;

	private boolean finished = false; // Used for assignment 2

	/**
	 * World constructor
	 */
	public World() {
		this.surface = new Surface(ROWS, COLUMNS);
	}

	/**
	 * Accesor method that returns the number of rows our world has.
	 * 
	 * @return The number of rows of the world
	 */
	public static int getROWS() {
		return ROWS;
	}

	/**
	 * Accesor method that returns the number of columns our world has.
	 * 
	 * @return The number of columns of the world
	 */
	public static int getCOLUMNS() {
		return COLUMNS;
	}

	/**
	 * Executes a simulation step.
	 * 
	 * @return The instructions that performs each cell in each step of the
	 *         simulation as a String
	 */
	public String evolve() {
		String executionSteps = new String("");
		PosList movedPosList = new PosList();
		Position pos = new Position(0, 0);
		Position posAux = new Position(0, 0);
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (surface.getCell(i, j) && !surface.hasMoved(movedPosList, i, j)) {
					posAux.setRow(i);
					posAux.setColumn(j);
					pos = surface.executeMovement(posAux);

					if (pos != null) {
						movedPosList.add(pos, movedPosList.length());
					}
				}
			}
		}
		return executionSteps;
	}

	/**
	 * Creates a new simple cell at a given position on the surface.
	 * 
	 * @param row
	 *            The row where we want to create the cell
	 * @param column
	 *            The column where we want to create the cell
	 * @return If the cell has been created in the given position
	 */
	public boolean createSimpleCell(int row, int column) {
		boolean cellCreated = false;

		SimpleCell cell = new SimpleCell();

		if (row < 0 || ROWS <= row || column < 0 || COLUMNS <= column) {
			addNewMessageToErrMsg("ERROR: The position introduced doesn't exist in this world.");
		} else {
			if (!surface.getCell(row, column)) {
				surface.insertCell(row, column, cell);
				cellCreated = true;
			} else {
				addNewMessageToErrMsg("ERROR: The position introduced is already occupied.");
			}
		}

		return cellCreated;
	}

	/**
	 * Creates a new complex cell at a given position on the surface.
	 * 
	 * @param row
	 *            The row where we want to create the cell
	 * @param column
	 *            The column where we want to create the cell
	 * @return If the cell has been created in the given position
	 */
	public boolean createComplexCell(int row, int column) {
		boolean cellCreated = false;

		ComplexCell cell = new ComplexCell();

		if (row < 0 || ROWS <= row || column < 0 || COLUMNS <= column) {
			addNewMessageToErrMsg("ERROR: The position introduced doesn't exist in this world.");
		} else {
			if (!surface.getCell(row, column)) {
				surface.insertCell(row, column, cell);
				cellCreated = true;
			} else {
				addNewMessageToErrMsg("ERROR: The position introduced is already occupied.");
			}
		}

		return cellCreated;
	}

	/**
	 * Deletes an existing cell at a given position on the surface.
	 * 
	 * @param row
	 *            The row where we want to create the cellis located the cell we
	 *            want to delete
	 * @param column
	 *            The column where we want to create the cellis located the cell
	 *            we want to delete
	 * @return If the cell has been deleted from the given position
	 */
	public boolean deleteCell(int row, int column) {
		boolean cellDeleted = false;

		if (row < 0 || ROWS <= row || column < 0 || COLUMNS <= column) {
			addNewMessageToErrMsg("ERROR: The position introduced doesn't exist in this world.");
		} else {
			if (surface.getCell(row, column)) {
				surface.deleteCell(row, column);
				cellDeleted = true;
			} else {
				addNewMessageToErrMsg("ERROR: The position has no cell to delete.");
			}
		}

		return cellDeleted;
	}

	/**
	 * Asks the surface to initialize the board.
	 */
	public void initWorld() {
		PosList initPosList = surface.allBoardPositions(ROWS, COLUMNS);
		initPosList.shuffle();

		// Creates a NUM_SIMPLE_CELLS number of cells in the first
		// NUM_SIMPLE_CELLS
		// positions:

		for (int i = 0; i < NUM_SIMPLE_CELLS; i++) {
			SimpleCell sCell = new SimpleCell();
			Position pos = surface.selectFirstPositions(initPosList, i);
			surface.insertCell(pos.getRow(), pos.getColumn(), sCell);
		}

		// Creates a NUM_COMPLEX_CELLS number of cells in the first
		// NUMOMPLEX_CELLS
		// positions:

		for (int j = 0; j < NUM_COMPLEX_CELLS; j++) {
			ComplexCell cCell = new ComplexCell();
			Position pos = surface.selectFirstPositions(initPosList, NUM_SIMPLE_CELLS + j);
			surface.insertCell(pos.getRow(), pos.getColumn(), cCell);
		}

	}

	/**
	 * Asks the surface to reset (empty) the board.
	 */
	public void cleanWorld() {
		surface = new Surface(ROWS, COLUMNS);
	}

	/*
	 * Sets the world as we want to see it in the console.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String board = surface.toString();

		return board;
	}

	/**
	 * Set the boolean finished as true because we have asked the game to exit.
	 */
	public void setSimulationFinished() {
		this.finished = true;
	}

	/**
	 * Tell us if we have to continue executing the game or not.
	 * 
	 * @return If we have order the simulation to stop or not.
	 */
	public boolean isSimulationFinished() {
		return this.finished;
	}

	/**
	 * Shows a help menu.
	 * 
	 * @return A String help menu with a brief explanation of each command.
	 */
	public String showHelp() {
		String help = CommandParser.helpTextCommands();
		return help;
	}

	/**
	 * Returns the complete message string.
	 * 
	 * @return The string with all the instructions executed in that step.
	 */
	public String getMessageToPrint() {
		return surface.getConsoleMsg();
	}

	/**
	 * Returns the complete error string.
	 * 
	 * @return The string with all the errors found in that step.
	 */
	public String getErrMsgToPrint() {
		return surface.getErrMsg();
	}

	/**
	 * Clears the message string.
	 */
	public void clearConsoleMsg() {
		surface.clearConsoleMsg();
	}

	/**
	 * Clears the error string.
	 */
	public void clearErrMsg() {
		surface.clearErrMsg();
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
		surface.addNewMessageToConsoleMsg(steps);
	}

	/**
	 * Add a new error to the string that is going to show all the errors found.
	 * 
	 * @param steps
	 *            The string where we are keeping the errors that is going to be
	 *            printed in console.
	 */
	public void addNewMessageToErrMsg(String steps) {
		surface.addNewMessageToErrMsg(steps);
	}

}
