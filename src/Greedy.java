import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
		GreedyGraph g = new GreedyGraph (size);
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
		Graph gr = new Graph (size, w);
		int lastEdge = 0;
		int curEdge = g.getEdgeFrom (0, -1);
		gr.addEdge (0, curEdge);
		for (int i=1;i<size;i++){
			int tmpCur = curEdge;
			curEdge = g.getEdgeFrom (curEdge, lastEdge);
			gr.addEdge (tmpCur, curEdge);lastEdge = tmpCur; 
		}
		return gr;
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

	private class GreedyNode {
			private ArrayList<Edge> edges = new ArrayList<Edge> ();
			private int id;

			public GreedyNode(int id) {
				this.id = id;
			}
			
			public void addEdge (Edge e){
				edges.add (e);
			}

			public int degree (){
				return edges.size ();
			}

			public ArrayList<Edge> getEdges (){
				return edges;
			}

			public Edge getEdgeExludeDestination (int exclude){
				for (Edge e : edges){
					if (e.to != exclude)
						return e;
				}
				return null;
			}
		
			public int getID() {
				return this.id;
			}

			public void clearEdges() {
				edges = new ArrayList<Edge>();
			}

			/** 
			 * node 1 egde[ 5 1 ] [1 7]
			 * 1.change(5, 9)
			 * [9 1] [ 1 7]
			 * Hittar en edge (from), och pekar om den till en ny nod (to)
			 * @param from
			 * @param to
			 */
			public void changeEdge(int from, int to) {
				for (Edge e : edges){
					if (e.to == from || e.from == from )  {
						if (e.from == from) {
							e.from = to;
						} else {
							e.to = to;
							e.to = e.to;
						}
					}
				}
			}
	}
	

	private class GreedyGraph {

			HashMap<Integer, GreedyNode> greedyNodes;
			public int addedEdges;
			int size;

			public GreedyGraph (int soize){
				//g'day
				size = soize;
				greedyNodes = new HashMap<Integer,GreedyNode> ();
				for (int i=0;i<size;i++){
					greedyNodes.put (i, new GreedyNode (i));
				}
				
			}

			public boolean addEdge(Edge e){
				if (greedyNodes.get (e.from).degree () > 1 || greedyNodes.get (e.to).degree () > 1)
					return false;
				if (addedEdges+1 != size){
					//We're not about to add the last edge, check for cycle
					if (hasCycle (e))
						return false;
				}
				greedyNodes.get (e.from).addEdge (e);
				greedyNodes.get (e.to).addEdge (e.getReverse ());
				addedEdges++;
				return true;
			}
			public int getEdgeFrom (int from, int cameFrom){
				GreedyNode n = greedyNodes.get (from);
				for (Edge e : n.getEdges ()){
					if (e.to != cameFrom || cameFrom < 0)
						return e.to;
				}
				return -1;
			}

			private boolean hasCycle (Edge e) {
				if (greedyNodes.get (e.from).degree () == 0 || greedyNodes.get(e.to).degree () == 0)
					return false;

				GreedyNode n = greedyNodes.get (e.to);
				int last = e.from;
				int cur = e.to;
				while (n.getID () == e.to || n.degree () > 1){
					Edge next = n.getEdgeExludeDestination (last);
					last = cur;
					cur = next.to;
					n = greedyNodes.get (cur);
					if (cur == e.from)
						return true;
				}
				return false;
			}

			public String toString (){
				StringBuilder sb = new StringBuilder ();
				for (int i=0; i<size;i++){
					GreedyNode n = greedyNodes.get (i);
					sb.append (n.getEdges ());
				}
				return sb.toString ();
			}
			
			public GreedyNode getNode (int i){
				return greedyNodes.get (i);
			}
			
			public int getSize (){
				return size;
			}

		}


}
