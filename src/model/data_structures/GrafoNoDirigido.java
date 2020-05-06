package model.data_structures;


public class GrafoNoDirigido<K extends Comparable<K>, V> {

	private int V;
	private int E;
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

	public double getCostArc(K idVertexIni, K idVertexFin){
		Iterable<Arco<K, V>> arcos = adj.get(idVertexIni).darAdyacentes();

		for (Arco<K, V> arco : arcos) {
			if (arco.darDestino().darId().compareTo(idVertexFin) == 0)
				return arco.darCosto();
		}

		return -1.0;
	}

	public void setCostArc(K idVertexIni, K idVertexFin, double cost){
		Iterable<Arco<K, V>> arcos = adj.get(idVertexIni).darAdyacentes();
		boolean exists = false;
		
		for (Arco<K, V> arco : arcos) {
			if (arco.darDestino().darId().compareTo(idVertexFin) == 0) {
				arco.cambiarCosto(cost);
				exists = true;
			}
		}
		
		if (!exists) return;
		
		arcos = adj.get(idVertexFin).darAdyacentes();
		
		for (Arco<K, V> arco : arcos) {
			if (arco.darDestino().darId().compareTo(idVertexIni) == 0)
				arco.cambiarCosto(cost);
		}
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

	}

	public void dfs(K s){

	}

	public int cc(){
		return 1;
	}
}
