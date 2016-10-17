package tp.pr1.logic;

/**
 * Cell class
 * <p>
 * A cell is an object with inactivity steps counter and mature steps counter.
 * 
 * @author David Calleja and Marta Pastor
 * @version 1.0
 */
public class Cell {

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
	public Cell() {
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

}
