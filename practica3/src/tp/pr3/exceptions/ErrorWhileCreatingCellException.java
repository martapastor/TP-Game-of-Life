package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class ErrorWhileCreatingCellException extends Exception {
	public ErrorWhileCreatingCellException () {
		super();
    }

    public ErrorWhileCreatingCellException (String message) {
        super (message);
    }

    public ErrorWhileCreatingCellException (Throwable cause) {
        super (cause);
    }

    public ErrorWhileCreatingCellException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
