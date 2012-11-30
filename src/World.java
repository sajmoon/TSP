
public class World {
	double[][] positions;
	double[][] matrix;
	int nextIndex;
	int size;
	double largestX = 0;
	double largestY = 0;

	Triple[][] neighbourlist;
	int neighbourlistSize = 60;

	static final boolean DEBUG = false;

	public World(int inputSize) {
		size = inputSize;
		positions = new double[size][2];
		matrix = new double[size][size];
		nextIndex = 0;
		if (size < neighbourlistSize)
			neighbourlistSize = size;
		neighbourlist = new Triple[size][neighbourlistSize];
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
		return (dx*dx) + (dy * dy);
	}

	/*
	public void makeNeighbourList() {
		for (int i = 0; i < size-1; i++) {
			TreeSet<Triple> h = new TreeSet<Triple>();
			for (int j = 0; j < size-1; j++) {
				if (i == j) {
				} else {
					h.add(new Triple(i, j, this.matrix[i][j]));
				}
			}
	
			for (int x = 0; x < neighbourlistSize-2; x++) {
				 neighbourlist[i][x] = h.pollFirst();
				if (DEBUG)
					System.out.println("makeNeigbourList() Nei[" + i + "][" + x + "] distance: " + neighbourlist[i][x].to + " " + neighbourlist[i][x].distance);
			}
			h.clear();
		}
	}
	*/

	public class Triple implements Comparable<Triple> {
		public final int from;
		public final int to;
		public final double distance;
		public Triple(int from, int to, double distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}

		@Override
		public int compareTo(Triple i) {
			if (this.distance == i.distance)
				return 0;
			else if (this.distance < i.distance)
				return -1;
			else
				return 1;
		}
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
		for (int i = 0; i < size-1; i++) {
			length += this.getDistanceTo(answer[i], answer[i+1]);
		}
		length += this.getDistanceTo(answer[answer.length-1], answer[0]);
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
//		int nearest = 0;
//		for (int i = 0; i < size; i++) {
//			if (!visited[i]) {
//				nearest = i;
//				break;
//			}
//		}
//		if (nearest == 0) {
//			//backupplan om alla i neighbourlist är besökta.
//			double shortestDist = Double.MAX_VALUE;
//			for (int i=0;i<size;i++){
//				if (i == city)
//					continue;
//				double dist = matrix[city][i];
//				if (dist < shortestDist && !visited[i]){
//					shortestDist = dist;
//					nearest = i;
//				}
//			}
//			return nearest;
//		}
//		return nearest;
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

	public double getPositionX(int i) {
		return positions[i][0];
	}

	public double getPositionY(int i) {
		return positions[i][1];
	}

	public double getWidth (){
		return largestX;
	}

	public double getHeight (){
		return largestY;
	}

}

