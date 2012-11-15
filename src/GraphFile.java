
import java.io.File;


public class GraphFile {
		int size;
		String name;
		File file;
		
		public GraphFile (int s, String n, File f){
			size = s;
			name = n;
			file = f;
		}
		@Override
		public String toString (){
			return name+" ("+size+")";
		}
	}
