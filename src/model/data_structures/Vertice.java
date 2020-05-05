package model.data_structures;

import java.util.Iterator;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class Vertice<K extends Comparable<K>, V>{

	private K idVertice;
	private V infoVertice;
	private boolean marca;
	private int color;
	private Arco<K,V> arcoLlegada;
	private int distancia;
	private Bag<Arco<K,V>> adyacentes;

	public Vertice(K id, V info)
	{
		idVertice = id;
		infoVertice = info;
	}
	
	public K darId(){
		return idVertice;
	}
	
	public V darInfo(){
		return infoVertice;
	}
	
	public void agregarArco(Arco<K,V> arco){
		adyacentes.add(arco);
	}
	
	public Arco<K,V> eliminarArco(K idDest){
		Iterator<Arco<K,V>> arreglo = adyacentes.iterator();
		while (arreglo.hasNext()){
			if (arreglo.next().darDestino() == idDest){
				
			}
		}
	}

	public Iterator<Arco<K,V>> darAdyacentes(){
		return adyacentes.iterator();
	}
	
	public boolean darMarca( ){
		return marca;
	}
	
	public void marcar(int colorP, Arco<K,V> arcoLlega){
		
	}
	
	public void desmarcar( ){
		
	}
	
	public void dfs( int colorP, Arco<K,V> arcoLlega ){
		
	}
	
	public void bfs( int colorP){
		
	}

}
