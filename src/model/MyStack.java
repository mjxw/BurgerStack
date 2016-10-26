package model;

/**
 * This class represents a stack. A client can add, remove, and check the value
 * from the top of the stack. Additionally, a client can check the size of the stack 
 * or whether or not it is empty. 
 * 
 * @author Matthew Wu
 * @version 1.0 
 * @param <T>
 */
public class MyStack<T> {

	private Node<T> myTop; // reference to the top of the stack 
	private int mySize; // reference to the size 

	/**
	 * Construct an empty stack object. 
	 */
	public MyStack() {
		myTop = null;
		mySize = 0;
	}
	
	/**
	 * Checks if the stack is empty.
	 * @return a boolean representing whether or not the stack is empty 
	 */
	public boolean isEmpty() {
		return mySize == 0;
	}
	
	/**
	 * Adds an item to the top of the stack. 
	 * @param item is the item type to be added 
	 */
	public void push(T item) {
		if (isEmpty()) {
			myTop = new Node<T>(item); // Start a stack if initially empty 
//			myTop.setNext(null);
		} else {
			Node<T> temp = new Node<T>(item); 
			temp.setNext(myTop);
			myTop = temp; // Change what myTop is referencing to update 'stack position' 
		}
		mySize++; 
	}
	
	/**
	 * Removes the item at the top of the stack and returns it.
	 * @return the item just removed 
	 */
	public T pop() {
		if (isEmpty()) {		
			return null;
		}
		Node<T> temp = myTop;
		myTop = myTop.next(); // Shift the myTop to next
		mySize--;
		return temp.item();
	}
	
	/**
	 * Checks the item at the top of the stack.
	 * @return the item at the top of the stack 
	 */
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return myTop.item();
	}
	
	/**
	 * Returns the size of the stack. 
	 * @return an integer representing the top of the stack 
	 */
	public int size() {
		return mySize;
	}

	/**
	 * Makes the stack a string for displaying. 
	 * @return a string representation of the stack
	 */
	public String toString() {
		StringBuilder result = new StringBuilder(); 
		result.append("[");
		Node<T> temp = myTop;
		if (temp != null) { // helps with empty stack case 
			result.append(myTop.item());
			temp = temp.next();
		}
		while (temp != null) {
			result.append(", " + temp.item());
			temp = temp.next();
		}
		result.append("]");
		return result.toString();
	}
	
	/**
	 * This class represents a node. A node points to another node.
	 * @author Matthew Wu
	 *
	 * @param <T>
	 */
	public class Node<T> {
		T myItem; // Reference to the item at this node
		Node<T> myNext; // Reference to the next node 
		
		/**
		 * Constructs a node with specific item.
		 * @param item is the node's item
		 */
		public Node(T item) {
			myItem = item; 
		}
		
		/**
		 * This method sets the pointer to the next node.
		 * @param next is the next node
		 */
		public void setNext(Node<T> next) {
			myNext = next; 
		}
		
		/**
		 * This method returns what the next node is. 
		 * @return the next node
		 */
		public Node<T> next() {
			return myNext;
		}
		
		/**
		 * This method returns what the item is at this node.
		 * @return this node's item
		 */
		public T item() {
			return myItem;
		}
	}
}
