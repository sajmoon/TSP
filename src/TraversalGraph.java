
public class TraversalGraph {
	GraphNode[] list;
	World world;
	
	public TraversalGraph(int[] answer, World w) {
		list = new GraphNode[answer.length];
		this.world = w;
		
		for (int i = 0; i < answer.length; i++) {
			list[i] = new GraphNode (i, w.getPositionX(i), w.getPositionY(i));
			if ( i > 0) {
				list[i].previous = list[i-1];
				list[i-1].next = list[i];
			}
		}
		list[0].previous = list[list.length-1];
		list[list.length-1].next = list[0];
	}
	
	public int[] toIntArray() {
		int[] answer = new int[list.length];
		GraphNode current = list[0];
		for (int i = 0; i < list.length; i++) {
			
			answer[i] = current.id;
			current = current.next;
			
		}
		return answer;
	}
	
	public GraphNode get(int i) {
		return list[i];
	}
}
