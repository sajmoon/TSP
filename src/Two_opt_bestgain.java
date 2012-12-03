
public class Two_opt_bestgain implements Optimization{

	@Override
	public Graph optimize (World w, Graph g) {
		int n1 = -1;
		int n11 = -1;
		int n2 = -1;
		int n22 = -1;
		double bestImprovement = 0;
		
		for (int node1 = 0;node1 < w.size-1; node1++){
			for (int node2 = node1 + 1; node2 < w.size-2; node2++){
				int node11 = g.getNext (node1);
				int node22 = g.getNext (node2);
				
				double dist1 = w.getDistanceTo (node1, node11);
				dist1+= w.getDistanceTo (node2, node22);
				
				double dist2 = w.getDistanceTo (node1, node2);
				dist2 += w.getDistanceTo (node11, node22);
				
				if (dist1 > dist2 ) {
					if ( (dist1 - dist2) > bestImprovement) {
						n1 = node1;
						n2 = node2;
						n11 = node11;
						n22 = node22;
						bestImprovement = dist1 - dist2;
					}
					
				}
			}
		}
		
		if (n1 != -1) 
			g.switchEdges (n1, n2, n11, n22);
		return g;
	} 
}
