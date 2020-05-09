package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.logic.Modelo;
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
					
					System.out.println("Numero de vertices: " + modelo.numeroVertices());
					System.out.println("Numero de arco: " + modelo.numeroVertices());
					
					break;

				case 2:
					modelo.escribirJson();
					break;

				case 3:
					modelo.leerJson();
					
					System.out.println("Numero de vertices: " + modelo.numeroVertices());
					System.out.println("Numero de arco: " + modelo.numeroVertices());
					
					break;

				case 4:
					/////////TODO/////////
					break;

				case 5: 
					System.out.println("--------- \nContenido del Arreglo: ");
					view.printModelo(modelo);
					//System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;	
					
				case 6: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
