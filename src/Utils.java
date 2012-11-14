
public class Utils {

	static final boolean DEBUG = true;
	final static String sep = System.getProperty ("file.separator");

	public static void printAnswer (int[] answer){
		for (int i = 0; i < answer.length; i++) {
			System.out.println (answer[i]);
		}
	}

	public static double printAnswerAndDistance (int[] answer, World w){
		printAnswer (answer);
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
		System.out.println ("Total distance: "+totalDistance);
		return totalDistance;
	}

	public static void printSolution(int[] answer, World w) {
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
