import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;


public class TestCaseFactory {
	
	static final int SCALE_FACTOR = 5;
	
	final static String FILE_NAME_BASE = "C:\\Users\\Jens\\Desktop\\Dropbox\\Eclipseprojekt\\TSP\\testcases\\";
	final static String GRAPH_DB = FILE_NAME_BASE+"graphs";
	
	public static void main (String[] args){
		GraphFile gf = askAndCreateGraphFile();
		try {
			writeGraph (gf);
			addFileToFileList (gf);
			TSP.runSolverAndPrintToConsoleWithDistance (TSP.algorithms[0], new BufferedReader(new FileReader(gf.file)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static GraphFile askAndCreateGraphFile (){
		String format = JOptionPane.showInputDialog ("Input format: graphdimensions filename");
		String[] options = format.split (" ");
		int size = Integer.parseInt (options[0]);
		File fileFolder = new File (FILE_NAME_BASE);
		if (!fileFolder.exists ())
			fileFolder.mkdirs ();
		String fullFileName = FILE_NAME_BASE + options[1];
		File file = new File(fullFileName);
		while (file.exists ()){
			String fileName = JOptionPane.showInputDialog ("File exists, enter new filename");
			fullFileName = FILE_NAME_BASE + fileName;
			file = new File(fullFileName);
		}
		try {
			file.createNewFile ();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new GraphFile (size, options[1], file);
	}
	
	
	
	public static ArrayList<GraphFile> getSavedTestCases () throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader (new File (GRAPH_DB)));
		ArrayList<String> graphs = new ArrayList<String> ();
		while (reader.ready ()){
			graphs.add (reader.readLine ());
		}
		ArrayList<GraphFile> graphFiles = new ArrayList<GraphFile> ();
		for (String s : graphs){
			String[] parts = s.split (" ");
			graphFiles.add (new GraphFile (Integer.parseInt (parts[0]), parts[1], new File(parts[2])));
		}
		return graphFiles;
	}
	
	private static void addFileToFileList (GraphFile gf) throws IOException {
		File graphDb = new File (GRAPH_DB);
		if (!graphDb.exists ())
			graphDb.createNewFile ();
		FileWriter writer = new FileWriter(graphDb, true);
		writer.append ("" + gf.name + " " + gf.size + " " + gf.file.getAbsolutePath () + "\n");
		writer.close ();
	}
	
	private static void writeGraph (GraphFile gf) throws IOException{
		Random r = new Random ();
		BufferedWriter out = new BufferedWriter (new FileWriter(gf.file));
		out.write (""+gf.size+"\n");
		for (int i=0;i<gf.size;i++){
			double x = r.nextDouble ()*gf.size*SCALE_FACTOR;
			double y = r.nextDouble ()*gf.size*SCALE_FACTOR;
			String coords = ""+x+" "+y+"\n";
			out.write (coords);
		}
		out.close ();
	}
	
	

}
