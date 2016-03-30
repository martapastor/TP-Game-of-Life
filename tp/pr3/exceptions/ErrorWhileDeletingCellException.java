package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class ErrorWhileDeletingCellException extends Exception {
	public ErrorWhileDeletingCellException () {
		super();
    }

    public ErrorWhileDeletingCellException (String message) {
        super (message);
    }

    public ErrorWhileDeletingCellException (Throwable cause) {
        super (cause);
    }

    public ErrorWhileDeletingCellException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
