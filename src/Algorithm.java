
public abstract class Algorithm {

	
	public abstract Graph graphSolve (World w);
	
	public int[] solve(World w){
		return getAnswerAsArray (graphSolve (w));
	}
	
	/**
	 * Använd den i world istället.
	 * @deprecated
	 * @param g
	 * @return
	 */
	public int[] getAnswerAsArray (Graph g){
		int size = g.getSize ();
		int[] ret = new int[size];
		ret[0] = 0;
		int lastEdge = 0;
		int curEdge = g.getEdgeFrom (0, -1);
		for (int i=1;i<size;i++){
			ret[i] = curEdge;
			int tmpCur = curEdge;
			curEdge = g.getEdgeFrom (curEdge, lastEdge);
			lastEdge = tmpCur;
		}
		return ret;
	}
	
	@Override
	public String toString (){
		return this.getClass ().getSimpleName ();
	}
	
}
