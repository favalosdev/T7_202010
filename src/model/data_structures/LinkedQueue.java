package model.data_structures;

import java.util.Iterator;

public class LinkedQueue<T> implements Iterable<T> {

	public class Node {
		private T value;
		private Node next;
		
		public Node(T pValue) {
			value = pValue;
			next = null;
		}
		
		public T getValue() {
			return value;
		}
		
		public Node getNext() {
			return next;
		}
		
		public void setNext(Node pNext) {
			next = pNext;
		}
	}
	
	Node head, pointer;
	
	public LinkedQueue() {
		head = null;
		pointer = null;
	}
	
	public void enqueue(T obj) {
		Node newPointer = new Node(obj);
		
		if (isEmpty()) {
			head = newPointer;
			pointer = head;
		} else {
			pointer.setNext(newPointer);
			pointer = newPointer;
		}
	}
	
	public T dequeue() {
		if (isEmpty()) return null;
		T deleted = head.getValue();
		head = head.getNext();
		return deleted;
	}

	public boolean isEmpty() {
		return head == null;
	}
	
	public Node getHead() {
		return head;
	}
	
	public Iterator<T> iterator() {
		return new GenericIterator<T>(this);
	}
}