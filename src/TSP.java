import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class TSP {

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
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//			BufferedReader in = new BufferedReader(new FileReader(new File("C:\\indata.txt")));
			
			String inLine = in.readLine();
			int size = Integer.parseInt(inLine);
			
			World w = new World(size);
			
			while (in.ready()) {
				String s = in.readLine();
				String[] a = s.split(" ");
				
				w.add(Double.parseDouble(a[0]), Double.parseDouble(a[1]));
			}
			
			Algorithm algo = new Greedy(w);
			
			int[] answer = algo.solve();
			
			w.printSolution(answer);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
