
public class GraphNode {
	double x;
	double y;
	int id;
	GraphNode previous;
	GraphNode next;
	
	public GraphNode (int id, double x, double y) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public GraphNode (int id, double x, double y, GraphNode prev) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.previous = prev;
		this.previous.next = this;
	}
	
	public void switchDirection() {
		GraphNode tmp = previous;
		previous = next;
		next = tmp;
	}
	
	public void switchRecursivelyUntilId(int id) {
		if (this.id != id) {
			next.switchRecursivelyUntilId(id);
		}
		switchDirection();
	}
}