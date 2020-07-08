/**
 * @author Connor Haines
 * 
 * This class defines what a "stack" is and supplies various methods for implementing stacks in code such as the FindConnections class.
 */
public class ArrayStack<T> implements ArrayStackADT<T> {
	
	
	/*
	 * "stack" is the main object created for this class.
	 * 
	 * "top" is an integer variable used to track the index of the top dataItem of the stack.
	 * 
	 * "DEFAULT_CAPACITY" is the initial default size of any new stack. 
	 */
	private T[] stack;
	private int top;
	private final int DEFAULT_CAPACITY = 20;
	
	/**
	 * A new stack is created with a default size of 20 (DEFAULT_CAPACITY).
	 */
	public ArrayStack() {
		top = -1;
		stack = (T[]) (new Object[DEFAULT_CAPACITY]);
	}
	
	/**
	 * @param initialCapacity a new stack is created with a size based on this parameter.
	 */
	public ArrayStack(int initialCapacity) {
		top = -1;
		stack = (T[]) (new Object[initialCapacity]);
	}
	
	/**  Adds one element to the top of this stack and increases the size of stack if full.
	 *   @param dataItem data item to be pushed onto stack
	 */
	public void push (T dataItem) {
		
		
		if (stack.length < 100 && stack.length == top +1) {
			T[] newStack = (T[]) (new Object[stack.length * 2]);
			for (int i = 0; i < top+1; i++) {
				newStack[i] = stack[i];
			}
			
			stack = newStack;
		}
		
		else if (stack.length == top +1) {
			T[] newStack = (T[]) (new Object[stack.length + 50]);
			for (int i = 0; i < top+1; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
		}
		
		top++;
		stack[top] = dataItem;

	}
	
	/**  Removes and returns the top element from this stack. 
	 *   @return data item removed from the top of the stack
	 *   
	 *   @throws EmptyStackException when attempting to pop a non existent dataItem.
	 */
	public T pop() throws EmptyStackException{
		if (isEmpty())
			throw new EmptyStackException();
		
		T result = stack[top];
		stack[top] = null;
		top--;
		
		if (top+1 < stack.length/3 && stack.length > 20) {
			T[] newStack = (T[]) (new Object[stack.length / 2]);
			for (int i = 0; i < top+1; i++) {
				newStack[i] = stack[i];
			}
			
			stack = newStack;
		}
		
		return result;
			
	}
	
	/**
	 * Returns the data item on the top of the stack without removing it. 
	 * @return data item on top of the stack
	 * 
	 * @throws EmptyStackException when attempting to peek at a non existent dataItem.
	 */
	public T peek() throws EmptyStackException{
		
		if (isEmpty())
			throw new EmptyStackException();
		
		return stack[top];
	}
	
	/**
	 * Returns true if the stack is empty/contains no elements.
	 * @return true if the stack is empty; false otherwise 
	 */
	public boolean isEmpty() {
		return (top == -1);
			
	}
	
	/**
	 * Returns the number of data items currently in the stack.
	 * @return int number of data items in this stack
	 */
	public int size() {
		return top+1;
	}
	
	/**
	 * Returns the total length of the stack.
	 * @return int number of total length.
	 */
	public int length() {
		return stack.length;
	}
	
	/** Returns a string representation of this stack in the specified format for the assignment.
	  * @return String representation of this stack
	  */
	public String toString(){
		String resultString = "Stack: ";
				
		for (int i = 0; i < top; i++) {
			resultString += stack[i].toString() + ", ";
		}
		resultString += stack[top].toString();
		
		return resultString;
	}
}
