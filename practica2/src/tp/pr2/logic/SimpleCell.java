package tp.pr2.logic;

import tp.pr2.utils.Position;
import tp.pr2.utils.PosList;

//import java.util.Random;

import tp.pr2.logic.Surface;

/**
 * SimpleCell class
 * <p>
 * A cell is an object with inactivity steps counter and mature steps counter.
 */
public class SimpleCell extends Cell {

	/*
	 * MAX_STEPS_WITHOUT_MOVING is the maximun of steps the cell can live
	 * without moving to another position.
	 * 
	 * REPRODUCTION STEPS is the number of steps the cell has to give before
	 * becoming mature and dividing into two new inmature cells.
	 */
	private static final int MAX_STEPS_WITHOUT_MOVING = 3;
	private static final int REPRODUCTION_STEPS = 3;

	private String cell;
	private int inactivitySteps;
	private int matureSteps;

	/**
	 * Cell constructor
	 */
	public SimpleCell() {
		this.inactivitySteps = MAX_STEPS_WITHOUT_MOVING;
		this.matureSteps = REPRODUCTION_STEPS;
	}

	/*
	 * Sets the cells as we want to see them in the console.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		cell = inactivitySteps + "-" + matureSteps;

		return cell;
	}

	/**
	 * Lets us know the value of inactivitySteps of a cell from another class.
	 * 
	 * @return The remaining steps to die
	 */
	public int getInactivitySteps() {
		return this.inactivitySteps;
	}

	/**
	 * Lets us know the value of matureSteps of a cell from another class.
	 * 
	 * @return The remaining steps to maturate
	 */
	public int getMatureSteps() {
		return this.matureSteps;
	}

	/**
	 * Decreases the mature steps counter in 1 because a step has been
	 * performed.
	 */
	public void maturate() {
		this.matureSteps--;
	}

	/**
	 * Decreases the inactivity steps counter in 1 because the cell has not
	 * moved succesfully.
	 */
	public void inactivate() {
		this.inactivitySteps--;
	}

	// Methods for assignment 2

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr2.logic.Cell#executeMovement(tp.pr2.utils.Position,
	 * tp.pr2.logic.Surface)
	 */
	public Position executeMovement(Position pos, Surface surface) {
		String steps = "";
		// If the cell has free neighbour position where it can move, selects
		// randomly one of them. If it can't move, returns null.

		// PosList with the freeNeighbourPositions:
		PosList freePosList = surface.freeNeighbourPositions(pos.getRow(), pos.getColumn());

		// We discard the neighbour positions that have cells:
		for (int i = 0; i < freePosList.length(); i++) {
			Position posAux = freePosList.get(i);

			if (surface.getCell(posAux.getRow(), posAux.getColumn())) {
				freePosList.remove(i);

			}
		}

		// Selects one randomly:
		freePosList.shuffle();
		Position posAux = freePosList.get(0);

		// We check there are at least one free neighbour position:
		if (freePosList.length() > 0) {

			// The cell dies because of inactivity
			if (getInactivitySteps() == 0) {
				surface.deleteCell(pos.getRow(), pos.getColumn());

				steps = "Simple cell at (" + pos.getRow() + ", " + pos.getColumn() + ") dies of inactivity."
						+ System.getProperty("line.separator");
			}

			else {
				int r1 = pos.getRow();
				int c1 = pos.getColumn();
				int r2 = posAux.getRow();
				int c2 = posAux.getColumn();

				// The cell matures
				if (getMatureSteps() == 0) {
					surface.deleteCell(r1, c1);
					SimpleCell newCell_1 = new SimpleCell();
					SimpleCell newCell_2 = new SimpleCell();
					surface.insertCell(r1, c1, newCell_1);
					surface.insertCell(r2, c2, newCell_2);

					steps = "Simple cell at (" + r1 + ", " + c1 + ") moved to (" + r2 + ", " + c2 + ")"
							+ System.getProperty("line.separator") + "New cell born at (" + r1 + ", " + r2 + ")"
							+ System.getProperty("line.separator");
				}
				// The cell moves
				else {
					surface.insertCell(r2, c2, this);
					surface.deleteCell(r1, c1);
					// Decrease the matureSteps counter:
					this.maturate();

					steps = "Simple cell at (" + r1 + ", " + c1 + ") moved to (" + r2 + ", " + c2 + ")"
							+ System.getProperty("line.separator");
				}
			}

		}
		// If there are no free positions:
		else {
			int r = pos.getRow();
			int c = pos.getColumn();

			// The cell decreases their inactivity and mature
			// counters, but it does not die yet:
			if (this.getInactivitySteps() != 0 && this.getMatureSteps() != 0) {
				this.inactivate();
				this.maturate();
			}
			// The cell dies because of inactivity:
			else if (this.getInactivitySteps() == 0) {
				surface.deleteCell(r, c);

				steps += "Simple cell at (" + r + ", " + c + ") dies of inactivity."
						+ System.getProperty("line.separator");
			}
			// The cell dies on being unable to reproduce:
			else if (this.getMatureSteps() == 0) {
				surface.deleteCell(r, c);

				steps += "Simple cell at (" + r + ", " + c + ") dies on being unable to reproduce."
						+ System.getProperty("line.separator");
			}
		}
		surface.addNewMessageToConsoleMsg(steps);

		return posAux;
	}

	/*
	 * Returns true because simple cells are edibles.
	 * 
	 * @see tp.pr2.logic.Cell#isEdible()
	 */
	public boolean isEdible() {
		// Simple cells are edible
		return true;
	}

}
