package tp.pr3.logic.world;

import tp.pr3.logic.cell.*;
import tp.pr3.utils.PosList;
import tp.pr3.utils.Position;

public class ComplexWorld extends World {
	private int numSCell;
	private int numCCell;

	/**
	 * ComplexWorld constructor (without arguments)
	 */
	public ComplexWorld() {
		super();
		this.numSCell = 0;
		this.numCCell = 0;
	}

	/**
	 * ComplexWorld constructor (with arguments)
	 * 
	 * @param row
	 *            The number of rows.
	 * @param column
	 *            The number of columns.
	 */
	public ComplexWorld(int r, int c, int numSimple, int numComplex) {
		super(r, c);
		this.numSCell = numSimple;
		this.numCCell = numComplex;
		initialiseWorld();
	}

	/**
	 * Asks the surface to initialize the board.
	 */
	public void initialiseWorld() {
		clean();
		PosList initPosList = surface.allBoardPositions(rows, columns);
		initPosList.shuffle();

		// Creates a NUM_SIMPLE_CELLS number of cells in the first
		// NUM_SIMPLE_CELLS positions:

		for (int i = 0; i < numSCell; i++) {
			SimpleCell sCell = new SimpleCell();
			Position pos = surface.selectFirstPositions(initPosList, i);
			surface.insertCell(pos, sCell);
		}

		// Creates a NUM_COMPLEX_CELLS number of cells in the first
		// NUM_COMPLEX_CELLS positions:
		for (int j = 0; j < numCCell; j++) {
			ComplexCell cCell = new ComplexCell();
			Position pos = surface.selectFirstPositions(initPosList, numSCell + j);
			surface.insertCell(pos, cCell);
		}
	}

	/**
	 * Creates a new simple cell in a given position.
	 * 
	 * @param pos
	 *            The position where we want to create the cell.
	 * @return If the cell has been successfully created.
	 */
	public boolean createSimpleCell(Position pos) {
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
	 * Creates a new complex cell in a given position.
	 * 
	 * @param pos
	 *            The position where we want to create the cell.
	 * @return If the cell has been successfully created.
	 */
	public boolean createComplexCell(Position pos) {
		boolean cellCreated = false;

		int r = pos.getRow();
		int c = pos.getColumn();

		ComplexCell cell = new ComplexCell();

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
		return "complex";
	}
}
