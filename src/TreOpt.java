import java.util.Random;

public class TreOpt implements Optimization { 
	@Override
	public Graph optimize (World w, Graph g) {
		Random r = new Random();
		
		int node1 = r.nextInt(g.getSize());
		int node11 = g.getNext(node1);
		int node2 = r.nextInt(g.getSize());
		int node22 = g.getNext(node2);
		int node3 = r.nextInt(g.getSize());
		int node33 = g.getNext(node3);
		
		if (node1 == node2 || node1 == node3 || node2 == node3)
			return g;
		g.treoptswitch(node1, node11, node2, node22, node3, node33);
		
		
		return g;
	}
}
