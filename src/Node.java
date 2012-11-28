import java.util.ArrayList;

public class Node {
		private ArrayList<Edge> edges = new ArrayList<Edge> ();
		private int id;

		public Node(int id) {
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
}
