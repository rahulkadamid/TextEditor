package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		this.add(size, element);
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index < 0 | index >= size){
			throw new IndexOutOfBoundsException();
		}
	
		LLNode<E> pointer = head; 
		for (int i = 0; i <= index; i++){
			pointer = pointer.next;
		}
		return pointer.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		LLNode<E> newNode = new LLNode<E>(element);
		
		if(index < 0 | index > size){
			throw new IndexOutOfBoundsException();
		}
		
		if(element == null){
			throw new NullPointerException();
		}
	
		LLNode<E> pointer = head; 
		for (int i = 0; i <= index; i++){
			pointer = pointer.next;
		}
		
		pointer.prev.next = newNode;
		newNode.next = pointer; 
		newNode.prev = pointer.prev;
		pointer.prev = newNode;
		
		this.size++;
		
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index < 0 | index >= size){
			throw new IndexOutOfBoundsException();
		}
	
		LLNode<E> pointer = head; 
		for (int i = 0; i <= index; i++){
			pointer = pointer.next;
		}
		
		pointer.prev.next = pointer.next;
		pointer.next.prev = pointer.prev;
		
		this.size--;
		
		return pointer.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(index < 0 | index >= size){
			throw new IndexOutOfBoundsException();
		}
		
		if (element == null){
			throw new NullPointerException();
		}
	
		LLNode<E> pointer = head; 
		for (int i = 0; i <= index; i++){
			pointer = pointer.next;
		}
		
		E oldValue = pointer.data;
	
		pointer.data = element;
		
		return oldValue;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
