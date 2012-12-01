
public class Graph {

	Node[] nodes;

	public Graph (int size, World w){
		nodes = new Node[size];
		for (int i=0;i<size;i++)
			nodes[i] = new Node ();
	}

	public int getSize () {
		return nodes.length;
	}

	public int getNext (int node){
		return nodes[node].to;
	}
	
	public int getPrev (int node){
		return nodes[node].from;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int curNode = 0;
		for (int i=0;i<getSize();i++){
			sb.append(curNode);
			sb.append("\n");
			curNode = getNext (curNode);
		}
		return sb.toString();
	}
	
	public void addEdge (int from, int to) {
		try {
			nodes[from].to = to;
			nodes[to].from = from;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class Node{
		int to;
		int from;
		
		@Override
		public String toString (){
			return "("+from+","+to+")";
		}
		
		public void reverse (){
			int tmp = to;
			to = from;
			from = tmp;
		}
	}

	public int[] toArray () {
		int[] ret = new int[getSize ()];
		int curNode = 0;
		for (int i=0;i<getSize();i++){
			ret[i] = curNode;
			curNode = getNext (curNode);
		}
		return ret;
	}

	public void switchEdges (int node1, int node2, int node11, int node22) {
		nodes[node1].to = node2;
		nodes[node2].to = node1;
		nodes[node11].from = node22;
		nodes[node22].from = node11;
		
		reverse (node2, node11);
	}

	private void reverse (int from, int to) {
		nodes[from].reverse ();
		if (from == to)
			return;
		reverse (nodes[from].to, to);
	}

	public void treOptSwitch(int node1, int node11, int node2, int node22, int node3, int node33) {
		nodes[node1].to = node22;
		nodes[node2].to = node33;
		nodes[node3].to = node11;
		
		nodes[node22].from = node1;
		nodes[node33].from = node2;
		nodes[node11].from = node3;
	}
	
	public void undoTreOptSwitch(int node1, int node11, int node2, int node22, int node3, int node33) {
		nodes[node1].to = node11;
		nodes[node2].to = node22;
		nodes[node3].to = node33;
		
		nodes[node11].from = node1;
		nodes[node22].from = node2;
		nodes[node33].from = node3;
	}
	
	public boolean hasCycle() {
		int startNode = 0;
		int curNode = getNext(startNode);
		int visitedNodes = 1;
		while (visitedNodes < getSize()) {
			if (curNode == startNode)
				return true;
			curNode = getNext(curNode);
			visitedNodes++;
		}
		
		if (curNode != startNode)
			return true;
		
		return false;
		
	}
}
