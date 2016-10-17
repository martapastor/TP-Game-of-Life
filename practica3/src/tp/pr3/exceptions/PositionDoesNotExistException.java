package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class PositionDoesNotExistException extends Exception {
	public PositionDoesNotExistException () {
		super();
    }

    public PositionDoesNotExistException (String message) {
        super (message);
    }

    public PositionDoesNotExistException (Throwable cause) {
        super (cause);
    }

    public PositionDoesNotExistException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
