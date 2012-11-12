
public class Greedy implements Algorithm {
	
	World w;
	boolean[] visited;
	int size;

	public Greedy(World w) {
		this.w = w;
		size = w.getSize ();
		visited = new boolean[size];
	}
	
	public int[] solve() {
		int[] ret = new int[size];
		int numVisited = 0;
		int currentCity = 0;
		while (numVisited < size){
			ret[numVisited] = currentCity;
			visited[currentCity] = true;
			currentCity = w.getNearestCity (currentCity, visited);
			numVisited++;
		}
		
		return ret;
	}
}
