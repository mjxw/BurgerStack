/*
 * Matthew Wu
 * TCSS 342
 * Baron Burger 
 */
package model;

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
