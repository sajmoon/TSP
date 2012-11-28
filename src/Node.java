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
