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
public class Modelo{

	public static String PATH = "./data/Comparendos_DEI_2018_Bogotá_D.C_small.geojson";
	public static String RUTAA = "./data/bogota_arcos.txt";
	public static String RUTAV = "./data/bogota_vertices.txt";
	//	public static String PATH = "./data/Comparendos_DEI_2018_Bogot�_D.C.geojson";
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	private int numeroDatos;
	
	private GrafoNoDirigido<LlaveVertice,Vertice> grafo;
	
	public void cargaTexto() throws FileNotFoundException, IOException {
        String cadena;
        FileReader a = new FileReader(RUTAA);
        FileReader v = new FileReader(RUTAV);
        BufferedReader ba = new BufferedReader(a);
        BufferedReader bv = new BufferedReader(v);
        
        grafo = new GrafoNoDirigido<LlaveVertice,Vertice>(997);
        
        while((cadena = ba.readLine()) != null) {
            String[] partes = cadena.split(" ");
            Vertice ver = new Vertice(new Integer (partes[0]),new Double (partes[1]),new Double(partes[2]));
            grafo.addVertex(new LlaveVertice(new Integer (partes[0])), ver);
        }
        
        ba.close();
        
        while((cadena = bv.readLine())!=null) {
        	String[] partes = cadena.split(" ");
        	LlaveVertice llaveInicio = new LlaveVertice(new Integer (partes[0]));
        	Vertice verInicio = grafo.getInfoVertex(llaveInicio);
        	
            for (int i = 1; i < partes.length-1; i++) {
            	LlaveVertice llaveFin = new LlaveVertice(new Integer (partes[i]));
            	Vertice verFin = grafo.getInfoVertex(llaveFin);
				grafo.addEdge(llaveInicio, llaveFin, distance(verInicio.darLatitud(), verInicio.darLongitud(), verFin.darLatitud(), verFin.darLongitud()));
			}
        }
        
        bv.close();
    }

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

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
    	Iterable<LlaveVertice> llaves = grafo.keys();
    	Vertice [] vertices = new Vertice[grafo.V()];
    	
    	int indice = 0;
    	
    	for (LlaveVertice llave : llaves) vertices[indice++] = grafo.getInfoVertex(llave);
    	
    	
    	Gson graphWriter = new Gson();
    	
    	try {
    		FileWriter fw = new FileWriter("./data/datos.gson");
    		graphWriter.toJson(vertices, fw);
    		
    	} catch (IOException e) {
    		// No hacer nada JAJAJAJAJA
    	}
    }
    
    public void leerJson() {
    	JsonReader reader;

    	
    	try {
    		reader = new JsonReader(new FileReader("./data/datos.gson"));
    		JsonElement elem = JsonParser.parseReader(reader);
    		
    		JsonArray a = elem.getAsJsonArray();
    		
    		grafo = new GrafoNoDirigido<LlaveVertice,Vertice>(997);
    		
    		for (JsonElement e : a) {
    			
    			int id = e.getAsJsonObject().get("id").getAsInt();
    			double longitud = e.getAsJsonObject().get("longitud").getAsDouble();
    			double latitud = e.getAsJsonObject().get("latitud").getAsDouble();
    			
    			Vertice nuevo = new Vertice(id, longitud, latitud);
    			LlaveVertice llave = new LlaveVertice(id);
    			grafo.addVertex(llave, nuevo);
    		}
    		
    		
    		reader.close();
    	} catch (Exception e) {
    		// No hacer nada JAJAJAJAJ
    	}
    	
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
	
}
