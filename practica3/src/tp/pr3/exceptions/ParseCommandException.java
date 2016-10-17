package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class ParseCommandException extends Exception {
	public ParseCommandException () {
		super();
    }

    public ParseCommandException (String message) {
        super (message);
    }

    public ParseCommandException (Throwable cause) {
        super (cause);
    }

    public ParseCommandException (String message, Throwable cause) {
        super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
