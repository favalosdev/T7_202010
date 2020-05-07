package model.data_structures;

import java.util.Iterator;


public class Camino<K extends Comparable<K>, V>{

	private double costo;
	private int distancia;
	private Vertice<K, V> origen;
	private Vertice<K, V> destino;
	private Bag<Arco<K,V>> secuencia;

	public Camino() {

	}
	
	public double darCosto(){
		return costo;
	}
	
	public int darDistancia(){
		return distancia;
	}
	
	
	public void agregarArcoFinal(Arco<K,V> arco){
		secuencia.add(arco);
	}
	
	public void agregarArcoInicio(Arco<K,V> arco){
		secuencia.add(arco);
	}

}
