import static org.junit.Assert.*;

import org.junit.Test;


public class GraphTest {

	@Test
	public void test1basic() {
		Graph g = new Graph(10);
		for (int i = 0; i < 10; i++) {
			assertEquals(i, g.getNode(i).getID());
		}
	}
	
	@Test
	public void test2basic() {
		Graph g = new Graph(10);
		for (int i = 0; i < 10; i++) {
			assertEquals(0, g.getNode(i).degree());
			assertEquals(0, g.getNode(i).getEdges().size());
		}
		
		for (int i = 0; i < 10; i++) {
			assertEquals(0, g.getNode(i).getEdges().size());
		}
	}
	
	@Test
	public void test3basic() {
		Graph g = new Graph(10);
		World w = new World(10);
		
		for (int i = 0; i < 10 - 1; i++) {
			boolean added = g.addEdge(new Edge(i, i + 1, w));
			assertEquals(added, true);
		}
		boolean added = g.addEdge(new Edge(0, 9, w));

		for (int i = 0; i < 10; i++) {
			assertEquals(2, g.getNode(i).degree());
		}
	}
	
	@Test
	public void test4basic() {
		Graph g = new Graph(10);
		World w = new World(10);
		
		for (int i = 0; i < 10 - 1; i++) {
			boolean added = g.addEdge(new Edge(i, i + 1, w));
			assertEquals(added, true);
		}	
	}
}
