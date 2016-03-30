package tp.pr2.command;

import tp.pr2.logic.World;

public class CreateSimpleCell extends Command {
	
	private int row;
	private int column;
	
	public CreateSimpleCell(){
	}
	
	public CreateSimpleCell(int r, int c){
		this.row = r;
		this.column = c;
	}
	public void execute(World world){
		if (world.createSimpleCell(row, column)) {
			world.addNewMessageToConsoleMsg("New simple cell created at (" + row + ", " + column + ")" + System.getProperty("line.separator"));
		}
	}
	
	public Command parse(String[] commandString){	
		if (commandString.length != 3){
			return null;
		} else {
			if (commandString[0].equals("createsimple")){
				Command createSimpleCell = new CreateSimpleCell(Integer.parseInt(commandString[1]), Integer.parseInt(commandString[2]));
				return createSimpleCell;
			}
		}
		
		return null;
	}
	
	public  String helpText(){
		String ayuda =(" CREATESIMPLE R C: create a new simple cell at position (r,c)." + System.getProperty("line.separator"));
		return ayuda; 
	}
}
