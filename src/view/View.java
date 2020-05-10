package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar malla vial de Bogota");
			System.out.println("2. Guardar grafo en un archivo .json");
			System.out.println("3. Cargar el archivo .json");
			System.out.println("4. Visualizar grafo");
			System.out.println("5. Cargar estaciones");
			System.out.println("6. Agregar estaciones al mapa");
			System.out.println("7. Visualizar estaciones en el mapa");
			System.out.println("8. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			System.out.println(modelo);
		}
}
