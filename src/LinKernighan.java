import java.util.ArrayList;


public class LinKernighan extends Algorithm{

	World world;
	int size;
	
	@Override
	public int[] solve(World w) {
		world = w;
		size = world.getSize();
		
		ArrayList<Integer> nodesA = new ArrayList<Integer>();
		ArrayList<Integer> nodesB = new ArrayList<Integer>();
		
		// dela upp i 2 set. 
		boolean addToSetA = true;
		for (int i = 0; i < size; i++) {
			if (addToSetA) {
				nodesA.add(i);
				addToSetA = false;
			} else {
				nodesB.add(i);
				addToSetA = true;
			}
		}
		
		
		return null;
	}
	
	public static class Node {
		public Node() {
			
		}
	}

	@Override
	public Graph graphSolve (World w) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * http://www.ida.liu.se/~TDDB19/reports_2003/htsp.pdf
	 * Pseudokode från http://en.wikipedia.org/wiki/Kernighan%E2%80%93Lin_algorithm#cite_note-ravikumar-1
	function Kernighan-Lin(G(V,E)):
		 determine a balanced initial partition of the nodes into sets A and B
		 do
			A1 := A; B1 := B
		 	compute D values for all a in A1 and b in B1
		 	for (i := 1 to |V|/2)
		 		find a[i] from A1 and b[i] from B1, such that g[i] = D[a[i]] + D[b[i]] - 2*c[a[i]][b[i]] is maximal
		        move a[i] to B1 and b[i] to A1
		        remove a[i] and b[i] from further consideration in this pass
		 		update D values for the elements of A1 = A1 / a[i] and B1 = B1 / b[i]
		 	end for
		 	find k which maximizes g_max, the sum of g[1],...,g[k]
		 	if (g_max > 0) then
		 		Exchange a[1],a[2],...,a[k] with b[1],b[2],...,b[k]
		 until (g_max <= 0)
	return G(V,E) */
	

}
