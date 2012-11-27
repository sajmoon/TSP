import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * Works now. Max points with 2opt was 6 @ kattis. fuckin' sucks
 * @author jens
 *
 */
public class Greedy extends Algorithm{
	
	static final int NEIGHBOURS_SIZE = 100;

	World w;
	int size;
	double width;
	double height;	

	@Override
	public Graph graphSolve (World w) {
//		try{
		this.w = w;
		this.size = w.getSize ();
		width = w.getWidth ();
		height = w.getHeight ();
		return getSolutionGraphFromEdges (getEdges ());
//		} catch (Exception e){
//			return new NearestNeighbour ().solve (w);
//		}
	}
	
	public Edge[] getEdges (){
		Edge[] edges = addEdges ();
		sortEdges (edges);
		return edges;
	}
	
	public void sortEdges (Edge[] e){
		Arrays.sort (e);
	}
	
	public Graph getSolutionGraphFromEdges (Edge[] edges){
		Graph g = createSolutionGraph (edges);
		return g;
	}
	
	public Graph createSolutionGraph (Edge[] edges){
		Graph g = new Graph (size);
		int checkedEdges=0;
		//Main loop
		while (g.addedEdges < size){
			Edge cur = edges[checkedEdges];
			//If it does not violate graph rules, then
			g.addEdge (cur);
			//			edges.remove (checkedEdges);
			checkedEdges++;
		}
		return g;
	}

	public Edge[] addEdges (){
		EdgeListNode[] nodes = new EdgeListNode[size];
		for (int i=0;i<size;i++)
			nodes[i] = new EdgeListNode ();
		for (int i=0;i<size;i++){
			for (int j=0;j<i;j++){
				Edge e = new Edge (i,j, w);
				nodes[i].addEdge (e);
			}
		}
		Edge[] edges = new Edge[size*NEIGHBOURS_SIZE];
		for (int i=0;i<size;i++){
			EdgeListNode n = nodes[i];
			for (int j=0;j < NEIGHBOURS_SIZE;j++){
				edges[i*NEIGHBOURS_SIZE + j] = n.getShortestEdge ();
			}
		}
		return edges;
	}
	
	private class EdgeListNode {
		
		ArrayList<Edge> edges;
		int numTaken = 0;
		int numAdded = 0;
		
		public void addEdge (Edge e){
			Edge lastEdge = edges.get (numAdded);
			if (e.getDistance () > lastEdge.getDistance ())
				return;
			edges.remove (numAdded);
			edges.add (e);
			Collections.sort (edges);
			if (numAdded < NEIGHBOURS_SIZE-1)
				numAdded++;
		}
		
		public Edge getShortestEdge (){
			numTaken++;
			return edges.get (numTaken-1);
		}
		
	}
}
