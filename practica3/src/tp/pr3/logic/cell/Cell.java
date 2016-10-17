package tp.pr3.logic.cell;

import tp.pr3.utils.Position;

import java.io.IOException;
import java.io.PrintWriter;

import tp.pr3.logic.Surface;

public interface Cell {

	/**
	 * Executes a move.
	 * <p>
	 * Cells execute a step in their life cycle. They might die, move,
	 * reproduce, burst, eat...
	 * 
	 * @param pos
	 *            Origin coordinates
	 * @param surface
	 *            Surface instance
	 * @return The destination of the cell (or null if it couldn't move)
	 */
	public Position executeMovement(Position pos, Surface surface);

	/**
	 * Returns if the cell is edible or not.
	 * 
	 * @return The edibility of the cell.
	 */
	public boolean isEdible();

	public abstract void save(PrintWriter file) throws IOException;
}
