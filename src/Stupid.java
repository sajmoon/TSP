
public class Stupid {

	int size;
	public Stupid(World w) {
		size = w.getSize ();
	}
	
	public int[] solve() {
		int[] answer = new int[size];
		for (int i = 0; i < size; i++) {
			answer[i] = i;
		}
		return answer;
	}
}
