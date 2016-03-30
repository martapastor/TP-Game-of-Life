package tp.pr3;

import tp.pr3.controller.Controller;

/**
 * Main class
 * <p>
 * Contains starting function.
 * 
 * @author Marta Pastor
 * @version 3.0
 */
public class Main {

	/**
	 * Creates a controller and starts the simulation.
	 * 
	 * @param args
	 *            Arguments ignored
	 */
	public static void main(String[] args) {
		//Creates a Controller object and executes the simulation:
		Controller controller = new Controller();
		controller.executeSimulation();
	}

}