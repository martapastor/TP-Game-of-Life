package tp.pr3.logic.cell;

import tp.pr3.utils.Position;
import tp.pr3.utils.PosList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import tp.pr3.logic.Surface;

public class ComplexCell implements Cell {
	public static final int MAX_EAT = 3;

	private String cell;
	private int not_fed;

	public ComplexCell() {
		not_fed = MAX_EAT;
	}

	/**
	 * ComplexCell constructor
	 * 
	 * @param not_fed
	 *            Initial eat capacity
	 */
	public ComplexCell(int not_fed) {
		super();
		this.not_fed = not_fed;
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

	public int getNotFedSteps() {
		return this.not_fed;
	}

	public void decreaseNotFedSteps() {
		this.not_fed--;
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

	public Position executeMovement(Position originPos, Surface surface) {
		String steps = "";
		boolean dead = this.mustDie();
		Position finalPos;

		PosList freePosList = surface.freeNeighbourPositions(originPos);

		freePosList.shuffle();
		finalPos = freePosList.get(0);

		// Checks if the cell is going to die or not
		if (!dead) {
			// The new position is free
			if (!surface.getCell(finalPos)) {
				surface.insertCell(finalPos, this);
				surface.deleteCell(originPos);

				steps = "Complex cell at (" + originPos.getRow() + ", " + originPos.getColumn() + ") moved to ("
						+ finalPos.getRow() + ", " + finalPos.getColumn() + ")" + System.getProperty("line.separator");
			} else {
				// In the new position there is an edible cell
				if (surface.edible(finalPos)) {
					surface.deleteCell(finalPos);
					decreaseNotFedSteps();
					surface.insertCell(finalPos, this);
					surface.deleteCell(originPos);

					steps = "Complex cell at (" + originPos.getRow() + ", " + originPos.getColumn() + ") moved to ("
							+ finalPos.getRow() + ", " + finalPos.getColumn() + ") and ate a simple cell."
							+ System.getProperty("line.separator");
				} else {
					finalPos = null;
				}
			}
		} else { // The cell dies
			surface.deleteCell(originPos);
			finalPos = null;

			steps = "Complex cell at (" + originPos.getRow() + ", " + originPos.getColumn() + ") dies burst."
					+ System.getProperty("line.separator");
		}
		surface.addNewMessageToConsoleMsg(steps);

		return finalPos;
	}

	public boolean isEdible() {
		// Complex cells are not edible
		return false;
	}

	/**
	 * Loads a cell.
	 * 
	 * @param fileReader
	 *            The file from where we are loading.
	 * @return The cell loaded.
	 */
	public static ComplexCell load(Scanner fileReader) {
		int ec = fileReader.nextInt();
		fileReader.nextLine();
		return new ComplexCell(ec);
	}

	/**
	 * Saves a cell.
	 * 
	 * @param fileReader
	 *            The file where we are saving the game.
	 * @return The cell saved.
	 */
	public void save(PrintWriter printWriter) throws IOException {
		printWriter.write("complex " + (new Integer(getNotFedSteps())).toString());
	}

}
