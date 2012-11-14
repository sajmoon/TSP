import java.util.ArrayList;
import java.util.Random;

public class RandomStupid extends Algorithm {

	int size;
	boolean[] visited;
	
	@Override
	public int[] solve(World w) {
		size = w.getSize();
		visited = new boolean[size];
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		int[] answer = new int[size];
		
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		
		Random rn = new Random();
		
		for (int i = 0; i < size; i++) {
			
			int random = rn.nextInt(list.size()); 
			int value = list.get(random);
			list.remove(random);
			answer[i] = value;
		}
		
		return answer;
	}
}
