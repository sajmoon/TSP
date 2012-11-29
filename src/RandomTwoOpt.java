import java.util.Random;


public class RandomTwoOpt  implements Optimization {

	@Override
	public Graph optimize (World w, Graph g) {
		
		Random r = new Random();
		int node1 = r.nextInt (g.getSize());
		int node2 = r.nextInt(g.getSize());
		int node11 = g.getNext (node1);
		int node22 = g.getNext (node2);
		
		if (node1 != node2)
			g.switchEdges (node1, node2, node11, node22);
		return g;
	}

}
