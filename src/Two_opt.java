
public class Two_opt implements Optimization{

	@Override
	public Graph optimize (World w, Graph g) {
		for (int node1=0;node1<w.size;node1++){
			for (int node2=node1+1;node2<w.size;node2++){
				int node11 = g.getNext (node1);
				int node22 = g.getNext (node2);
				
				double dist1 = w.getDistanceTo (node1, node11);
				dist1+= w.getDistanceTo (node2, node22);
				
				double dist2 = w.getDistanceTo (node1, node2);
				dist2 += w.getDistanceTo (node11, node22);
				
				if (dist1 > dist2 ) {
					g.switchEdges (node1, node2, node11, node22);
				}
			}
		}
		return g;
	}
	
	

}
