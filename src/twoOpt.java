
public class twoOpt implements Optimization {

	public int[] optimize(World w, int[] answer, long timeLeft) {
		int STEP = 5;
		for (int i = 0; i < answer.length - STEP; i += 1) {
			//hoppa två steg
			double originalLength = 0;
			
			originalLength += w.getDistanceTo(answer[i], answer[i+1]);
			originalLength += w.getDistanceTo(answer[i+1], answer[i+2]);
			originalLength += w.getDistanceTo(answer[i+2], answer[i+3]);
			
			double newLength = 0;
			
			newLength += w.getDistanceTo(answer[i], answer[i+2]);
			newLength += w.getDistanceTo(answer[i+2], answer[i+1]);
			newLength += w.getDistanceTo(answer[i+1], answer[i+3]);
			
			System.out.println("orig: " + originalLength + " new: " + newLength );
			
			if (Double.compare(newLength, originalLength) < 0 ) {
				System.out.println("do the switch");
				int tmp = answer[i+1]; 
				answer[i+1] = answer[i+2];
				answer[i+2] = tmp;
			}
		}
		return answer;
		
	}
}
