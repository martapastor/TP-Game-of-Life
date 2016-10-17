package tp.pr3.logic.world;

import tp.pr3.logic.cell.*;
import tp.pr3.utils.PosList;
import tp.pr3.utils.Position;

public class SimpleWorld extends World {

	private int numCells;

	/**
	 * SimpleWorld constructor (without arguments)
	 */
	public SimpleWorld() {
		super();
		this.numCells = 0;
	}

	/**
	 * SimpleWorld constructor (with arguments)
	 * 
	 * @param row
	 *            The number of rows.
	 * @param column
	 *            The number of columns.
	 */
	public SimpleWorld(int r, int c, int numCells) {
		super(r, c);
		this.numCells = numCells;
		initialiseWorld();
	}

	/**
	 * Asks the surface to initialize the board.
	 */
	public void initialiseWorld() {
		clean();
		PosList initPosList = surface.allBoardPositions(rows, columns);
		initPosList.shuffle();

		// Creates a numCells number of cells in the first numCells
		// positions: (O NO, POR LO VISTO)
		for (int i = 0; i < numCells; i++) {
			SimpleCell sCell = new SimpleCell();
			Position pos = surface.selectFirstPositions(initPosList, i);
			surface.insertCell(pos, sCell);
		}
	}

	/**
	 * Creates a new cell in a given position.
	 * 
	 * @param pos
	 *            The position where we want to create the cell.
	 * @return If the cell has been successfully created.
	 */
	public boolean createCell(Position pos) {
		boolean cellCreated = false;

		int r = pos.getRow();
		int c = pos.getColumn();

		SimpleCell cell = new SimpleCell();

		if (r < 0 || rows <= r || c < 0 || columns <= c) {
			addNewMessageToErrMsg("ERROR: The position introduced doesn't exist in this world.");
		} else {
			if (!surface.getCell(pos)) {
				surface.insertCell(pos, cell);
				cellCreated = true;
			} else {
				addNewMessageToErrMsg("ERROR: The position introduced is already occupied.");
			}
		}

		return cellCreated;
	}

	/**
	 * Deletes a cell from a given position.
	 * 
	 * @param pos
	 *            The position from where we want to delete the cell.
	 * @return If the cell has been successfully deleted.
	 */
	public boolean deleteCell(Position pos) {
		boolean cellDeleted = false;

		int r = pos.getRow();
		int c = pos.getColumn();

		if (r < 0 || rows <= r || c < 0 || columns <= c) {
			addNewMessageToErrMsg("ERROR: The position introduced doesn't exist in this world.");
		} else {
			if (surface.getCell(pos)) {
				surface.deleteCell(pos);
				cellDeleted = true;
			} else {
				addNewMessageToErrMsg("ERROR: The position has no cell to delete.");
			}
		}

		return cellDeleted;
	}

	public String toString() {
		String board = surface.toString();

		return board;
	}

	protected String getComplexity() {
		return "simple";
	}
}
