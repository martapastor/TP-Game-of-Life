package tp.pr2.command;

import tp.pr2.logic.World;

public class Exit extends Command {
	public void execute(World world){
		world.setSimulationFinished(); 
	}
	
	public  Command parse(String[] commandString){
		Command exit = new Exit();
		if (commandString.length != 1){
			return null;
		} else {
			if (commandString[0].equals("exit")){
				return exit;
			}
		}
		return null;
	}
	
	public  String helpText(){
		String ayuda =(" EXIT: exit the game."  + System.getProperty("line.separator"));
		return ayuda; 
	}
}
