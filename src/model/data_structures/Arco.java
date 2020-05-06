package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class Arco<K extends Comparable<K>, V>{

	private double costo;
	private Vertice<K, V> origen;
	private Vertice<K, V> destino;


	public Arco(Vertice<K, V> orig, Vertice<K, V> dest, double pCosto) {
		origen = orig;
		destino = dest;
		costo = pCosto;
	}
	
	public Vertice<K, V> darOrigen(){
		return origen;
	}
	
	public Vertice<K, V> darDestino(){
		return destino;
	}
	
	public double darCosto(){
		return costo;
	}
	
	public void cambiarCosto(double pCosto) {
		costo = pCosto;
	}
}
