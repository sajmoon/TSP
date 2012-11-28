import java.util.Random;


public class twoOpt implements Optimization {

	public Graph optimize(World w, Graph g) {
		int x 		= -1;
		int xnext 	= -1;
		int y 		= -1;
		int ynext 	= -1;
		
		double bestLength = 0;
		
		System.out.println("twoopt! ");
		for (int n = 0;n < w.size; n++) {
			Node node = g.getNode (n);
			int exclude = node.getEdges().get(0).from;
			for (int i = n+1; i < w.size; i++) {
				
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
					if ( (x == -1) || (Double.compare(originalLength - newLength, bestLength) > 0) ) {
						x = j;
						xnext = nextj;
						y = k;
						ynext = nextk;
						bestLength = originalLength - newLength;
					}
				}
			}
			
			// Hittat bästa bytet för denna nod.
			
		}
		System.out.println("dist innan " + w.getTotalDistance(w.getAnswerAsArray(g)));
		if (x != -1) {
			System.out.println("byter " + x + ":" + xnext + " - " + y + ":" + ynext + " => " + bestLength);
			//Hittade ett byte som gör det bättre
			Node one = g.getNode(x);
			one.changeEdge(xnext, y);
			Node two = g.getNode(y);
			two.changeEdge(ynext, x);
			Node three = g.getNode(xnext);
			three.changeEdge(x, ynext);
			Node four = g.getNode(ynext);
			four.changeEdge(y, xnext);
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
		
		for (int i = 0; i < answer.length-1; i++) {
			g.addEdge(new Edge(answer[i], answer[i+1], w));
		}
		
		g.addEdge(new Edge(answer[answer.length-1], answer[0], w));
		
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
