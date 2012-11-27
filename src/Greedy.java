
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
	public int[] solve (World w) {
//		try{
		this.w = w;
		this.size = w.getSize ();
		width = w.getWidth ();
		height = w.getHeight ();
		return getAnswer (getSolutionGraphFromEdges (getEdges ()));
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
//		int numEdges = sumTo (size-1);
		for (int i=0;i<size;i++){
			for (int j=0;j<i;j++){
				Edge e = new Edge (i,j);
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

	private int sumTo (int num){
		return ((num *(num+1)) / 2);
	}


	private int[] getAnswer (Graph g){
		int[] ret = new int[size];
		ret[0] = 0;
		int lastEdge = 0;
		int curEdge = g.getEdgeFrom (0, -1);
		for (int i=1;i<size;i++){
			ret[i] = curEdge;
			int tmpCur = curEdge;
			curEdge = g.getEdgeFrom (curEdge, lastEdge);
			lastEdge = tmpCur;
		}
		return ret;
	}

	private class Edge implements Comparable<Edge>{
		public int from,to;
		boolean isDummy = false;

		public Edge (int fro, int t){
			from = fro;
			to = t;
		}
		
		public Edge (boolean dummy){
			isDummy = dummy;
		}
		
		public Edge getReverse(){
			return new Edge (to, from);
		}

		@Override
		public int compareTo (Edge o) {
			if (isDummy && o.isDummy)
				return 0;
			if (isDummy)
				return 1;
			if (o.isDummy)
				return -1;
			
			double dist = w.getDistanceTo (from, to);
			if (o.getDistance () > dist)
				return 1;
			if (dist > o.getDistance ())
				return -1;
			return 0;

		}

		@Override
		public boolean equals (Object eO){
			if (!(eO instanceof Edge))
				return false;
			Edge e = (Edge)eO;
			if (e.to == to && e.from == from)
				return true;
			if (e.to == from && e.from == to)
				return true;
			return false;
		}

		@Override
		public int hashCode (){
			return (from*to)+(from+to);
		}

		public String toString (){
			return "("+from+", "+to+")";
		}
		
		public double getDistance (){
			if (isDummy)
				return Double.MAX_VALUE;
			return w.getDistanceTo (from, to);
		}
	}

	private class Graph {

		HashMap<Integer, Node> nodes;
		int numEdges;
		public int addedEdges;

		public Graph (int size){
			numEdges = size;
			nodes = new HashMap<Integer,Node> ();
			for (int i=0;i<size;i++){
				nodes.put (i, new Node ());
			}
		}

		public boolean addEdge(Edge e){
			if (nodes.get (e.from).degree () > 1 || nodes.get (e.to).degree () > 1)
				return false;
			if (addedEdges+1 != numEdges){
				//We're not about to add the last edge, check for cycle
				if (hasCycle (e))
					return false;
			}
			nodes.get (e.from).addEdge (e);
			nodes.get (e.to).addEdge (e.getReverse ());
			addedEdges++;
			return true;
		}
		public int getEdgeFrom (int from, int cameFrom){
			Node n = nodes.get (from);
			for (Edge e : n.getEdges ()){
				if (e.to != cameFrom || cameFrom < 0)
					return e.to;
			}
			return -1;
		}

		private boolean hasCycle (Edge e) {
			if (nodes.get (e.from).degree () == 0 || nodes.get(e.to).degree () == 0)
				return false;

			Node n = nodes.get (e.to);
			int visitedNodes = 0;
			int last = e.from;
			int cur = e.to;
			while (n.degree () > 0 && visitedNodes < size){
				Edge next = n.getEdgeExludeDestination (last);
				if (next == null)
					return false;
				last = cur;
				cur = next.to;
				n = nodes.get (cur);
				visitedNodes++;
				if (cur == e.from && visitedNodes < size)
					return true;
			}
			return false;
		}

		public String toString (){
			StringBuilder sb = new StringBuilder ();
			for (int i=0; i<size;i++){
				Node n = nodes.get (i);
				sb.append (n.getEdges ());
			}
			return sb.toString ();
		}

	}

	private class Node {
		private ArrayList<Edge> edges = new ArrayList<Edge> ();

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
	}
	
	private class EdgeListNode {
		
		Edge[] edges = new Edge[NEIGHBOURS_SIZE];
		int numTaken = 0;
		int numAdded = 0;
		
		public EdgeListNode (){
			Arrays.fill (edges, new Edge (true));
		}
		
		public void addEdge (Edge e){
			if (e.getDistance () > edges[numAdded].getDistance ())
				return;
			edges[numAdded] = e;
			Arrays.sort (edges);
			if (numAdded < NEIGHBOURS_SIZE-1)
				numAdded++;
		}
		
		public Edge getShortestEdge (){
			numTaken++;
			return edges[numTaken-1];
		}
		
	}
}
