import java.util.Random;


public class twoOpt implements Optimization {

	public TraversalGraph optimize(World w, TraversalGraph g, long timeLeft) {
		GraphNode x = null;
		GraphNode y = null;
		double bestLength = 0;
		for (GraphNode node : g.list) {
			for (int i = node.next.id; i < g.list.length-1; i++) {
				double originalLength = 0;
				originalLength += w.getDistanceTo(node.id, node.next.id);
				originalLength += w.getDistanceTo(g.get(i).id, g.get(i+1).id);
				
				double newLength = 0;
				
				newLength += w.getDistanceTo(node.id, g.get(i).id);
				newLength += w.getDistanceTo(node.next.id, g.get(i+1).id);
				
				if (Double.compare(newLength, originalLength) < 0 ) {
					if ( (x == null) || (Double.compare(newLength, bestLength) < 0) ) {
						x = node;
						y = g.get(i);
						bestLength = originalLength - newLength;
					}
				}
			}
		}
		
		// Do the switch
		g.switchNodes(x,y);
		
		
		return g;
	}
	
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
					if ( (newI == 0) || (Double.compare(originalLength - newLength, bestLenght) > 0) ) {
						newI = i;
						newJ = j;
						bestLenght = originalLength - newLength;
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
