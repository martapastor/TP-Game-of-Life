package tp.pr1.logic;

import tp.pr1.utils.Position;
import tp.pr1.utils.PosList;

/**
 * Surface class
 * <p>
 * A surface is a matrix of cells with X rows and Y columns.
 * 
 * @author David Calleja and Marta Pastor
 * @version 1.0
 */
public class Surface {

	private Cell[][] surface;
	private int rows;
	private int columns;

	private String board;

	/**
	 * Surface constructor
	 * 
	 * @param numRows
	 *            The number of rows that is going to have our surface
	 * @param numColumns
	 *            The number of columns that is going to have our surface
	 */
	public Surface(int numRows, int numColumns) {
		this.rows = numRows;
		this.columns = numColumns;

		surface = new Cell[rows][columns];

		// Initializes all positions of the surface to null:
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				surface[i][j] = null;
			}
		}
	}

	/**
	 * Accesor method that returns the cell located in a given position.
	 * 
	 * @param r
	 *            The row where is the cell we want to get
	 * @param c
	 *            The column where is the cell we want to get
	 * @return The cell that is in the given position (row and column)
	 */
	public Cell getCell(int r, int c) {
		return surface[r][c];
	}

	/**
	 * Creates a new cell in a given position.
	 * 
	 * @param r
	 *            The row where we want to create the cell
	 * @param c
	 *            The column where we want to create the cell
	 */
	public void createCell(int r, int c) {
		Cell cell = new Cell();
		surface[r][c] = cell;
	}

	/**
	 * Delete the cell from a given position.
	 * 
	 * @param r
	 *            The row where is located the cell we want to delete
	 * @param c
	 *            The column where is located the cell we want to delete
	 */
	public void deleteCell(int r, int c) {
		surface[r][c] = null;
	}

	/**
	 * Inserts a given cell in a given position.
	 * 
	 * @param r
	 *            The row where we want to insert the cell
	 * @param c
	 *            The column where we want to insert the cell
	 * @param cell
	 *            The cell we want to insert
	 * @return If the cell has been inserted or not
	 */
	public boolean insertCell(int r, int c, Cell cell) {
		boolean inserted = false;

		if (surface[r][c] == null) {
			surface[r][c] = cell;
			inserted = true;
		}
		return inserted;
	}

	/**
	 * Moves the cell from one position to another given using a list.
	 * 
	 * @param r1
	 *            The row where the cell is located
	 * @param c1
	 *            The column where the cell is located
	 * @param r2
	 *            The row where we want to move the cell
	 * @param c2
	 *            The column where we want to move the cell
	 * @param movedPosList
	 *            An auxiliar list the keeps the positions whose cells have
	 *            already been moved
	 * @return A string with all the instructions and steps the cells executes
	 */
	public String moveCell(int r1, int c1, int r2, int c2, PosList movedPosList) {
		String executionSteps = "";

		// The cell dies because of inactivity
		if (surface[r1][c1].getInactivitySteps() == 0) {
			deleteCell(r1, c1);

			executionSteps = "Cell at (" + r1 + ", " + c1 + ") dies of inactivity."
					+ System.getProperty("line.separator");
		}

		else {
			// The cell matures
			if (surface[r1][c1].getMatureSteps() == 0) {
				deleteCell(r1, c1);
				Cell newCell_1 = new Cell();
				Cell newCell_2 = new Cell();
				insertCell(r1, c1, newCell_1);
				insertCell(r2, c2, newCell_2);

				Position pos_1 = new Position(r1, c1);
				Position pos_2 = new Position(r2, c2);
				movedPosList.add(pos_1, movedPosList.length());
				movedPosList.add(pos_2, movedPosList.length());

				executionSteps = "Cell at (" + r1 + ", " + c1 + ") moved to (" + r2 + ", " + c2 + ")"
						+ System.getProperty("line.separator") + "New cell born at (" + r1 + ", " + r2 + ")"
						+ System.getProperty("line.separator");
			}
			// The cell moves
			else {
				insertCell(r2, c2, surface[r1][c1]);
				deleteCell(r1, c1);
				// Decrease the matureSteps counter:
				surface[r2][c2].maturate();

				Position pos = new Position(r2, c2);
				movedPosList.add(pos, movedPosList.length());

				executionSteps = "Cell at (" + r1 + ", " + c1 + ") moved to (" + r2 + ", " + c2 + ")"
						+ System.getProperty("line.separator");
			}
		}
		return executionSteps;
	}

	/**
	 * Checks if a cell located in a given position has already move from
	 * another one in the current "step" movement.
	 * 
	 * @param posList
	 *            The auxiliar list containing all the cells already moved
	 * @param r
	 *            The row where is located the given cell
	 * @param c
	 *            The column where is located the given cell
	 * @return If a given cell has already moved in the current "step" movement
	 */
	public boolean hasMoved(PosList posList, int r, int c) {
		boolean moved = false;

		for (int i = 0; i < posList.length(); i++) {
			if (posList.get(i).getRow() == r && posList.get(i).getColumn() == c)
				moved = true;
		}

		return moved;
	}

	/*
	 * Sets the surface as we want to see it in the console.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		board = "" + System.getProperty("line.separator");

		// Draw the board whatever the value of the cell is:
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (surface[i][j] == null) {
					board = board + " | - | ";
				} else {
					board = board + " |" + surface[i][j] + "| ";
				}
			}
			// Line finished:
			board = board + System.getProperty("line.separator");
		}
		// Board finished:
		board = board + System.getProperty("line.separator");

		return board;
	}

	/**
	 * Creates a posList with all the positions in the board which we will use
	 * later to select randomly the INI_NUM_CELLS initial positions.
	 * 
	 * @param r
	 *            The number of rows that has our surface
	 * @param c
	 *            Yhe number of columns that has our surface
	 * @return A posList with all the board positions ordered.
	 */
	public PosList allBoardPositions(int r, int c) {
		PosList allPosList = new PosList();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				Position newPos = new Position(i, j);
				allPosList.add(newPos, allPosList.length());
			}
		}

		return allPosList;
	}

	/**
	 * Creates a posList with the positions where the cell can move - the cell
	 * can move to a position if it is neighbour and if it is free of cells.
	 * 
	 * @param r
	 *            The row where is located the given cell
	 * @param c
	 *            The column where is located the given cell
	 * @return A posList with all the free neighbour positions ordered.
	 */
	public PosList freeNeighbourPositions(int r, int c) {
		PosList freePosList = new PosList();

		for (int i = r - 1; i <= r + 1; i++) {
			if (0 <= i && i < this.rows) {
				for (int j = c - 1; j <= c + 1; j++) {
					if (0 <= j && j < this.columns && surface[i][j] == null) {
						Position pos = new Position(i, j);
						freePosList.add(pos, freePosList.length());
					}
				}
			}
		}

		return freePosList;
	}

	/**
	 * Returns the position coords (r, c) kept in the n position of the posList.
	 * 
	 * @param posList
	 *            A given posList
	 * @param n
	 *            The index of the posList element we want to get
	 * @return The position coords (r, c) kept in the n position of the posList
	 */
	public Position selectFirstPositions(PosList posList, int n) {
		return posList.get(n);
	}

}
