import java.util.ArrayList;
import java.util.Random;

public class Insertion extends Algorithm{

	World world;
	int size;
	boolean[] visited;
	Random r;
	ArrayList<Integer> list;
	
	@Override
	public int[] solve(World w) {
		list = new ArrayList<Integer>();
		r = new Random();
		world = w;
		size = w.getSize();
		visited = new boolean[size];
		int[] result = new int[size];
		
		// bygg en första cykel, randomly
		
//		for (int i = 0; i < 2; i++) {
//			int new_node = getRandomUnvisited();
			int new_node = 0;
			list.add(new_node);
			visited[new_node] = true;
//		}
		
		while (true) {
			int addAfter = -1;
			int addBefore = -1;
			int bestCity = -1; 
			
			for (int i = 0; i < list.size(); i++) {
				
				int nextCity = addAfter + 1;
				if (nextCity > list.size()) {
					nextCity = 0;
				}
				int tmp_new_city = world.getNearestCity(list.get(i), visited);
				if (addAfter < 0) {
					addAfter = i;
					bestCity = tmp_new_city;
					addBefore = nextCity;
				} else if ( distance(addAfter, bestCity, nextCity) > distance(i, tmp_new_city, nextCity)) {
//					System.out.println("add " + bestCity + " after " + addAfter + " total \tlist now:\t " + list.size() + "distance before: " +  world.getDistanceTo(addAfter, bestCity) + world.getDistanceTo(bestCity, nextCity) + " distance after: " + world.getDistanceTo(list.get(i), tmp_new_city)  + world.getDistanceTo(tmp_new_city, list.get(nextCity)));
					bestCity = tmp_new_city;
					addAfter = i;
					addBefore = nextCity;
				}
			}
			
//			System.out.println("selecting " + bestCity + "after " + addAfter + " distance: " + (world.getDistanceTo(addAfter, bestCity) + world.getDistanceTo(bestCity, addBefore)) );
			list.add(addAfter, bestCity);
			visited[bestCity] = true;
			
			if (list.size() >= world.size)
				break;
		}
		
		return getKattisOutput();
	}
	
	private double distance(int c1, int c2, int c3) {
		double dist1 = world.getDistanceTo(list.get(c1), c2);
		double dist2 = world.getDistanceTo(c2, list.get(c3));
		return dist1 + dist2;
	}
	
	public int[] getKattisOutput() {
		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
	}
	
	public int getRandomUnvisited() {
		
		int rInt = 0;
		while (rInt == 0 || visited[rInt] ) {
			rInt = r.nextInt(size);
		}
		
		return rInt;
		
	}
	
}
