package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import java.text.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javafx.scene.shape.Arc;

import com.google.gson.GsonBuilder;
import model.data_structures.Arco;
import model.data_structures.ListaEnlazada;
import model.data_structures.Vertice;
import view.Maps;

import java.io.FileWriter;
import java.io.File;

import model.data_structures.GrafoNoDirigido;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	public static final String RUTAA = "./data/bogota_arcos.txt";
	public static final String RUTAV = "./data/bogota_vertices.txt";
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
	private static final double LONGITUD_MIN = -74.094723;
	private static final double LONGITUD_MAX = -74.062707;
	private static final double LATITUD_MIN = 4.597714;
	private static final double LATITUD_MAX = 4.621360;

	private GrafoNoDirigido<Integer, Informacion> grafo;
	private Estacion[] arregloEst;
	
	public Modelo() {
		grafo = null;
	}

	public void cargaTexto() {
		FileReader a;
		FileReader v;
		BufferedReader ba;
		BufferedReader bv;

		try {
			String cadena;
			v = new FileReader(RUTAV);
			bv = new BufferedReader(v);
			
			a = new FileReader(RUTAA);
			ba = new BufferedReader(a);
			
			grafo = new GrafoNoDirigido<Integer, Informacion>(997);
			
			
			while((cadena = bv.readLine()) != null) {
				String[] partes = cadena.split(",");
				
				Integer id = Integer.parseInt(partes[0]);
				Double l1 = Double.parseDouble(partes[1]);
				Double l2 = Double.parseDouble(partes[2]);
				
				Informacion actual = new Informacion(id, l1, l2);
				
				//if (id >= 0 && id <11)	System.out.println(llave.hashCode());
				grafo.addVertex(id, actual);
			}

			bv.close();

			a = new FileReader(RUTAA);
			ba = new BufferedReader(a);
		
			for (int i = 0; i < 3; i++) {
				ba.readLine();
			}
			
			while((cadena = ba.readLine()) != null) {
				String[] partes = cadena.split(" ");
				
				Integer id = Integer.parseInt(partes[0]);
				Informacion inicio = grafo.getInfoVertex(id);
				
				for (int i = 1; i < partes.length-1; i++) {
					Integer idAdj = Integer.parseInt(partes[i]);
					Informacion fin = grafo.getInfoVertex(idAdj);
					
					if (inicio != null && fin != null) grafo.addEdge(id, idAdj, distance(inicio.darLatitud(), inicio.darLongitud(), fin.darLatitud(), fin.darLongitud()));
				}
			}
			
			ba.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double distance(double startLat, double startLong, double endLat, double endLong) {

		double dLat  = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}

	
	
	// Vamos a guardar los datos bajo el nombre de datos.json
	public void escribirJson() {
		Iterable<Integer> ids = grafo.keys();
		AuxiliarJson [] auxiliares = new AuxiliarJson[grafo.V()];

		int indice = 0;
		
		for (Integer id : ids) {		
			Iterable<Integer> idAdjs = grafo.adj(id);
			int tamanio = ((ListaEnlazada<Integer>) idAdjs).darTamanio();
			
			Dupla [] duplas = new Dupla[tamanio];
			
			int indice1 = 0;
			
			for (Integer ida : idAdjs) {
				Dupla dupla = new Dupla(grafo.darArco(id, ida).darCosto(), ida);
				duplas[indice1++] = dupla;
			}
			
			AuxiliarJson auxiliar = new AuxiliarJson(grafo.getInfoVertex(id), duplas);
			auxiliares[indice++] = auxiliar;
		}

		Gson graphWriter = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			FileWriter fw = new FileWriter("./data/datos.geojson");
			graphWriter.toJson(auxiliares, fw);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void leerJson() {
		JsonReader reader;

		try {
			reader = new JsonReader(new FileReader("./data/datos.geojson"));
			JsonElement elem = JsonParser.parseReader(reader);

			JsonArray grafos = elem.getAsJsonArray();

			grafo = new GrafoNoDirigido<Integer, Informacion>(997);

			for (JsonElement e : grafos) {

				Integer idInicio = e.getAsJsonObject().get("id").getAsInt();
				Double longitud = e.getAsJsonObject().get("longitud").getAsDouble();
				Double latitud = e.getAsJsonObject().get("latitud").getAsDouble();

				Informacion nuevo = new Informacion(idInicio, longitud, latitud);
				grafo.addVertex(idInicio, nuevo);

				
				JsonArray arcos = e.getAsJsonObject().get("arcos").getAsJsonArray();
				
				for (JsonElement arco : arcos) {
					
					Integer idFinal = arco.getAsJsonObject().get("id").getAsInt();
					Double costo = arco.getAsJsonObject().get("costo").getAsDouble();
					
					grafo.addEdge(idInicio, idFinal, costo);
				}
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ListaEnlazada<Informacion> verticesUtiles(){
		ListaEnlazada<Informacion> arreglo = new ListaEnlazada<Informacion>();
		Iterable<Integer> llaves = grafo.keys();
		for (Integer llave : llaves) {
			Informacion ver = grafo.getInfoVertex(llave);
			if (ver.darLatitud()<=LATITUD_MAX && ver.darLatitud()>=LATITUD_MIN && ver.darLongitud()<=LONGITUD_MAX && ver.darLongitud()>=LONGITUD_MIN){
				arreglo.agregar(ver);
			}
		}
		return arreglo;
	}
	
	public void visualizacion(){
		Maps mapsReqX = new Maps(verticesUtiles());
		mapsReqX.initFrame();
	}
	
	public void visualizacionFinal(){
		Maps mapsReqX = new Maps(verticesUtiles(), arregloEst);
		mapsReqX.initFrame();
	}
	
	public int cargarEstaciones() {
		int numeroEstaciones = 0;
		
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader("./data/estacionpolicia.geojson"));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();
			arregloEst = new Estacion[e2.size()];

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				
				double latitud = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOLATITUD").getAsDouble();
				double longitud = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOLONGITU").getAsDouble();

				Estacion est = new Estacion(OBJECTID, longitud, latitud);
				arregloEst[numeroEstaciones] = est;
				
				numeroEstaciones++;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return numeroEstaciones;
	}
	
	public Estacion[] darEstaciones(){
		return arregloEst;
	}

	public int V(){
		return grafo.V();
	}

	public int E(){
		return grafo.E();
	}

	public static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}

}
