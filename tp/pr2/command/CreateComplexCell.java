package tp.pr2.command;

import tp.pr2.logic.World;

public class CreateComplexCell extends Command {

	private int row;
	private int column;

	public CreateComplexCell() {
	}

	public CreateComplexCell(int r, int c) {
		this.row = r;
		this.column = c;
	}

	public void execute(World world) {
		if (world.createComplexCell(row, column)) {
			world.addNewMessageToConsoleMsg(
					"New complex cell created at (" + row + ", " + column + ")" + System.getProperty("line.separator"));
		}
	}

	public Command parse(String[] commandString) {
		if (commandString.length != 3) {
			return null;
		} else {
			if (commandString[0].equals("createcomplex")) {
				Command createComplexCell = new CreateComplexCell(Integer.parseInt(commandString[1]),
						Integer.parseInt(commandString[2]));
				return createComplexCell;
			}
		}
		return null;
	}

	public String helpText() {
		String ayuda = (" CREATECOMPLEX R C: create a new complex cell at position (r,c)"
				+ System.getProperty("line.separator"));
		return ayuda;
	}
}
