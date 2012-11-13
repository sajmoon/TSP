
public class twoOpt implements Optimization {

	public int[] optimize(World w, int[] answer, long timeLeft) {
		int STEP = 5;
		for (int i = 0; i < answer.length - STEP; i += STEP) {
			//hoppa två steg
			double originalLength = 0;
			
			originalLength += w.getDistanceTo(i, i+1);
			originalLength += w.getDistanceTo(i+1, i+2);
			originalLength += w.getDistanceTo(i+2, i+3);
			
			double newLength = 0;
			
			newLength += w.getDistanceTo(i, i+2);
			newLength += w.getDistanceTo(i+2, i+1);
			newLength += w.getDistanceTo(i+1, i+3);
			
			if (newLength < originalLength) {
				int tmp = answer[i+1]; 
				answer[i+1] = answer[i+2];
				answer[i+2] = tmp;
				
			}
		}
		return answer;
		
	}
}
