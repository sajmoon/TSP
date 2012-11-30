
public class Utils {

	static final boolean DEBUG = true;

	public static void printAnswer (Graph answer){
		System.out.println(answer);
//		for (int i = 0; i < answer.length; i++) {
//			System.out.println (answer[i]);
//		}
	}
	
	public static boolean answerHasDuplicates (int[] answer){
		boolean[] checked = new boolean[answer.length];
		for (int i=0;i<answer.length;i++){
			int node = answer[i];
			if (checked[node])
				return true;
			checked[node] = true;
		}
		return false;
	}

	public static double printAnswerAndDistance (Graph answer, World w){
		printAnswer (answer);
		double totalDistance = getAnswerDistance (answer.toArray(), w);
		System.out.println ("Total distance: "+totalDistance);
		return totalDistance;
	}
	
	public static double getAnswerDistance (int[] answer, World w){
		double totalDistance = 0;
		int back = 0;
		int front = 0;
		for (int i=0;i<answer.length;i++){
			if (i == answer.length-1){
				back = answer[i]; front = answer[0];
			}else{
				back = answer[i]; front = answer[i+1];
			}
			totalDistance+= w.getDistanceTo (back, front);
		}
		return totalDistance;
	}

	public static void printSolution(Graph answer, World w) {
		if(DEBUG){
			printWorldPositions(w);
			printWorldMatrix(w);
		}
		printAnswer(answer);
	}

	public static void printWorldPositions(World w) {
		if (!DEBUG)
			return;
		System.out.println("All positions in the wooooorld");
		for (int i = 0; i < w.size; i++) {
			System.out.println("pos: " + w.positions[i][0] + ", " + w.positions[i][1]);
		}
	}

	public static void printWorldMatrix(World w) {
		if (!DEBUG)
			return;
		System.out.println("Matrix of the wooorld");
		for (int i = 0; i < w.size; i++) {
			System.out.println("");
			for (int j = 0; j < w.size; j++) {
				System.out.print("[" + sameLength(round(w.matrix[i][j])) + "]");
			}
		}
	}

	private static double round(double d) {
		//      DecimalFormat twoDForm = new DecimalFormat("#.##");
		//      return Double.valueOf(twoDForm.format(d));
		return d;
	}

	private static String sameLength(double d) {
		return String.format("%6s", d);  
	}

}
