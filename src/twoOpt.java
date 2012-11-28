import java.util.Random;


public class twoOpt implements Optimization {

	public Graph optimize(World w, Graph g) {
		Node x = null;
		Node y = null;
		double bestLength = 0;
		
		for (int n = 0;n < w.size; n++) {
			Node node = g.getNode (n);
			int exclude = node.getEdges().get(0).from;
			for (int i = 0; i < w.size; i++) {
				
				double originalLength = 0;
				int j = node.getID();
				int nextj = node.getEdgeExludeDestination(exclude).to;
						
				originalLength += w.getDistanceTo(j, nextj);
				
				Node node2 = g.getNode(i);
				
				int k = node2.getID();
				int nextk = node2.getEdges().get(0).to;
				originalLength += w.getDistanceTo( k, nextk);
				
				double newLength = 0;
				
				newLength += w.getDistanceTo(j, k);
				newLength += w.getDistanceTo(nextj, nextk);
//				
				if (Double.compare(newLength, originalLength) < 0 ) {
					if ( (x == null) || (Double.compare(originalLength - newLength, bestLength) > 0) ) {
						x = node;
						y = node2;
						bestLength = originalLength - newLength;
					}
				}
			}
		}
//		
//		// Do the switch
//		
//		g.switchNodes(x,y);
//		
		
		return g;
	}
	
	public int[] optimize(World w, int[] answer, long timeLeft) {
		
		Graph g = new Graph(answer.length);
		
		for (int i = 0; i < answer.length; i++) {
			g.addEdge(new Edge(answer[i], answer[i+1], w));
		}
		
		g = optimize(w, g);
		return w.getAnswerAsArray(g);
//		
//		g.addEdge(e)
//		int[] newAnswer = new int[answer.length];
//		int newI = 0, newJ = 0;
//		double bestLenght = 0;
//		
//		for (int i = 0; i < (answer.length-1); i++) {
//			newAnswer[i] = answer[i];
//			for (int j = i+1; j < answer.length-1; j++) {
//				double originalLength = 0;
//				originalLength += w.getDistanceTo(answer[i], answer[i+1]);
//				originalLength += w.getDistanceTo(answer[j], answer[j+1]);
//				
//				double newLength = 0;
//				
//				newLength += w.getDistanceTo(answer[i], answer[j]);
//				newLength += w.getDistanceTo(answer[j+1], answer[i+1]);
//				
//				if (Double.compare(newLength, originalLength) < 0 ) {
//					if ( (newI == 0) || (Double.compare(originalLength - newLength, bestLenght) > 0) ) {
//						newI = i;
//						newJ = j;
//						bestLenght = originalLength - newLength;
//					}
//				}
//			}
//			
//		}
//		
////		System.out.println(" byter: " + newI + " " + newJ);
//		
//		for (int a = 1; a < (newJ+1)-newI; a++) {
//			newAnswer[newI+a] = answer[newJ-a+1];
//		}
//		for (int a = newJ+1; a < answer.length; a++) {
//			newAnswer[a] = answer[a];
//		}
//		
//		return newAnswer;
//		
	}
	
	@Override
	public String toString (){
		return "2-opt";
	}
}
