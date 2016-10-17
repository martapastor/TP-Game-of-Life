package tp.pr2.command;

import tp.pr2.logic.World;

public class Clean extends Command {
	
	public void execute(World world){
		world.cleanWorld(); 
	}
	
	public  Command parse(String[] commandString){
		Command clean = new Clean();
		if (commandString.length != 1){
			return null;
		} else {
			if (commandString[0].equals("clean")){
				return clean;
			}
		}
		
		return null;
	}
	
	public  String helpText(){
		String ayuda =(" CLEAN: delete all the cells." + System.getProperty("line.separator"));
		return ayuda; 
	}
}
