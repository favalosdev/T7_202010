package model.data_structures;

import java.util.Iterator;


/**
 * 
 * @author fercoder
 * A heavily modified and ugly version of SequentialSearchST
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<K extends Comparable<K>, Vertice>  {
	private STLink head;
	private int size;
	private STLink puntero;
	
	SequentialSearchST() {
		head = null;;
		size = 0;
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public void put(K pKey, Vertice pVal) {
		if (head == null) {

			head = new STLink(pKey, pVal);
			puntero = head;

		} else {

			puntero.cambiarSiguiente(new STLink(pKey, pVal));

			puntero = puntero.darSiguiente();

			puntero.cambiarSiguiente(null);
		}
		size++;
	}

	public Vertice getVertice(K pKey) {
		STLink current = head;

		while (current != null) {	
			if (current.getKey().compareTo(pKey) == 0) return (Vertice) current.getVertice();
			current = current.darSiguiente();
		}

		return null;
	}

	public void remove(K pKey) {
		if (head.getKey().compareTo(pKey) == 0) {
			head = head.darSiguiente();
			size--;
		} else {

			STLink actual = head;
			STLink backtrack = null;

			while (actual != null) {

				if (actual.getKey().compareTo(pKey) == 0) {

					backtrack.cambiarSiguiente(actual.darSiguiente());
					if (actual  == puntero) puntero = backtrack;

					size--;
				}

				backtrack = actual;
				actual = actual.darSiguiente();
			}
		}
	}
	
	public Iterator<K> keys() {
		IListaEnlazada<K> keys = new ListaEnlazada<K>();
		STLink current = head;
		
		while (current != null) {
			keys.agregar((K) current.getKey());
			current = current.darSiguiente();
		}
		
		return new GenericIterator<K>(keys);
	}
	
	public STLink getHead() {
		return head;
	}
}