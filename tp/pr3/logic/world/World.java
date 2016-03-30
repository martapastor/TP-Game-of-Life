package tp.pr3.logic.world;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import tp.pr3.logic.Surface;
import tp.pr3.logic.cell.*;
import tp.pr3.utils.PosList;
import tp.pr3.utils.Position;
import tp.pr3.exceptions.FileFormatException;

/**
 * World class
 * <p>
 * Controls the simulation of the game and interacts with the surface.
 */
public abstract class World {
	// Creates a Surface object that is keeping the board where the cells live
	// and two integers, rows and columns, that keeps the dimensions of this
	// world.
	protected Surface surface;
	protected int rows;
	protected int columns;

	/**
	 * World constructor (without arguments)
	 * <p>
	 * Sets the dimensions of the world to 0.
	 */
	public World() {
		this.rows = 0;
		this.columns = 0;
	}

	/**
	 * World constructor (with arguments)
	 * <p>
	 * Sets the dimensions of the world to row and column.
	 * 
	 * @param row
	 *            The number of rows.
	 * @param column
	 *            The number of columns.
	 */
	public World(int row, int column) {
		this.rows = row;
		this.columns = column;
		surface = new Surface(row, column);
	}

	/**
	 * Method that returns if there is a cell in a given position.
	 * 
	 * @param pos
	 *            The position we want to check if it is occupied.
	 * @return If the position pos is occupied or not.
	 */
	public boolean getCell(Position pos) {
		return surface.getCell(pos);
	}

	/**
	 * Accesor method that returns the number of rows our world has.
	 * 
	 * @return The number of rows of the world
	 */
	public int getRows() {
		return this.rows;
	}

	/**
	 * Accesor method that returns the number of columns our world has.
	 * 
	 * @return The number of columns of the world
	 */
	public int getColumns() {
		return this.columns;
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
		Position originPos = new Position();
		Position finalPos = new Position();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				originPos.setRow(i);
				originPos.setColumn(j);

				if (surface.getCell(originPos) && !surface.hasMoved(movedPosList, originPos)) {
					finalPos = surface.executeMovement(originPos);

					if (finalPos != null) {
						movedPosList.add(finalPos, movedPosList.length());
					}
				}
			}
		}
		return executionSteps;
	}

	/**
	 * Initialises a new game in the current world.
	 */
	public abstract void initialiseWorld();

	/**
	 * Cleans the current world.
	 */
	public void clean() {
		surface.clean();
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
		return surface.insertCell(pos, cell);
	}

	/**
	 * Deletes a cell from a given position.
	 * 
	 * @param pos
	 *            The position from where we want to delete the cell.
	 * @return If the cell has been successfully deleted.
	 */
	public abstract boolean deleteCell(Position pos);

	/**
	 * Loads a game from a file.
	 * 
	 * @param file
	 *            The file where the game is saved.
	 */
	public void load(Scanner file)
			throws IndexOutOfBoundsException, NumberFormatException, IOException, FileFormatException {
		this.loadDimension(file);
		this.surface = Surface.load(file, rows, columns);
	}

	/**
	 * Saves a game in a file.
	 * 
	 * @param file
	 *            The file where the game is going to be saved.
	 */
	public void save(PrintWriter file) throws IOException, IndexOutOfBoundsException {
		file.write(this.getComplexity() + System.getProperty("line.separator") + this.rows
				+ System.getProperty("line.separator") + this.columns + System.getProperty("line.separator"));
		this.surface.save(file);
	}

	/**
	 * Returns the complete message string.
	 * 
	 * @return The string with all the instructions executed in that step.
	 */
	public String getConsoleMessage() {
		return surface.getConsoleMsg();
	}

	/**
	 * Returns the complete error string.
	 * 
	 * @return The string with all the errors found in that step.
	 */
	public String getErrorMessage() {
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

	/**
	 * Loads the dimension for the surface from a file.
	 * 
	 * @param file
	 *            The file where the data is saved.
	 * @throws NumberFormatException
	 */
	private void loadDimension(Scanner file) throws NumberFormatException {
		try {
			this.rows = file.nextInt();
			this.columns = file.nextInt();
			//file.nextLine();
		} catch (Exception e) {
			throw new NumberFormatException();
		}

	}

	/**
	 * Gets the complexity from a file where a game is saved.
	 * 
	 * @return A string with the complexity for the world.
	 */
	protected abstract String getComplexity();
}
