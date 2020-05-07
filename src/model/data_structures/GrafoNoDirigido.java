package model.data_structures;

import java.util.Iterator;

public class GrafoNoDirigido<K extends Comparable<K>, V> {

	private int V;
	private int E;
	private int coloresUsados;
	private SeparateChainingHashST<K, Vertice<K, V>> adj;

	GrafoNoDirigido (int n){
		this.V = n; 
		this.E = 0;
		adj = new SeparateChainingHashST<K, Vertice<K, V>>(n);	
	}


	public int V(){
		return V;
	}

	public int E(){
		return E;
	}

	public void addEdge(K idVertexIni, K idVertexFin, double cost) {
		Vertice<K, V> i = adj.get(idVertexIni);
		Vertice<K, V> f = adj.get(idVertexFin);

		i.agregarArco(new Arco<K, V>(i, f, cost));
		f.agregarArco(new Arco<K, V>(f, i, cost));
		E++;
	}


	public V getInfoVertex(K idVertex) {
		return adj.get(idVertex).darInfo();
	}

	public void setInfoVertex(K idVertex, V infoVertex){
		adj.get(idVertex).ponerInfo(infoVertex);
	}
	
	public Arco<K,V> darArco(K idOrig, K idDest){
		Iterable<Arco<K, V>> arcos = adj.get(idOrig).darAdyacentes();

		for (Arco<K, V> arco : arcos) {
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
		Iterable<Arco<K, V>> arcos = adj.get(idVertex).darAdyacentes();
		LinkedQueue<K> llaves = new LinkedQueue<K>();

		for (Arco<K, V> arco : arcos)
			llaves.enqueue(arco.darDestino().darId());

		return llaves;
	}

	public void uncheck(){
		Iterable<K> llaves = adj.keys();
		for (K llave : llaves)
		{
			Vertice<K,V> ver = adj.get(llave);
			ver.desmarcar();
		}
	}

	public void posDfs(K s, Arco<K,V> arcoInicial){
		Iterable<K> llaves = adj(s);
		adj.get(s).marcar(coloresUsados, arcoInicial);
		for (K llave : llaves)
			if (adj.get(llave).darMarca() == false){
				posDfs(llave, darArco(s, llave));
			}
		coloresUsados++;
	}
	
	public void dfs(K s){
		uncheck();
		posDfs(s, null);
	}
	
	public int cc(){
		int contador = 0;
		int[] colores;
		marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                res++;
            }
        }
        return contador;
	}
	
	public Iterable<K> getCC(K idVertex){
		
	}
}
