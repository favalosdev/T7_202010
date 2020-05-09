package model.data_structures;

import java.util.Iterator;

public class GrafoNoDirigido<K extends Comparable<K>, V> {

	private int E;
	private int colorActual;
	private SeparateChainingHashST<K, Vertice<K, V>> adj;

	public GrafoNoDirigido (int n){ 
		this.E = 0;
		colorActual = 0;
		adj = new SeparateChainingHashST<K, Vertice<K, V>>(n);	
	}

	public Iterable<K> keys() {
		return adj.keys();
	}

	public int V(){
		return adj.size();
	}

	public int E(){
		return E;
	}

	public void addEdge(K idVertexIni, K idVertexFin, double cost) {
		Vertice<K, V> i = adj.get(idVertexIni);
		Vertice<K, V> f = adj.get(idVertexFin);

		if (i == null || f == null) return;
		i.agregarArco(new Arco<K,V>(i, f, cost));
		f.agregarArco(new Arco<K,V>(f, i, cost));
		E++;
	}


	public V getInfoVertex(K idVertex) {
		return adj.get(idVertex).darInfo();
	}

	public void setInfoVertex(K idVertex, V infoVertex){
		adj.get(idVertex).ponerInfo(infoVertex);
	}

	public Arco<K,V> darArco(K idOrig, K idDest){
		Iterable<Arco<K,V>> arcos = adj.get(idOrig).darAdyacentes();

		for (Arco<K,V> arco : arcos) {
			if (arco.darDestino().darId().compareTo(idDest) == 0)
				return arco;
		}

		return null;
	}

	public double getCostArc(K idVertexIni, K idVertexFin){
		Arco<K,V> arco = darArco(idVertexIni, idVertexFin);
		if (arco != null) return arco.darCosto();
		return -1.0;
	}

	public void setCostArc(K idVertexIni, K idVertexFin, double cost){
		Arco<K,V> arco = darArco(idVertexIni, idVertexFin);
		if (arco != null) arco.cambiarCosto(cost);
	}

	public void addVertex(K idVertex, V infoVertex) {
		adj.put(idVertex, new Vertice<K, V>(idVertex, infoVertex));
	}

	public Iterable<K> adj(K idVertex) {
		Iterable<Arco<K,V>> arcos = adj.get(idVertex).darAdyacentes();
		LinkedQueue<K> llaves = new LinkedQueue<K>();

		for (Arco<K, V> arco : arcos)
			llaves.enqueue(arco.darDestino().darId());

		return llaves;
	}

	public void uncheck(){
		Iterable<K> llaves = adj.keys();
		for (K llave : llaves) {
			Vertice<K,V> ver = adj.get(llave);
			ver.desmarcar();
		}
	}

	public void posDfs(K s, Arco<K,V> arcoInicial, int pColor){
		Iterable<K> llaves = adj(s);
		adj.get(s).marcar(pColor, arcoInicial);
		
		for (K llave : llaves) {
			if (adj.get(llave).darMarca() == false){
				posDfs(llave, darArco(s, llave), pColor);
			}
		}
		colorActual++;
	}

	public void dfs(K s){
		uncheck();
		colorActual = 0;
		posDfs(s, null, colorActual);
	}

	public int cc(){
		Iterable<K> llaves = adj.keys();
		uncheck();
		colorActual = 0;
		for (K llave : llaves) if (adj.get(llave).darColor() == -1) posDfs(llave,null,colorActual);

		return colorActual+1;
	}

	public Iterable<K> getCC(K idVertex){
		dfs(idVertex);
		Vertice<K,V> inicio = adj.get(idVertex);
		Iterable<K> llaves = adj.keys();
		LinkedQueue<K> respuesta = new LinkedQueue<K>();
		
		for (K llave : llaves) {
			Vertice<K,V> ver = adj.get(llave);
			if (ver.darColor() == inicio.darColor()) respuesta.enqueue(llave);;
		}
		return respuesta;
	}
}
