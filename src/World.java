public class World {
	double[][] positions;
	double[][] matrix;
	int nextIndex;
	int size;
	double largestX = 0;
	double largestY = 0;
	
	static final boolean DEBUG = false;
	
	public World(int inputSize) {
		size = inputSize;
		positions = new double[size][2];
		matrix = new double[size][size];
		nextIndex = 0;
	}
	
	public void add(double x, double y) {
		if (x > largestX)
			largestX = x;
		if (y > largestY)
			largestY = y;
		positions[nextIndex][0] = x;
		positions[nextIndex][1] = y;
		
		for (int i = 0; i < nextIndex; i++) {
			double dist = calculateDistance(i, nextIndex);
			matrix[i][nextIndex] = dist;
			matrix[nextIndex][i] = dist;
		}
		nextIndex++;
	}
	
	private double calculateDistance(int p1, int p2) {
		double dx = positions[p1][0] - positions[p2][0];
		double dy = positions[p1][1] - positions[p2][1];
		
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
	
	public void printSolution(int[] answer) {
		if(DEBUG){
		printWorldPositions();
		printWorldMatrix();
		}
		
		for (int i = 0; i < answer.length; i++) {
			System.out.println (answer[i]);
		}
	}
	
	public double getTotalDistance(int[] answer) {
		double length = 0;
		for (int i = 0; i < size; i++) {
			length += this.getDistanceTo(answer[i], answer[i+1]);
		}
		return length;
	}
	
	public double getDistanceIn(int[] answer, int start, int end) {
		int dist = 0;
		for (int i = start; i < end; i++) {
			dist += this.getDistanceTo(i,i+1);
		}
		return dist;
	}
	
	public void printWorldPositions() {
		if (!DEBUG)
			return;
		System.out.println("All positions in the wooooorld");
		for (int i = 0; i < size; i++) {
			System.out.println("pos: " + positions[i][0] + ", " + positions[i][1]);
		}
	}
	
	public void printWorldMatrix() {
		if (!DEBUG)
			return;
		System.out.println("Matrix of the wooorld");
		for (int i = 0; i < size; i++) {
			System.out.println("");
			for (int j = 0; j < size; j++) {
				System.out.print("[" + sameLength(round(matrix[i][j])) + "]");
			}
		}
	}
	
	String sameLength(double d) {
		return String.format("%6s", d);  
	}
	
	double round(double d) {
//        DecimalFormat twoDForm = new DecimalFormat("#.##");
//        return Double.valueOf(twoDForm.format(d));
		return d;
	}
	
	public int getNearestCity (int city, boolean[] visited){
		double shortestDist = Double.MAX_VALUE;
		int nearest = 0;
		for (int i=0;i<size;i++){
			if (i == city)
				continue;
			double dist = matrix[city][i];
			if (dist < shortestDist && !visited[i]){
				shortestDist = dist;
				nearest = i;
			}
		}
		return nearest;
	}
	
	public double getDistanceTo (int from, int to){
		return matrix[from][to];
	}
	
	public int getSize (){
		return size;
	}
	
	public double[][] getPositions (){
		return positions;
	}
	
	public double getWidth (){
		return largestX;
	}
	
	public double getHeight (){
		return largestY;
	}
}

