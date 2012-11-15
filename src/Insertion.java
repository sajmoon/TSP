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
			int bestCity = -1; 
			for (int i = 0; i < list.size(); i++) {
				
				int tmp_new_city = world.getNearestCity(list.get(i), visited);
				if (addAfter < 0) {
					addAfter = i;
					bestCity = tmp_new_city;
				} else if (world.getDistanceTo(addAfter, bestCity) > world.getDistanceTo(list.get(i), tmp_new_city)) {
					bestCity = tmp_new_city;
					addAfter = i;
				}
				
			}
			System.out.println("add " + bestCity + " after " + addAfter + " total \tlist now:\t " + list.size());
			
			list.add(addAfter, bestCity);
			visited[bestCity] = true;
			
			if (list.size() >= world.size)
				break;
		}
		
		return getKattisOutput();
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
