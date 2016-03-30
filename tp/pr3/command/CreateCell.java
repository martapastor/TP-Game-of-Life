package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.cell.Cell;
import tp.pr3.logic.cell.SimpleCell;
import tp.pr3.logic.cell.ComplexCell;
import tp.pr3.utils.Position;
import tp.pr3.exceptions.ErrorWhileCreatingCellException;
import tp.pr3.exceptions.PositionDoesNotExistException;
import tp.pr3.exceptions.UnknownWorldTypeException;

public class CreateCell implements Command {

	private Position finalPos;
	private int row;
	private int column;

	/**
	 * CreateCell constructor (without arguments)
	 */
	public CreateCell() {
	}

	/**
	 * CreateCell constructor (with arguments)
	 * 
	 * @param pos
	 *            The position where we want to create the cell.
	 */
	public CreateCell(Position pos) {
		this.finalPos = pos;
		this.row = pos.getRow();
		this.column = pos.getColumn();
	}

	public void execute(Controller controller)
			throws PositionDoesNotExistException, UnknownWorldTypeException, ErrorWhileCreatingCellException {
		Cell cell;

		// The position introduced does not exist:
		if (this.row < 0 || this.row >= controller.getRows() || this.column < 0
				|| this.column >= controller.getColumns())
			throw new PositionDoesNotExistException("ERROR: The position introduced does not exist.");

		// The position introduced is already occupied:
		if (controller.getCell(finalPos))
			throw new ErrorWhileCreatingCellException("ERROR: The position is already occupied.");

		// At this point, we have already check that the pos is free:
		if (controller.isSimpleWorld()) {
			cell = new SimpleCell();
		} else if (!controller.isSimpleWorld()) {
			String command;

			// Asks us for a command through the screen:
			controller.printMessage("Which type of cell do you want to create? -> ");

			// Reads the request of command we have typed and ignore capital
			// letters:
			command = controller.readInputFromConsole();

			if (command.equals("simple")) {
				cell = new SimpleCell();
			} else if (command.equals("complex")) {
				cell = new ComplexCell();
			} else {
				throw new ErrorWhileCreatingCellException("ERROR: That type of cell does not exist.");
			}
		} else {
			throw new UnknownWorldTypeException("ERROR: The type of world introduced is not valid.");
		}

		// The cell has been successfully created:
		if (controller.createCell(this.finalPos, cell)) {
			controller.addNewMessageToConsoleMsg(
					"Cell created at " + finalPos + "." + System.getProperty("line.separator"));
		}
	}

	public Command parse(String[] commandString) {
		if (commandString.length != 3) {
			return null;
		} else {
			if (commandString[0].equals("create")) {
				Position pos = new Position(Integer.parseInt(commandString[1]), Integer.parseInt(commandString[2]));

				Command createCell = new CreateCell(pos);
				return createCell;
			}
		}

		return null;
	}

	public String helpText() {
		String help = (" CREATE R C: create a new cell at position (r,c)" + System.getProperty("line.separator"));
		return help;
	}

}
