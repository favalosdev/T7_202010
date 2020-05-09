package test.data_structures;

import model.data_structures.GrafoNoDirigido;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestGrafoNoDirigido {

	private static int TAMANO2 = 10;
	private static int TAMANO1 = 5;
	private GrafoNoDirigido<String, Integer> table;


	//Set ups de agregar
	public void setUp1() {
		table = new GrafoNoDirigido<String, Integer>(TAMANO1);
	}


	public void setUp2() {
		setUp1();
		//table.addVertex("1", 1);
		for (int i = 1; i < TAMANO1+1; i++) {
			table.addVertex(i + "", i);
		}
	}

	public void setUp3() {
		setUp1();
		for (int i = 1; i < TAMANO2+1; i++) {
			table.addVertex(i + "", i);
		}
	}

	// Set ups reales
	public void setUp4() {
		table.addEdge("1", "2", 5.0);
		table.addEdge("1", 3+"", 5.0);
		table.addEdge(1+"", 4+"", 5.0);
		table.addEdge(2+"", 3+"", 5.0);
		table.addEdge(3+"", 4+"", 5.0);
		table.addEdge(5+"", 2+"", 5.0);
		table.addEdge(5+"", 3+"", 5.0);
		table.addEdge(5+"", 4+"", 5.0);
	}

	public void setUp5() {
		setUp2();
		table.addEdge(1+"", 2+"", 5.0);
		table.addEdge(1+"", 3+"", 5.5);
		table.addEdge(2+"", 4+"", 5.0);
		table.addEdge(2+"", 5+"", 5.5);
	}

	public void setUp6() {
		setUp2();
		for (int i = 1; i < TAMANO1; i++) {
			table.addEdge(i+"", i+1+"", 3.1);
		}
	}

	public void setUp7(){
		setUp6();
		table.addEdge(1+"", 5+"", 2.0);
	}

	public void setUp8() {
		setUp3();
		for (int i = 1; i < TAMANO2; i=i+2) {
			table.addEdge(i+"", i+1+"", 3.1);
		}
	}

	public void setUp9(){
		setUp3();
		table.addEdge(1+"", 2+"", 5.0);
		table.addEdge(1+"", 3+"", 5.0);
		table.addEdge(4+"", 2+"", 5.0);
		table.addEdge(4+"", 3+"", 5.0);
		table.addEdge(3+"", 5+"", 5.0);
		table.addEdge(5+"", 6+"", 5.0);
		table.addEdge(10+"", 7+"", 5.0);
		table.addEdge(10+"", 8+"", 5.0);
		table.addEdge(10+"", 9+"", 5.0);
	}

	// Este test cuenta como test tanto para set como para get
	@Test
	public void testNumeroVertices() {
		setUp1();
		assertEquals("El numero de vertices no es el correcto", 0, table.V());
		setUp2();
		assertEquals("El numero de vertices no es el correcto", 5, table.V());
		setUp1();
		setUp3();
		assertEquals("El numero de vertices no es el correcto", 10, table.V());
	}

	@Test
	public void testNumeroArcos() {
		setUp1();
		assertEquals("El numero de arcos no es el correcto", 0, table.E());
		setUp2();
		assertEquals("El numero de arcos no es el correcto", 0, table.E());
		setUp4();
		assertEquals("El numero de arcos no es el correcto", 8, table.E());
		setUp2();
		setUp5();
		assertEquals("El numero de arcos no es el correcto", 4, table.E());
		setUp2();
		setUp6();
		assertEquals("El numero de arcos no es el correcto", 4, table.E());
		setUp2();
		setUp7();
		assertEquals("El numero de arcos no es el correcto", 5, table.E());
		setUp3();
		setUp8();
		assertEquals("El numero de arcos no es el correcto", 5, table.E());
		setUp3();
		setUp9();
		assertEquals("El numero de arcos no es el correcto", 9, table.E());
	}

	@Test
	public void testCc() {
		setUp1();
		assertEquals("El numero de componentes conectadas no es el correcto", 0, table.cc());
		setUp2();
		assertEquals("El numero de componentes conectadas no es el correcto", 5, table.cc());
		setUp4();
		assertEquals("El numero de componentes conectadas no es el correcto", 1, table.cc());
		setUp2();
		setUp6();
		assertEquals("El numero de componenetes conectadas no es el correcto", 1, table.cc());
		setUp3();
		setUp8();
		assertEquals("El numero de componenetes conectadas no es el correcto", 5, table.cc());
		setUp3();
		setUp9();
		assertEquals("El numero de componenetes conectadas no es el correcto", 2, table.cc());
	}
	
	@Test
	public void testDarCostoArco(){
		setUp2();
		setUp4();
		assertEquals("El costo del arco no es el correcto", 5.0+"", table.getCostArc("1", "2")+"");
		setUp2();
		setUp6();
		assertEquals("El costo del arco no es el correcto", 3.1+"", table.getCostArc("1", "2")+"");
		setUp2();
		setUp7();
		assertEquals("El costo del arco no es el correcto", 2.0+"", table.getCostArc("1", "5")+"");
		setUp3();
		setUp8();
		assertEquals("El costo del arco no es el correcto", 3.1+"", table.getCostArc("1", "2")+"");
	}

}
