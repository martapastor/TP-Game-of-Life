package tp.pr2.command;

import tp.pr2.logic.World;

public class Init extends Command {

	public void execute(World world){
		world.cleanWorld();
		world.initWorld();
	}
	
	public  Command parse(String[] commandString){
		Command init = new Init();
		if (commandString.length != 1){
			return null;
		} else {
			if (commandString[0].equals("init")){
				return init;
			}
		}
		
		return null;
	}
	
	public  String helpText(){
		String ayuda =(" INIT: restart the game."  + System.getProperty("line.separator"));
		return ayuda; 
	}
}
