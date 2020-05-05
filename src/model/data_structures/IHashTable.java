package model.data_structures;

import java.util.*;

public interface IHashTable<K extends Comparable<K>, Vertice> {
	
	public void put(K key, Vertice value);
	
	public Vertice getValue(K key);
	
	public void deleteSet(K key);
	
	public Iterator<K> keys();
}
