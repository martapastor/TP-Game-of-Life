package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.ErrorWhileDeletingCellException;
import tp.pr3.exceptions.PositionDoesNotExistException;
//import tp.pr3.logic.World;
import tp.pr3.utils.Position;

public class DeleteCell implements Command {

	private Position originPos;
	private int row;
	private int column;

	/**
	 * DeleteCell constructor (without arguments)
	 */
	public DeleteCell() {
	}

	/**
	 * DeleteCell constructor (with arguments)
	 * 
	 * @param pos
	 *            The position from where we want to delete the cell.
	 */
	public DeleteCell(Position pos) {
		this.originPos = pos;
		this.row = pos.getRow();
		this.column = pos.getColumn();
	}

	public void execute(Controller controller) throws PositionDoesNotExistException, ErrorWhileDeletingCellException {
		// If the position does not exist:
		if (this.row < 0 || this.row >= controller.getRows() || this.column < 0
				|| this.column >= controller.getColumns())
			throw new PositionDoesNotExistException("ERROR: The position introduced does not exist.");

		// If there is no cell in the introduced position:
		if (!controller.getCell(originPos))
			throw new ErrorWhileDeletingCellException("ERROR: The position has no cell to delete.");

		// The cell has been successfully deleted:
		if (controller.deleteCell(this.originPos)) {
			controller.addNewMessageToConsoleMsg(
					"Cell deleted at " + originPos + "." + System.getProperty("line.separator"));
		}
	}

	public Command parse(String[] commandString) {
		if (commandString.length != 3) {
			return null;
		} else {
			if (commandString[0].equals("delete")) {
				Position pos = new Position(Integer.parseInt(commandString[1]), Integer.parseInt(commandString[2]));

				Command deleteCell = new DeleteCell(pos);
				return deleteCell;
			}
		}
		return null;
	}

	public String helpText() {
		String help = (" DELETE R C: delete the cell at position (r,c)." + System.getProperty("line.separator"));
		return help;
	}
}
