package model.data_structures;

public class GrafoNoDirigido<K extends Comparable<K>, V extends Comparable<V>>{

	private int V;
	private int E;
	private Bag<K, V>[] adj;

	GrafoNoDirigido (int n){
		this.V = n; 
		this.E = 0;
		adj = (Bag<K, V>[]) new Bag[n];
		for (int v = 0; v < n; v++)
			adj[v] = new Bag<K, V>();
	}

	int V(){
		return V;
	}

	int E(){
		return E;
	}

	//TODO añadir costo
	void addEdge(K idVertexIni, K idVertexFin, double cost){
		adj[idVertexIni].add(idVertexFin);
		adj[idVertexFin].add(idVertexIni);
		E++;
	}

	V getInfoVertex(K idVertex){

	}

	void setInfoVertex(K idVertex, V infoVertex){

	}

	double getCostArc(K	idVertexIni, K idVertexFin){

	}

	void setCostArc(K idVertexIni, K idVertexFin, double cost){

	}

	void addVertex(K idVertex, V infoVertex){

	}

	Iterable<K> adj (K idVertex){
		return adj[idVertex];
	}

	void uncheck(){

	}

	void dfs(K s){

	}

	int cc(){

	}

	Iterable<K> getCC(K idVertex){

	}


}
