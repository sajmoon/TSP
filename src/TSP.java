import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TSP {
	public static long startTime;
	public static long cutoffTime;
	

	public static final Algorithm[] algorithms = {new NearestNeighbour(), new Greedy()};
	public static final Optimization[] optimizations = {new Two_opt(), new RandomTwoOpt(), new TreOpt(), new Two_opt_bestgain()};

	/**
	 * Sample input
	 * 10
	 * 95.0129 61.5432
	 * 23.1139 79.1937
	 * 60.6843 92.1813
	 * 48.5982 73.8207
	 * 89.1299 17.6266
	 * 76.2097 40.5706
	 * 45.6468 93.5470
	 * 1.8504 91.6904
	 * 82.1407 41.0270
	 * 44.4703 89.3650
	 */
	public static void main(String[] args) {
		// TODO Krama Jens
		startTime = System.currentTimeMillis();
		cutoffTime = 1800;

		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			runWithDefinedInput (in);
		} catch(Exception e){
		}

	}

	public static void runWithDefinedInput(BufferedReader in){
		runSolverAndPrintToConsole (algorithms[0], in);
	}
	
	/**
	 * Standard KATTIS output
	 * @param algo
	 * @param in
	 */
	public static void runSolverAndPrintToConsole (Algorithm algo, BufferedReader in){
		Utils.printAnswer (runSolver(algo,in).toArray ());
	}
	
	public static void runSolverAndPrintToConsoleWithDistance (Algorithm algo, BufferedReader in){
		try {
			World w = makeWorld (in);
			Utils.printAnswerAndDistance (solveForWorld(algo,w).toArray (),w);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static World makeWorld (BufferedReader in) throws NumberFormatException, IOException{
		String inLine = in.readLine();
		int size = Integer.parseInt(inLine);
		World w = new World(size);

		while (in.ready()) {
			String s = in.readLine();
			String[] a = s.split(" ");

			w.add(Double.parseDouble(a[0]), Double.parseDouble(a[1]));
		}
		
//		w.makeNeighbourList();
		return w;
	}
	
	public static Graph runSolver (Algorithm algo, BufferedReader in){
		try{
			World w = makeWorld (in);
			
			Graph answer = solveForWorld(algo, w);
			
//			Optimization twoOpt = new twoOpt();
//
//			for (int i=0;i<5;i++){
//				answer = optimizeResult(twoOpt, answer, w);
//			}

			return answer;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static Graph optimizeResult(Optimization opt, Graph g, World w) {
		g = opt.optimize(w, g);
		return g;
	}

	
	public static Graph solveForWorld (Algorithm algo, World w){
		
		Graph g = algo.graphSolve(w);
		Optimization o =  new Two_opt();
		for (int i = 0; i < 10; i++) {
			g = o.optimize(w, g);
		}

		Optimization two =  new Two_opt();
		Optimization tre =  new TreOpt();
		Optimization random_two =  new RandomTwoOpt();
		
		for (int i = 0; i < 20; i++) {
			g = two.optimize(w, g);
		}
//		
//		g = tre.optimize(w, g);
//		g = tre.optimize(w, g);
//		g = tre.optimize(w, g);
//		g = tre.optimize(w, g);
//		g = tre.optimize(w, g);
//		g = tre.optimize(w, g);
//		
//		for (int i = 0; i < 40; i++) {
//			g = two.optimize(w, g);
//		}
//		
		return g;
	}
	
	public static void printWorldDistance(int[] answer, World w) {
		double totDistance = 0;
		for (int i = 0; i < answer.length-1; i++) {
			totDistance += w.getDistanceTo(answer[i], answer[i+1]);
		}
		System.out.println("Total distance: " + totDistance);
	}

}
