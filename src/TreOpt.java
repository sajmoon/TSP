import java.util.Random;

public class TreOpt implements Optimization { 
	@Override
	public Graph optimize (World w, Graph g) {
		Random r = new Random();
		
		
		int b1 = -1;
		int b11 = -1;
		int b2 = -1;
		int b22 = -1;
		int b3 = -1;
		int b33 = -1;
		double best = 0;
		
		int LIMIT = w.size/3;
		
		int node1 = r.nextInt(g.getSize());
		int node11 = g.getNext(node1);
		
		int node2 = g.getNext( node11 );
		int node22 = g.getNext(node2);
		
		
		
		double orignode1dist = w.getDistanceTo(node1, node11);
		
		for (int i = 0; i < LIMIT; i++) {
			node2 = g.getNext(node2);
			node22 = g.getNext(node2);
			if (node2 == node1)
				break;
			
			double orignode2dist = w.getDistanceTo(node2, node22);
			
			int node3 = g.getNext( node22 );
			int node33 = g.getNext(node3);
			
			for (int j = 0; j < LIMIT; j++) {
				
				node3 = g.getNext(node3);
				node33 = g.getNext(node3);
				
				if (node33 == node1)
					break;
				
				double orignode3dist = w.getDistanceTo(node3, node33);
				
				double org = orignode3dist + orignode2dist + orignode1dist;
				
				double newdistance = w.getDistanceTo(node1, node22) + w.getDistanceTo(node3, node11) + w.getDistanceTo(node2, node33);
				
				
				if ( (org > newdistance  ) ) {
					if (best < (org - newdistance )) {
						b1 = node1;
						b11 = node11;
						b2 = node2;
						b22 = node22;
						b3 = node3;
						b33 = node33;
						best = org - newdistance;
						
					}
				}
			}
		}
		
		if (best > 0 ) {
			g.treOptSwitch(b1, b11, b2, b22, b3, b33);
			
		}
		
		
		return g;
	}
	
	
}
