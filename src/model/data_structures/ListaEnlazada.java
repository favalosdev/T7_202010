package model.data_structures;

import java.util.Iterator;

public class ListaEnlazada<T extends Comparable<T>> implements Iterable<T> {

	private Nodo<T> cabeza;


	// Este atributo se usa para siempre apuntar al �ltimo elemento

	private Nodo<T> puntero;

	private int tamanio;


	public ListaEnlazada(T obj) {

		cabeza = new Nodo<T>(obj);
		puntero = cabeza;
		tamanio = 1;
	}

	public ListaEnlazada() {
		cabeza = null;
		puntero = null;
		tamanio = 0;
	}

	public int darTamanio() {
		return tamanio;
	}


	// TODO implementar agregar

	public void agregar(T obj) {

		if (cabeza == null) {

			cabeza = new Nodo<T>(obj);
			puntero = cabeza;

		} else {

			puntero.cambiarSiguiente(new Nodo<T>(obj));

			puntero = puntero.darSiguiente();

			puntero.cambiarSiguiente(null);
		}


		tamanio++;
	}
	
	
	// Este agregar es solo para listas ordenadas
	
	public void agregarEnOrden(T obj) {
		
		
		Nodo<T> n = new Nodo<T>(obj);
		
		if (cabeza == null) {
			cabeza = n;
		} else if (obj.compareTo(cabeza.darDato()) < 0) {
			n.cambiarSiguiente(cabeza);
			cabeza = n;
		} else if (obj.compareTo(cabeza.darDato()) > 0 ) {
			
			Nodo<T> anterior = cabeza;
			Nodo<T> actual = cabeza.darSiguiente();
			
			
			while (actual != null && obj.compareTo(actual.darDato()) > 0) {
				anterior = actual;
				actual = actual.darSiguiente();
			}
			
			if (actual == null || obj.compareTo(actual.darDato())<0)
			{
				anterior.cambiarSiguiente(n);
				n.cambiarSiguiente(actual);
			}
		}
		tamanio ++;
	}
	

	// TODO implementar buscar

	public T buscar (int pos) {

		// Verificar que la posici�n requerida est� dentro del rango

		if (pos >= 0 && pos < tamanio) {

			Nodo<T> actual = cabeza;

			int contador = 0;

			while (contador < pos) {
				actual = actual.darSiguiente();
			}

			return actual.darDato();
		}

		return null;
	}

	public T buscar (T dato) {

		Nodo<T> actual = cabeza;

		while (actual != null) {

			if (actual.darDato().compareTo(dato) == 0) return actual.darDato();

			actual = actual.darSiguiente();
		}

		return null;
	}

	public T eliminar (T dato) {

		if (cabeza.darDato().compareTo(dato) == 0) {
			T retorno = cabeza.darDato();
			cabeza = cabeza.darSiguiente();
			tamanio--;

			return retorno;
		} else {

			Nodo<T> actual = cabeza;
			Nodo<T> backtrack = null;

			while (actual != null) {

				if (actual.darDato().compareTo(dato) == 0) {

					backtrack.cambiarSiguiente(actual.darSiguiente());
					if (actual  == puntero) puntero = backtrack;

					tamanio--;
					return actual.darDato();
				}

				backtrack = actual;
				actual = actual.darSiguiente();
			}


			return null;
		}
	}

	public T eliminar(int pos) {

		if (pos >= 0 && pos < tamanio) {


			Nodo<T> actual = cabeza;
			Nodo<T> backtrack = null;

			if (pos == 0) {
				cabeza = cabeza.darSiguiente();
				tamanio--;
				return actual.darDato();
			} else {

				int contador = 0;

				while (contador < pos) {

					backtrack = actual;
					actual = actual.darSiguiente();
					contador++;
				}

				backtrack.cambiarSiguiente(actual.darSiguiente());

				if (contador == tamanio - 1) puntero = backtrack;

				tamanio--;

				return actual.darDato();
			}
		}

		return null;
	}

	public Nodo<T> darCabeza() {
		return cabeza;
	}


	public Nodo<T> darPuntero() {
		return puntero;
	}
	
	public Iterator<T> iterator() {
		return new GenericIterator<T>(this);
	}
}