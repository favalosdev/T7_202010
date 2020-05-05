package model.data_structures;

import java.util.Iterator;

public class SeparateChainingHashTable<K extends Comparable<K>, Vertice> implements IHashTable<K, Vertice> {
	// TODO implementar re-hash
	private final static double LOAD_FACTOR = 5.0;
	private SequentialSearchST<K,Vertice>[] values;
	private int M;
	private int N;
	private int numeroReHashes;

	public SeparateChainingHashTable(int m) {
		N = 0;
		M = m;
		values = (SequentialSearchST<K, Vertice>[]) new SequentialSearchST[M];
		for (int i = 0; i < M; i++)
			values[i] = (SequentialSearchST<K, Vertice>) new SequentialSearchST();
	}

	public int getN() {
		return N;
	}

	public int getM() {
		return M;
	}

	private SequentialSearchST<K, Vertice>[] getValues() {
		return values;
	}

	public int darNumeroReHashes(){
		return numeroReHashes;
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public Vertice getValue(K pKey) {
		return values[hash(pKey)].getVertice(pKey);
	}

	public void deleteSet(K pKey) {
		values[hash(pKey)].remove(pKey);
		N--;
	}

	private void resize(int capacity) {
		SeparateChainingHashTable<K, Vertice> temp = new SeparateChainingHashTable<K, Vertice>(capacity);

		for (int i = 0; i < M; i++) {
			STLink<K, Vertice> current = values[i].getHead();

			while (current != null) {
				// Iterar todo el set de las llaves
				temp.put(current.getKey(), current.getVertice());
				current = current.darSiguiente();
			}
		}

		M = temp.getM();
		N = temp.getN();
		values = temp.getValues();
		numeroReHashes++;
	}

	public double getLoadFactor(){
		return (double) ((double) N / (double) M);
	}

	public void put(K pKey, Vertice pVal) {
		// Support rehash operations
		if (getLoadFactor() > LOAD_FACTOR) resize(2*M);

		// Ver esta parte
		values[hash(pKey)].put(pKey, pVal);
		N++;
	}

	public Iterator<K> keys() {
		IListaEnlazada<K> generalKeys = new ListaEnlazada<K>();

		for (int i = 0; i < M; i++) {
			if (!values[i].isEmpty()) {
				Iterator<K> localKeys = values[i].keys();
				while (localKeys.hasNext()) {
					generalKeys.agregar(localKeys.next());
				}
			}
		}

		return new GenericIterator<K>(generalKeys);
	}

}
