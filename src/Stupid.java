


public class Stupid extends Algorithm {

	int size;
	
	public int[] solve(World w) {
		size = w.getSize ();
		int[] answer = new int[size];
		for (int i = 0; i < size; i++) {
			answer[i] = i;
		}
		return answer;
	}
}
