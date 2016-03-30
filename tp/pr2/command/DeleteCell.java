package tp.pr2.command;

import tp.pr2.logic.World;

public class DeleteCell extends Command {
	
	private int row;
	private int column;
	
	public DeleteCell(){
	}
	
	public DeleteCell(int r, int c){
		this.row = r;
		this.column = c;
	}
	
	public void execute(World world){
		if (world.deleteCell(row, column)) {
			world.addNewMessageToConsoleMsg("Cell deleted at (" + row + ", " + column + ")" + System.getProperty("line.separator"));
		}
	}
	
	public  Command parse(String[] commandString){
		if (commandString.length != 3){
			return null;
		} else {
			if (commandString[0].equals("delete")){
				Command deleteCell = new DeleteCell(Integer.parseInt(commandString[1]), Integer.parseInt(commandString[2]));
				return deleteCell;
			}
		}
		return null;
	}
	
	public  String helpText(){
		String ayuda =(" DELETE F C: delete the cell at position (r,c)." + System.getProperty("line.separator"));
		return ayuda; 
	}
}
