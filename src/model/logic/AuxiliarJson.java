package model.logic;



public class AuxiliarJson {
	private Integer id;
	private Double latitud;
	private Double longitud;
	private Dupla [] arcos;
	
	public AuxiliarJson(Informacion info, Dupla [] arcos) {
		id = info.darId();
		latitud = info.darLatitud();
		longitud = info.darLongitud();
		this.arcos = arcos;
	}
}
