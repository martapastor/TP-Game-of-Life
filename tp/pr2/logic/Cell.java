package tp.pr2.logic;

import tp.pr2.utils.Position;
import tp.pr2.logic.Surface;

public abstract class Cell {
	protected boolean edible;

	/**
	 * Executes a move. Cells execute a step in their life cycle. They might
	 * die, move, reproduce, burst, eat...
	 * 
	 * @param pos
	 *            Origin coordinates
	 * @param surface
	 *            Surface instance
	 * @return the destination of the cell (or null if it couldn't move)
	 */
	public abstract Position executeMovement(Position pos, Surface surface);

	/**
	 * Returns if the cell is edible or not.
	 * 
	 * @return The edibility of the cell.
	 */
	public abstract boolean isEdible();
}
