package tp.pr2.logic;

import tp.pr2.utils.Position;
import tp.pr2.utils.PosList;
import tp.pr2.logic.Surface;

/**
 * ComplexCell class
 * <p>
 * A cell is an object with a not fed counter that can eats simple cells.
 */
public class ComplexCell extends Cell {
	public static final int MAX_EAT = 3;

	private String cell;
	private int not_fed;

	public ComplexCell() {
		not_fed = MAX_EAT;
	}

	/*
	 * Sets the cells as we want to see them in the console.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		cell = " " + not_fed + " ";

		return cell;
	}

	/**
	 * Returns if the complex cell is about to die or not
	 * 
	 * @return If the cell is going to die
	 */
	public boolean mustDie() {
		if (this.not_fed == 0) {
			return true;
		} else {
			return false;
		}
	}

	public Position executeMovement(Position pos, Surface surface) {
		String steps = "";
		boolean dead = this.mustDie();
		Position posAux;

		PosList freePosList = surface.freeNeighbourPositions(pos.getRow(), pos.getColumn());

		freePosList.shuffle();
		posAux = freePosList.get(0);

		// Checks if the cell is going to die or not
		if (!dead) {
			// The new position is free
			if (!surface.getCell(posAux.getRow(), posAux.getColumn())) {
				surface.insertCell(posAux.getRow(), posAux.getColumn(), this);
				surface.deleteCell(pos.getRow(), pos.getColumn());

				steps = "Complex cell at (" + pos.getRow() + ", " + pos.getColumn() + ") moved to (" + posAux.getRow()
						+ ", " + posAux.getColumn() + ")" + System.getProperty("line.separator");
			} else {
				// In the new position there is an edible cell
				if (surface.edible(posAux)) {
					surface.deleteCell(posAux.getRow(), posAux.getColumn());
					this.not_fed--;
					surface.insertCell(posAux.getRow(), posAux.getColumn(), this);
					surface.deleteCell(pos.getRow(), pos.getColumn());

					steps = "Complex cell at (" + pos.getRow() + ", " + pos.getColumn() + ") moved to ("
							+ posAux.getRow() + ", " + posAux.getColumn() + ") and ate a simple cell."
							+ System.getProperty("line.separator");
				} else {
					posAux = null;
				}
			}
		} else { // The cell dies
			surface.deleteCell(pos.getRow(), pos.getColumn());
			posAux = null;

			steps = "Complex cell at (" + pos.getRow() + ", " + pos.getColumn() + ") dies burst."
					+ System.getProperty("line.separator");
		}
		surface.addNewMessageToConsoleMsg(steps);

		return posAux;
	}

	/*
	 * Returns false because complex cells are not edibles.
	 * 
	 * @see tp.pr2.logic.Cell#isEdible()
	 */
	public boolean isEdible() {
		// Complex cells are not edible
		return false;
	}

}
