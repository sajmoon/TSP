import java.util.Random;


public class twoOpt implements Optimization {

	public int[] optimize(World w, int[] answer, long timeLeft) {
		
		int[] newAnswer = new int[answer.length];
		int newI = 0, newJ = 0;
		double bestLenght = 0;
		
		for (int i = 0; i < (answer.length-2); i++) {
			newAnswer[i] = answer[i];
			for (int j = i+2; j < answer.length-2; j++) {
				double originalLength = 0;
				originalLength += w.getDistanceTo(answer[i], answer[i+1]);
				originalLength += w.getDistanceTo(answer[j], answer[j+1]);
				
				double newLength = 0;
				
				newLength += w.getDistanceTo(answer[i], answer[j]);
				newLength += w.getDistanceTo(answer[j+1], answer[i+1]);
				
				if (Double.compare(newLength, originalLength) < 0 ) {
					if ( (i == 0) || (Double.compare(newLength, bestLenght) < 0) ) {
						newI = i;
						newJ = j;
						bestLenght = newLength;
					}
				}
			}
			
		}
		
//		System.out.println(" byter: " + newI + " " + newJ);
		
		for (int a = 1; a < (newJ+1)-newI; a++) {
			newAnswer[newI+a] = answer[newJ-a+1];
		}
		for (int a = newJ+1; a < answer.length; a++) {
			newAnswer[a] = answer[a];
		}
		
		return newAnswer;
		
	}
	
	@Override
	public String toString (){
		return "2-opt";
	}
}
