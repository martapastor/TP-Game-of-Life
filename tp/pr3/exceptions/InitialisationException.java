package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class InitialisationException extends Exception {
	public InitialisationException () {
		super();
    }

    public InitialisationException (String message) {
        super (message);
    }

    public InitialisationException (Throwable cause) {
        super (cause);
    }

    public InitialisationException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
