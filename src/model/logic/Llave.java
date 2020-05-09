package model.logic;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class Llave implements Comparable<Llave> {

	private final Integer id;

	public Llave(Integer pId) {
		id = pId;
	}

	public int hashCode(){
		int hash = 17;
		hash = 31 * hash + id.hashCode();
		return hash;
	}

	public int compareTo(Llave otra) {
		return hashCode() - otra.hashCode();
	}

}