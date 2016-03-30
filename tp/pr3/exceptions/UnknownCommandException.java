package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class UnknownCommandException extends Exception{
	public UnknownCommandException () {
		super();
    }

    public UnknownCommandException (String message) {
        super (message);
    }

    public UnknownCommandException (Throwable cause) {
        super (cause);
    }

    public UnknownCommandException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
