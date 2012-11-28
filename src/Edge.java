public class Edge implements Comparable<Edge>{
		public int from,to;
		private World w;

		public Edge (int fro, int t, World w){
			from = fro;
			to = t;
			this.w = w;
		}
		
		public Edge getReverse(){
			return new Edge (to, from, w);
		}

		@Override
		public int compareTo (Edge o) {
			double dist = getDistance ();
			if (o.getDistance () > dist)				
				return -1;
			if (dist > o.getDistance ())
				return 1;
			return 0;

		}

		@Override
		public boolean equals (Object eO){
			if (!(eO instanceof Edge))
				return false;
			Edge e = (Edge)eO;
			if (e.to == to && e.from == from)
				return true;
			if (e.to == from && e.from == to)
				return true;
			return false;
		}

		@Override
		public int hashCode (){
			return (from*to)+(from+to);
		}

		public String toString (){
			return "("+from+", "+to+")";
		}
		
		public double getDistance (){
			return w.getDistanceTo (from, to);
		}
	}