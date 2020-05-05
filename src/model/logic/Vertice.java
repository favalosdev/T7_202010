package model.logic;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class Vertice{

	private int id;
	private double longitud;
	private double latitud;

	public Vertice(int pId, double pLongitud, double pLatitud)
	{
		id = pId;
		longitud = pLongitud;
		latitud = pLatitud;
	}

}
