import java.util.Random;


public class TreOptRandom implements Optimization {
	
	@Override
	public Graph optimize (World w, Graph g) {
		
		// TODO Borde göra bättre val.
		// Tex hålla koll på vilka kanter som är längst. 
		// Säg de 10 längsta och sedan försöka byta dom.
		
		
		Random r =  new Random();
		int node1 = r.nextInt(g.getSize());
		int node11 = g.getNext(node1);
		int node2 = r.nextInt(g.getSize());
		int node22 = g.getNext(node2);
		int node3 = r.nextInt(g.getSize());
		int node33 = g.getNext(node3);
		
		if ( node1 == node2 || node1 == node3 || node3 == node2)
			return g;
		
		double orgdist = w.getDistanceTo(node1, node11) + w.getDistanceTo(node2, node22) + w.getDistanceTo(node3, node33);
		double newdist = w.getDistanceTo(node1, node2) + w.getDistanceTo(node2, node33) + w.getDistanceTo(node3, node11);
		
		if (orgdist > newdist) {
			g.treOptSwitch(node1, node11, node2, node22, node3, node33);
			
			if (g.hasCycle()) {
				g.undoTreOptSwitch(node1, node11, node2, node22, node3, node33);
			}
		}
		
		return g;
	}

}
