package tp.pr3.logic.cell;

import tp.pr3.utils.Position;
import tp.pr3.utils.PosList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import tp.pr3.logic.Surface;

/**
 * Cell class
 * <p>
 * A cell is an object with inactivity steps counter and mature steps counter.
 */
public class SimpleCell implements Cell {

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

	/**
	 * SimpleCell constructor
	 * 
	 * @param ip
	 *            initial inactivity steps
	 * @param mp
	 *            initial mature steps
	 */
	public SimpleCell(int inactivitySteps, int matureSteps) {
		super();
		this.inactivitySteps = inactivitySteps;
		this.matureSteps = matureSteps;
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

	public Position executeMovement(Position originPos, Surface surface) {
		String steps = "";
		// If the cell has free neighbour position where it can move, selects
		// randomly one of them. If it can't move, returns null.

		// PosList with the freeNeighbourPositions:
		PosList freePosList = surface.freeNeighbourPositions(originPos);

		// We discard the neighbour positions that have cells:
		for (int i = 0; i < freePosList.length(); i++) {
			Position posAux = freePosList.get(i);

			if (surface.getCell(posAux)) {
				freePosList.remove(i);
			}
		}

		// Selects one randomly:
		freePosList.shuffle();
		Position finalPos = freePosList.get(0);

		// We check there are at least one free neighbour position:
		if (freePosList.length() > 0) {

			// The cell dies because of inactivity
			if (getInactivitySteps() == 0) {
				surface.deleteCell(originPos);

				steps = "Simple cell at (" + originPos.getRow() + ", " + originPos.getColumn() + ") dies of inactivity."
						+ System.getProperty("line.separator");
			}

			else {
				int r1 = originPos.getRow();
				int c1 = originPos.getColumn();
				int r2 = finalPos.getRow();
				int c2 = finalPos.getColumn();

				// The cell matures
				if (getMatureSteps() == 0) {
					surface.deleteCell(originPos);
					SimpleCell newCell_1 = new SimpleCell();
					SimpleCell newCell_2 = new SimpleCell();
					surface.insertCell(originPos, newCell_1);
					surface.insertCell(finalPos, newCell_2);

					steps = "Simple cell at (" + r1 + ", " + c1 + ") moved to (" + r2 + ", " + c2 + ")"
							+ System.getProperty("line.separator") + "New cell born at (" + r1 + ", " + r2 + ")"
							+ System.getProperty("line.separator");
				}
				// The cell moves
				else {
					surface.insertCell(finalPos, this);
					surface.deleteCell(originPos);

					// Decrease the matureSteps counter:
					this.maturate();

					steps = "Simple cell at (" + r1 + ", " + c1 + ") moved to (" + r2 + ", " + c2 + ")"
							+ System.getProperty("line.separator");
				}
			}

		}
		// If there are no free positions:
		else {
			int r = originPos.getRow();
			int c = originPos.getColumn();

			// The cell decreases their inactivity and mature
			// counters, but it does not die yet:
			if (getInactivitySteps() != 0 && getMatureSteps() != 0) {
				this.inactivate();
				this.maturate();
			}
			// The cell dies because of inactivity:
			else if (this.getInactivitySteps() == 0) {
				surface.deleteCell(originPos);

				steps += "Simple cell at (" + r + ", " + c + ") dies of inactivity."
						+ System.getProperty("line.separator");
			}
			// The cell dies on being unable to reproduce:
			else if (this.getMatureSteps() == 0) {
				surface.deleteCell(originPos);

				steps += "Simple cell at (" + r + ", " + c + ") dies on being unable to reproduce."
						+ System.getProperty("line.separator");
			}
		}
		surface.addNewMessageToConsoleMsg(steps);

		return finalPos;
	}

	public boolean isEdible() {
		// Simple cells are edible
		return true;
	}

	/**
	 * Loads a cell.
	 * 
	 * @param fileReader
	 *            The file from where we are loading.
	 * @return The cell loaded.
	 */
	public static SimpleCell load(Scanner fileReader) {
		int inactivitySteps = fileReader.nextInt();
		int matureSteps = fileReader.nextInt();
		return new SimpleCell(inactivitySteps, matureSteps);
	}

	/**
	 * Saves a cell.
	 * 
	 * @param fileReader
	 *            The file where we are saving the game.
	 * @return The cell saved.
	 */
	public void save(PrintWriter printWriter) throws IOException {
		printWriter.write("simple " + (new Integer(getInactivitySteps())).toString() + " "
				+ (new Integer(getMatureSteps())).toString());
	}
}
