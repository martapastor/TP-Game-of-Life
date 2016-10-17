package tp.pr1;

import tp.pr1.controller.Controller;

/**
 * Main class
 * <p>
 * Contains starting function.
 * 
 * @author Marta Pastor
 * @version 1.0
 */
public class Main {

	/**
	 * Creates a controller and starts the simulation.
	 * 
	 * @param args
	 *            Arguments ignored
	 */
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.executeSimulation();
	}

}