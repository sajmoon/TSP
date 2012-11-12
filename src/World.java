
public class World {
	double[][] positions;
	double[][] matrix;
	int nextIndex;
	
	public World(int size) {
		positions = new double[size][2];
		matrix = new double[size][size];
		nextIndex = 1;
	}
	
	public void add(double x, double y) {
		positions[nextIndex][0] = x;
		positions[nextIndex][0] = x;
		
		for (int i = 0; i < nextIndex; i++) {
			double dist = calculateDistance(i, nextIndex);
			matrix[i][nextIndex] = dist;
			matrix[nextIndex][i] = dist;
		}
		nextIndex++;
	}
	
	private double calculateDistance(int p1, int p2) {
		
		return 0;
	}
	
	public void printSolution(int[] answer) {
		for (int i = 0; i < answer.length; i++) {
			
		}
	}
}
