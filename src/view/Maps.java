package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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

	// Objeto Google Maps
	private Map map;

	// Identificador del requerimiento a visualizar
	//private String idRequerimiento;

	//Coordenadas del camino a mostrar (secuencia de localizaciones (Lat, Long))
	//private LatLng[] locations = {new LatLng(4.6285797,-74.0649341), new LatLng(4.608550, -74.076443), new LatLng(4.601363, -74.0661), new LatLng(4.5954979,-74.068708) }; //Coordenadas de los vertices inicio, intermedio y fin.		

	private ListaEnlazada<Informacion> vertices;
	
	/**
	 * Visualizacion Google map con camino, marcas, circulos y texto de localizacion
	 * @param idReq
	 */
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

	public void initMap(Map map) {
		MapOptions mapOptions = new MapOptions();
		MapTypeControlOptions controlOptions = new MapTypeControlOptions();
		controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);

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
}
