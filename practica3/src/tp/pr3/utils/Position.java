package tp.pr3.utils;

/**
 * Position class
 * <p>
 * A position is an object with the row and the column in which is located a
 * cell in the surface.
 */
public class Position {
	private int row;
	private int column;

	
	public Position() {
	}
	
	/**
	 * Position constructor
	 * 
	 * @param row
	 *            The row in the surface where is located a cell
	 * @param column
	 *            The column in the surface where is located a cell
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Accesor method that returns the row where is the cell.
	 * 
	 * @return The number of row where is located the cell
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Accesor method that returns the column chere is the cell.
	 * 
	 * @return The number of column where is located the cell
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * Mutator method that saves the value of the row passed as parameter.
	 * 
	 * @param row
	 *            The row where is the cell
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Mutator method that saves the value of the column passed as parameter.
	 * 
	 * @param column
	 *            The colum where is the cell
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	public String toString() {
		String pos;
		
		pos = "(" + row + ", " + column + ")";

		return pos;
	}
}
