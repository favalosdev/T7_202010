package model.data_structures;

import java.util.Iterator;

public class GenericIterator<T> implements Iterator<T> {
	
	private LinkedQueue<T> queue;
	
	public GenericIterator(LinkedQueue<T> pQueue) {
		queue = pQueue;
	}
	
	public boolean hasNext() {
		return queue.isEmpty();
	}
	
	public T next() {
		return queue.dequeue();
	}
}
