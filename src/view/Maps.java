package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.junit.internal.ArrayComparisonFailure;

import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.InfoWindow;
//import com.teamdev.jxmaps.InfoWindowOptions;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;
import model.data_structures.*;
import model.logic.*;

public class Maps extends MapView {

	private static final int EARTH_RADIUS = 6371;

	// Objeto Google Maps
	private Map map;

	// Identificador del requerimiento a visualizar
	//private String idRequerimiento;

	//Coordenadas del camino a mostrar (secuencia de localizaciones (Lat, Long))
	private ListaEnlazada<Informacion> vertices;
	private Estacion[] estaciones;

	public Maps(ListaEnlazada<Informacion> vertices) {	
		//idRequerimiento = idReq;

		this.vertices = vertices;
		setOnMapReadyHandler( new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {

				// Configuracion de localizaciones del path (circulos)
				CircleOptions vertexLocOpt= new CircleOptions(); 
				vertexLocOpt.setFillColor("#00FF00");  // color de relleno
				vertexLocOpt.setFillOpacity(0.5);
				vertexLocOpt.setStrokeWeight(1.0);

				if ( status == MapStatus.MAP_STATUS_OK) {
					map = getMap();

					
					for (Informacion info : vertices) {
						LatLng data = new LatLng(info.darLatitud(), info.darLongitud());
						Circle loc = new Circle(map);
						loc.setOptions(vertexLocOpt);
						loc.setCenter(data);
						loc.setRadius(30);
					}

					initMap( map );
				}
			}

		} );
	}

	public Maps(ListaEnlazada<Informacion> vertices, Estacion[] ests) {	
		//idRequerimiento = idReq;

		this.vertices = vertices;
		this.estaciones = ests;
		setOnMapReadyHandler( new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {

				// Configuracion de localizaciones del path (circulos)
				CircleOptions vertexLocOpt= new CircleOptions(); 
				vertexLocOpt.setFillColor("#00FF00");  // color de relleno
				vertexLocOpt.setFillOpacity(0.5);
				vertexLocOpt.setStrokeWeight(1.0);

				CircleOptions vertexLocOp= new CircleOptions(); 
				vertexLocOp.setFillColor("#1C96D4");  // color de relleno
				vertexLocOp.setFillOpacity(0.5);
				vertexLocOp.setStrokeWeight(1.0);

				if ( status == MapStatus.MAP_STATUS_OK) {
					map = getMap();

					for (Informacion info : vertices) {
						LatLng data = new LatLng(info.darLatitud(), info.darLongitud());
						Circle loc = new Circle(map);
						loc.setOptions(vertexLocOpt);
						loc.setCenter(data);
						loc.setRadius(30);
					}

					for (int i = 0; i < estaciones.length; i++) {
						LatLng data = new LatLng(estaciones[i].darLatitud(), estaciones[i].darLongitud());
						Circle loc = new Circle(map);
						loc.setOptions(vertexLocOp);
						loc.setCenter(data);
						loc.setRadius(30);
					}

					initMap( map );
				}
			}

		} );
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


	public void initMap(Map map) {
		MapOptions mapOptions = new MapOptions();
		MapTypeControlOptions controlOptions = new MapTypeControlOptions();
		controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
		mapOptions.setMapTypeControlOptions(controlOptions);

		map.setOptions(mapOptions);
		map.setCenter(new LatLng(vertices.darCabeza().darDato().darLatitud(), vertices.darCabeza().darDato().darLongitud()));
		map.setZoom(14.0);

	}

	public void initFrame() {
		JFrame frame = new JFrame("Mapa");
		frame.setSize(600, 600);
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}
}
