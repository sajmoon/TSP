

public class NearestNeighbour extends Algorithm {
	
	World w;
	boolean[] visited;
	int size;
	
	public int[] solve(World w) {
		this.w = w;
		size = w.getSize ();
		visited = new boolean[size];
		int[] ret = new int[size];
		int numVisited = 0;
		int currentCity = 0;
		Graph g = new Graph (w.getSize ());
		while (numVisited < size){
			ret[numVisited] = currentCity;
			visited[currentCity] = true;
			currentCity = w.getNearestCity (currentCity, visited);
			numVisited++;
		}
		
		return ret;
	}

	@Override
	public Graph graphSolve (World w) {
		// TODO Auto-generated method stub
		return null;
	}
}
