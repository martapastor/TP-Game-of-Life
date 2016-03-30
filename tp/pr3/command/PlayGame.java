package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.world.World;
import tp.pr3.logic.world.SimpleWorld;
import tp.pr3.logic.world.ComplexWorld;

import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.exceptions.InitialisationException;

public class PlayGame implements Command {

	private World world;

	/**
	 * PlayGame constructor (without arguments)
	 */
	public PlayGame() {

	}

	/**
	 * PlayGame constructor (with arguments)
	 * 
	 * @param world
	 *            The world in which we are playing
	 */
	public PlayGame(World world) {
		this.world = world;
	}

	public void execute(Controller controller) {
		controller.play(world);
	}

	public Command parse(String[] commandString) throws UnknownWorldTypeException, InitialisationException {
		if (commandString.length > 6 || commandString.length < 5) {
			return null;
		} else {
			if (commandString[0].equals("play")) {
				if (commandString[1].equals("simple")) {
					if (Integer.parseInt(commandString[4]) > Integer.parseInt(commandString[2])
							* Integer.parseInt(commandString[3]))
						throw new InitialisationException(
								"ERROR: Cannot create " + Integer.parseInt(commandString[4]) + " cells on a "
										+ Integer.parseInt(commandString[2]) + "x" + Integer.parseInt(commandString[3])
										+ " board." + System.getProperty("line.separator"));

					World world = new SimpleWorld(Integer.parseInt(commandString[2]),
							Integer.parseInt(commandString[3]), Integer.parseInt(commandString[4]));

					Command playGame = new PlayGame(world);
					return playGame;

				} else if (commandString[1].equals("complex")) {
					if (Integer.parseInt(commandString[5])
							+ Integer.parseInt(commandString[4]) > Integer.parseInt(commandString[2])
									* Integer.parseInt(commandString[3]))
						throw new InitialisationException("ERROR: Cannot create "
								+ (Integer.parseInt(commandString[5]) + Integer.parseInt(commandString[4]))
								+ " cells on a " + Integer.parseInt(commandString[2]) + "x"
								+ Integer.parseInt(commandString[3]) + " board."
								+ System.getProperty("line.separator"));

					World world = new ComplexWorld(Integer.parseInt(commandString[2]),
							Integer.parseInt(commandString[3]), Integer.parseInt(commandString[4]),
							Integer.parseInt(commandString[5]));

					Command playGame = new PlayGame(world);
					return playGame;
				} else {
					throw new UnknownWorldTypeException("ERROR: Unknown world type: " + commandString[1]);
				}
			}
		}
		return null;
	}

	public String helpText() {
		String help = (" PLAY SIMPLE R C SC: initialises a new simple world of dimensions RxC with SC simple cells."
				+ System.getProperty("line.separator")
				+ " PLAY COMPLEX R C SC CC: initialises a new complex world of dimensions RxC with SC simple cells and CC complex cells.");
		return help;
	}

}
