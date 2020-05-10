package model.logic;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */

public class Informacion implements Comparable<Informacion>{

	private int id;
	private double longitud;
	private double latitud;

	public Informacion(int pId, double pLongitud, double pLatitud) {
		id = pId;
		longitud = pLongitud;
		latitud = pLatitud;
	}
	
	public int darId() {
		return id;
	}
	
	public double darLongitud() {
		return longitud;
	}
	
	public double darLatitud() {
		return latitud;
	}
	
	public int compareTo(Informacion i2) {
		return 0;
	}
}
