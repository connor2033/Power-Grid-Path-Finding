/**
 * @author Connor Haines
 * 
 * This is a simple class which defines an exception to be thrown by the pop and peek methods of the FindConnection class when a given stack is empty.
 */
public class EmptyStackException extends RuntimeException {
	/**
	 * This exception simply catches when a stack is empty and returns a basic string.
	 */
	public EmptyStackException(){
		super ("The stack is empty.");
	}
}
