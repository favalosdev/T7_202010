package model.data_structures;

public class GrafoNoDirigido<K extends Comparable<K>, V>{

	private int numVertices;
	private int numArcos;
	private SeparateChainingHashTable<K,Vertice<K,V>> adj;

	GrafoNoDirigido (int n){
		this.numVertices = n; 
		this.numArcos = 0;
		adj = new SeparateChainingHashTable<K,Vertice<K,V>>(n);
	}

	public int V(){
		return numVertices;
	}

	public int E(){
		return numArcos;
	}
	
	public Vertice<K, V> darVertice(K idVert){
		for (int i = 0; i < adj.length; i++) {
			if (adj[i].)
		}
	}
	
	public Arco<K, V> darArco(K idOrig, K idDest){
		
	}

	//TODO añadir costo
	public void addEdge(K idVertexIni, K idVertexFin, double cost){
		adj[(int) idVertexIni].add(idVertexFin);
		adj[(int) idVertexFin].add(idVertexIni);
		numArcos++;
	}

	public V getInfoVertex(K idVertex){
		for (int i = 0; i < adj.length; i++) {
			while(adj[i].iterator().hasNext()){
				if (adj[i].iterator().next().darId().compareTo(idVertex) == 0){
					return (V) adj[i].iterator().next().darInfo();
				}
			}
		}
		return null;
	}

	public void setInfoVertex(K idVertex, V infoVertex){

	}

	public double getCostArc(K idVertexIni, K idVertexFin){
		
	}

	public void setCostArc(K idVertexIni, K idVertexFin, double cost){

	}

	public void addVertex(K idVertex, V infoVertex){

	}

	public Iterable<K> adj (K idVertex){
		return adj[idVertex];
	}

	public void uncheck(){

	}

	public void dfs(K s){

	}

	public int cc(){

	}

	public Iterable<K> getCC(K idVertex){

	}
}
