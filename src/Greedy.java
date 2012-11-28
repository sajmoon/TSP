import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * Works now. Max points with 2opt was 6 @ kattis. fuckin' sucks
 * @author jens
 *
 */
public class Greedy extends Algorithm{

	static final int NEIGHBOURS_SIZE = 500;

	World w;
	int size;
	double width;
	double height;	

	@Override
	public Graph graphSolve (World w) {
		this.w = w;
		this.size = w.getSize ();
		width = w.getWidth ();
		height = w.getHeight ();
		Edge[] edges = addEdges ();
		Arrays.sort (edges);
		return createSolutionGraph (edges);
	}

	public Graph createSolutionGraph (Edge[] edges){
		Graph g = new Graph (size);
		int checkedEdges=0;
		//Main loop
		while (g.addedEdges < size){
			// i denna loop stannar den sjuuuukt länge!!
			// TODO: Varför? Den skapar en addEdge ungefär var 10 eller 20 gång. Tar sjukt långtid. Fast kanske ska vara så.
			Edge cur = edges[checkedEdges];
			// If it does not violate graph rules, then
			g.addEdge (cur);
			// edges.remove (checkedEdges);
			checkedEdges++;
		}
		return g;
	}
	public Edge[] addEdges (){
		int numEdges = sumTo (size-1);
		Edge[] edges = new Edge[numEdges];
		int edgeIndex = 0;
		for (int i=0;i<size;i++){
			for (int j=0;j<i;j++){
				Edge e = new Edge (i,j, w);
				edges[edgeIndex] = e;
				edgeIndex++;
			}
		}
		return edges;
	}

	private int sumTo (int num){
		return ((num *(num+1)) / 2);
	}
	//	public Edge[] addEdges (){
//		EdgeListNode[] nodes = new EdgeListNode[size];
//		
//		for (int i=0;i<size;i++){
//			nodes[i] = new EdgeListNode ();
//			for (int j=0;j<i;j++){
//				Edge e = new Edge (i,j, w);
//				nodes[i].addEdge (e);
//			}
//		}
//		ArrayList<Edge> edges = new ArrayList<Edge> ();
//		for (int i=0;i<size;i++){
//			EdgeListNode n = nodes[i];
//			for (Edge e : n.getEdges ())
//					edges.add (e);
//		}
//		
//		Edge[] edgeArray = new Edge[edges.size ()];
//		edges.toArray (edgeArray);
//		return edgeArray;
//	}
	
	/* memory efficient node 
	private class EdgeListNode {

		ArrayList<Edge> edges = new ArrayList<Edge> ();

		public void addEdge (Edge e){
			if (edges.size () == NEIGHBOURS_SIZE){
				Edge lastEdge = edges.get (edges.size ()-1);

				if (lastEdge != null && e.getDistance () > lastEdge.getDistance ())
					return;
				edges.remove (edges.size ()-1);
			}
			edges.add (e);
			Collections.sort (edges); //TODO Ska det göras varje gång?
		}

		public ArrayList<Edge> getEdges (){
			return edges;
		}

	}*/
}
