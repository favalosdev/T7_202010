package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

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
        while((cadena = ba.readLine())!=null) {
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

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

	public void cargarDatos() {

		JsonReader reader;
		try {
			cargaTexto();
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();

			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, MUNICIPIO, longitud, latitud);
				numeroDatos++;
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
}
