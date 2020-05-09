package model.logic;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class Estacion{

	private int objectId;
	private double latitud;
	private double longitud;

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public Estacion(int pID, double pLong, double pLat){
		objectId = pID;
		latitud = pLat;
		longitud = pLong;
	}
	
	public String toString() {
		return "Estacion [OBJECTID= " + objectId + ", LATITUD= " + latitud + ", LONGITUD= " + longitud + "]";
	}

}
