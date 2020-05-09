package model.data_structures;

import java.util.Iterator;


public class Vertice<K extends Comparable<K>, V>{

	private K idVertice;
	private V infoVertice;
	
	private boolean marca;
	private int color;
	private Arco<K,V> arcoLlegada;
	private int distancia;
	private Bag<Arco<K,V>> adj;

	public Vertice(K id, V info) {
		Bag<Arco<K,V>> adj = new Bag<Arco<K,V>>();
		idVertice = id;
		infoVertice = info;
		color = -1;
		arcoLlegada = null;
	}
	
	public K darId() {
		return idVertice;
	}
	
	public Arco<K,V> darArcoLlegada(){
		return arcoLlegada;
	}
	
	public void ponerInfo(V pInfoVertice) {
		infoVertice = pInfoVertice;
	}
	
	public V darInfo() {
		return infoVertice;
	}
	
	public void agregarArco(Arco<K,V> arco){
		adj.add(arco);
	}

	public Iterable<Arco<K,V>> darAdyacentes(){
		return adj;
	}
	
	public boolean darMarca( ){
		return marca;
	}
	
	public void marcar(int colorP, Arco<K,V> arcoLlega){
		marca = true;
		color = colorP;
		arcoLlegada = arcoLlega;
		
	}
	
	public int darColor(){
		return color;
	}
	
	public void desmarcar( ){
		marca = false;
		color = -1;
		arcoLlegada = null;
	}
	
	public void desmarcaParcial( ){
		marca = false;
		arcoLlegada = null;
	}

}
