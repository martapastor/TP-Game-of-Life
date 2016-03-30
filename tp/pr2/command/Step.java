package tp.pr2.command;

import tp.pr2.logic.World;

public class Step extends Command{
	
	public void execute(World world){
		world.evolve(); 
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
		String ayuda =(" STEP: executes a simulation step." + System.getProperty("line.separator"));
				  
		return ayuda; 
	}
}
