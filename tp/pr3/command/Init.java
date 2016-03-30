package tp.pr3.command;

import tp.pr3.controller.Controller;

public class Init implements Command {

	public void execute(Controller controller){
		controller.cleanWorld();
		controller.initGame();
	}
	
	public Command parse(String[] commandString){
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
		String help =(" INIT: restart the game."  + System.getProperty("line.separator"));
		return help; 
	}
}
