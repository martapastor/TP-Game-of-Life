package tp.pr1.logic;

import tp.pr1.utils.Position;
import tp.pr1.utils.PosList;

import java.util.Random;

/**
 * World class
 * <p>
 * Controls the simulation of the game and interacts with the surface.
 * 
 * @author David Calleja and Marta Pastor
 * @version 1.0
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
	private static final int INI_NUM_CELLS = 3;

	private static final int ROWS = 3;
	private static final int COLUMNS = 4;

	private Surface surface;

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
		String executionSteps = "";

		PosList movedPosList = new PosList();

		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				boolean cellAlreadyMoved = surface.hasMoved(movedPosList, r, c);

				// We check that there is a cell in (r, c) position and it has
				// not move yet:
				if (surface.getCell(r, c) != null && !cellAlreadyMoved) {
					PosList freePosList = surface.freeNeighbourPositions(r, c);

					// We check there are at least one free neighbour position:
					if (freePosList.length() > 0) {
						Random rand = new Random();
						int randomPos = rand.nextInt(freePosList.length());

						// We move the cell from position (r,c) to position
						// randomPos, which attributes are rows and columns:
						Position newPos = freePosList.get(randomPos);
						executionSteps += surface.moveCell(r, c, newPos.getRow(), newPos.getColumn(), movedPosList);

					}
					// If there are no free positions:
					else {
						// The cell decreases their inactivity and mature
						// counters, but it does not die yet:
						if (surface.getCell(r, c).getInactivitySteps() != 0
								&& surface.getCell(r, c).getMatureSteps() != 0) {
							surface.getCell(r, c).inactivate();
							surface.getCell(r, c).maturate();
						}
						// The cell dies because of inactivity:
						else if (surface.getCell(r, c).getInactivitySteps() == 0) {
							surface.deleteCell(r, c);

							executionSteps += "Cell at (" + r + ", " + c + ") dies of inactivity."
									+ System.getProperty("line.separator");
						}
						// The cell dies on being unable to reproduce:
						else if (surface.getCell(r, c).getMatureSteps() == 0) {
							surface.deleteCell(r, c);

							executionSteps += "Cell at (" + r + ", " + c + ") dies on being unable to reproduce."
									+ System.getProperty("line.separator");
						}
					}
				}
			}
		}

		return executionSteps;
	}

	/**
	 * Creates a new cell at a given position on the surface.
	 * 
	 * @param row
	 *            The row where we want to create the cell
	 * @param column
	 *            The column where we want to create the cell
	 * @return If the cell has been created in the given position
	 */
	public boolean createCell(int row, int column) {
		boolean cellCreated = false;

		if (surface.getCell(row, column) == null) {
			surface.createCell(row, column);
			cellCreated = true;
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

		if (surface.getCell(row, column) != null) {
			surface.deleteCell(row, column);
			cellDeleted = true;
		}

		return cellDeleted;
	}

	/**
	 * Asks the surface to initialize the board.
	 */
	public void initWorld() {
		PosList initPosList = surface.allBoardPositions(ROWS, COLUMNS);
		initPosList.shuffle();

		// Creates a INI_NUM_CELLS number of cells in the first INI_NUM_CELLS
		// positions:
		for (int i = 0; i < INI_NUM_CELLS; i++) {
			Position pos = surface.selectFirstPositions(initPosList, i);
			surface.createCell(pos.getRow(), pos.getColumn());
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

}
