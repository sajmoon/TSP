

public class NearestNeighbour extends Algorithm {
	
	World w;
	boolean[] visited;
	int size;
	
	@Override
	public Graph graphSolve(World w) {
		this.w = w;
		size = w.getSize ();
		visited = new boolean[size];
		int[] ret = new int[size];
		int numVisited = 0;
		int currentCity = 0;
		Graph g = new Graph (w.getSize (), w);
		while (numVisited < size){
			visited[currentCity] = true;
			int nextCity = w.getNearestCity (currentCity, visited);
			g.addEdge(currentCity, nextCity);
			currentCity = nextCity;
			numVisited++;
		}
		
		return g;
	}
}
