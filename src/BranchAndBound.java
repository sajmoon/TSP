
public class BranchAndBound extends Algorithm {
	// http://www.google.se/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&ved=0CCgQFjAA&url=http%3A%2F%2Fparallel-tsp.googlecode.com%2Ffiles%2FteamDharmaPresentation.pdf&ei=4SehUNmrIMWG4ASU_4CACQ&usg=AFQjCNH5_a2DG1swAkO1JcVVqxagiGyK0g&sig2=i5528a42IZGbrR_2QdVdpQ

	// http://lcm.csa.iisc.ernet.in/dsa/node185.html
	World world;
	int size;
	
	@Override
	public int[] solve(World w) {
		world = w;
		size = world.getSize();
		int cost = all_pairs_weight();
		
		// TODO Auto-generated method stub
		
		return null;
	}
	private int all_pairs_weight() {
		for (int i = 0; i < size; i++) {
			//world.getDistance()
		}
		return size;
	}
	
	private int[] search() {
		return new int[4];
	}

}
