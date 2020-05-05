package model.data_structures;

import java.util.Iterator;;

public class STLink<K extends Comparable<K>, Vertice>{

	private K key;

	private Vertice vertice;

	private STLink<K, Vertice> next;


	public STLink(K pKey, Vertice pVal) {
		key = pKey;
		vertice = pVal;
		next = null;
	}

	public K getKey() {
		return key;
	}
	
	public Vertice getVertice(){
		return vertice;
	}
	
	public STLink<K, Vertice> darSiguiente(){
		return next;
	}
	
	public void cambiarSiguiente(STLink obj) {
		next = obj;
	}
}
