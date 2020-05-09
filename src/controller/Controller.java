package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.logic.Modelo;
import quicktime.app.time.TaskAllMovies;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}
		
	public void run() throws FileNotFoundException, IOException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					modelo.cargaTexto();
					System.out.println("Numero de vertices: " + modelo.V());
					System.out.println("Numero de arco: " + modelo.E());
					
					break;

				case 2:
					modelo.escribirJson();
					break;

				case 3:
					modelo.leerJson();
					System.out.println("Numero de vertices: " + modelo.V());
					System.out.println("Numero de arco: " + modelo.E());
					
					break;

				case 4:
					
					break;

				case 5:
					int tamanio = modelo.cargarEstaciones();
					System.out.println("Numero estaciones: " + tamanio);
					for (int i = 0; i < tamanio; i++) {
						System.out.println(modelo.darEstaciones()[i].toString());
					}
					break;	
					
				case 6: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;
					
				case 7:
					
					break;

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
