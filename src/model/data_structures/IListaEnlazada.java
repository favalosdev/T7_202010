package model.data_structures;

public interface IListaEnlazada<T extends Comparable<T>> {
	
	public void agregar(T dato);
	
	public T buscar(T dato);
	
	public Nodo<T> darCabeza();
	
	public Nodo<T> darPuntero();
	
	public int darTamanio();
}
