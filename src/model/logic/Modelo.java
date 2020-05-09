package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileWriter;
import java.io.File;

import model.data_structures.GrafoNoDirigido;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	public static String RUTAA = "./data/bogota_arcos.txt";
	public static String RUTAV = "./data/bogota_vertices.txt";
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	private GrafoNoDirigido<Integer, Informacion> grafo;

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
		Informacion [] informaciones = new Informacion[grafo.V()];

		int indice = 0;

		for (Integer id : ids) informaciones[indice++] = grafo.getInfoVertex(id);

		Gson graphWriter = new Gson();

		try {
			FileWriter fw = new FileWriter("./data/datos.gson");
			graphWriter.toJson(informaciones, fw);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void leerJson() {
		JsonReader reader;

		try {
			reader = new JsonReader(new FileReader("./data/datos.gson"));
			JsonElement elem = JsonParser.parseReader(reader);

			JsonArray a = elem.getAsJsonArray();

			grafo = new GrafoNoDirigido<Integer, Informacion>(997);

			for (JsonElement e : a) {

				Integer id = e.getAsJsonObject().get("id").getAsInt();
				Double longitud = e.getAsJsonObject().get("longitud").getAsDouble();
				Double latitud = e.getAsJsonObject().get("latitud").getAsDouble();

				Informacion nuevo = new Informacion(id, longitud, latitud);
				grafo.addVertex(id, nuevo);
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
