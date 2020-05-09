package model.data_structures;

public class Nodo<T> {
	
	private T dato;
	
	private Nodo<T> siguiente;
	
	public Nodo(T obj) {
		dato = obj;
		siguiente = null;
	}
	
	public Nodo<T> darSiguiente() {
		return siguiente;
	}
	
	public void cambiarSiguiente(Nodo<T> obj) {
		siguiente = obj;
	}
	
	public T darDato() {
		return dato;
	}
}