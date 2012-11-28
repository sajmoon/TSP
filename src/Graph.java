import java.util.HashMap;

public class Graph {

		HashMap<Integer, Node> nodes;
		public int addedEdges;
		int size;

		public Graph (int soize){
			//g'day
			size = soize;
			nodes = new HashMap<Integer,Node> ();
			for (int i=0;i<size;i++){
				nodes.put (i, new Node (i));
			}
			
		}

		public boolean addEdge(Edge e){
			if (nodes.get (e.from).degree () > 1 || nodes.get (e.to).degree () > 1)
				return false;
			if (addedEdges+1 != size){
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
		
		public Node getNode (int i){
			return nodes.get (i);
		}
		
		public int getSize (){
			return size;
		}

	}
