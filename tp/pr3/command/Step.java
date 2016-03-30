package tp.pr3.command;

import tp.pr3.controller.Controller;

public class Step implements Command{
	
	public void execute(Controller controller){
		controller.evolve(); 
	}
	
	public  Command parse(String[] commandString){
		Command step = new Step();
		if (commandString.length != 1){
			return null;
		} else {
			if (commandString[0].equals("step"))
				return step;
			}
		
		return null;
	}
	
	public  String helpText(){
		String help =(" STEP: executes a simulation step." + System.getProperty("line.separator"));
				  
		return help; 
	}
}
