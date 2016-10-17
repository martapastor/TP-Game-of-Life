package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class UnknownFileException extends Exception{
	public UnknownFileException () {
		super();
    }

    public UnknownFileException (String message) {
        super (message);
    }

    public UnknownFileException (Throwable cause) {
        super (cause);
    }

    public UnknownFileException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}