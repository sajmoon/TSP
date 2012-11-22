
public class threeOpt implements Optimization {

	public int[] optimize(World w, int[] answer, long timeLeft) {
		int STEP = 5;
		for (int i = 0; i < answer.length - 1; i++) {
			for (int j = i; j < answer.length - 1; j++) {
				
			}
			//hoppa två steg
			double originalLength = 0;
			
			double newOriginalLength = w.getDistanceIn(answer, i, i+3);
			originalLength += w.getDistanceTo(answer[i], answer[i+1]);
			originalLength += w.getDistanceTo(answer[i+1], answer[i+2]);
			originalLength += w.getDistanceTo(answer[i+2], answer[i+3]);
			
			double newLength = 0;
			
			newLength += w.getDistanceTo(answer[i], answer[i+2]);
			newLength += w.getDistanceTo(answer[i+2], answer[i+1]);
			newLength += w.getDistanceTo(answer[i+1], answer[i+3]);
			
//			System.out.println("orig: " + originalLength + " new: " + newLength );
			
			if (Double.compare(newLength, originalLength) < 0 ) {
//				System.out.println("do the switch");
				int tmp = answer[i+1]; 
				answer[i+1] = answer[i+2];
				answer[i+2] = tmp;
			}
		}
		return answer;
		
	}

	@Override
	public TraversalGraph optimize(World w, TraversalGraph g, long l) {
		// TODO Auto-generated method stub
		return null;
	}
}
