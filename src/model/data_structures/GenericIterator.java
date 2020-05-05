package model.data_structures;

import java.util.Iterator;

public class GenericIterator<T extends Comparable<T>> implements Iterator<T> {
	private Nodo<T> current;
	
	public GenericIterator(IListaEnlazada<T> pKeys) {
		current = pKeys.darCabeza();
	}
	
	public boolean hasNext() {
		return current != null;
	}

	public T next() {
		T dato = current.darDato();
		current = current.darSiguiente();
		return dato;
	}
}
