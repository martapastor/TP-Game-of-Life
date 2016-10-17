package tp.pr3.command;

import tp.pr3.controller.Controller;
//import tp.pr3.logic.World;

public class Exit implements Command {
	public void execute(Controller controller){
		controller.simulationTerminated(); 
	}
	
	public Command parse(String[] commandString){
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
		String help =(" EXIT: exit the game."  + System.getProperty("line.separator"));
		return help; 
	}
}
