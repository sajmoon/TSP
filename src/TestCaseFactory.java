

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;



public class TestCaseFactory {

	static final int SCALE_FACTOR = 2;
	final static String sep = System.getProperty ("file.separator");
	final static String FILE_NAME_BASE = "testcases"+System.getProperty ("file.separator");
	final static String GRAPH_DB = FILE_NAME_BASE+"graphs";

	public static void main (String[] args){
		GraphFile gf = askAndCreateGraphFile();
		try {
			writeGraph (gf);
			addFileToFileList (gf);
			//			TSP.runSolverAndPrintToConsoleWithDistance (TSP.algorithms[0], new BufferedReader(new FileReader(gf.file)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static GraphFile askAndCreateGraphFile (){
		String format = JOptionPane.showInputDialog ("Input format: graphdimensions filename");
		String[] options = format.split (" ");
		if (options.length < 2)
			return null;
		int size = -1;
		try{
			size = Integer.parseInt (options[0]);
		} catch (NumberFormatException e){
			return null;
		}
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

	public static void removeGraphFromDb (GraphFile gf){
		try {
			File dbFile = new File (GRAPH_DB);
			File temp = new File (GRAPH_DB+"_temp");
			BufferedReader br = new BufferedReader(new FileReader(dbFile));
			PrintWriter pw = new PrintWriter(new FileWriter(temp));
			String lineToRemove = gf.name+" "+gf.size+" "+gf.file.getAbsolutePath ();
			String line = "";
			while ((line = br.readLine()) != null) {

				if (!line.trim().equals(lineToRemove)) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();
			dbFile.delete ();
			temp.renameTo (dbFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Vector<GraphFile> getSavedTestCases () throws IOException{
		File graphDb = new File (GRAPH_DB);
		if (!graphDb.exists ())
			return new Vector<GraphFile> ();
		BufferedReader reader = new BufferedReader(new FileReader (graphDb));
		ArrayList<String> graphs = new ArrayList<String> ();
		while (reader.ready ()){
			graphs.add (reader.readLine ());
		}
		Vector<GraphFile> graphFiles = new Vector<GraphFile> ();
		for (String s : graphs){
			String[] parts = s.split (" ");
			graphFiles.add (new GraphFile (Integer.parseInt (parts[1]), parts[0], new File(parts[2])));
		}
		reader.close ();
		return graphFiles;
	}

	public static void addFileToFileList (GraphFile gf) throws IOException {
		File graphDb = new File (GRAPH_DB);
		if (!graphDb.exists ())
			graphDb.createNewFile ();
		FileWriter writer = new FileWriter(graphDb, true);
		writer.append ("" + gf.name + " " + gf.size + " " + gf.file.getAbsolutePath () + "\n");
		writer.close ();
	}

	public static void writeGraph (GraphFile gf) throws IOException{
		if (gf.clumped)
			writeClumpedGraph (gf);
		else
			writeRandomGraph (gf);
	}
	/**
	 * THIS DOES NOT FUCKING WORK
	 * @param gf
	 * @throws IOException
	 */
	public static void writeClumpedGraph (GraphFile gf) throws IOException{
		Random r = new Random ();
		BufferedWriter out = new BufferedWriter (new FileWriter(gf.file));
		out.write (""+gf.size+"\n");
		double yTerm = 0;
		double y = 0;
		for (int i=0;i<gf.size;i++){
			if (i % 50 == 0){
				yTerm += 2;
				y = yTerm;
			} else{
				y =0;
			}
			double x = gf.size*SCALE_FACTOR;
			String coords = ""+x+" "+y+"\n";
			out.write (coords);
		}
		out.close ();
	}

	public static void writeRandomGraph (GraphFile gf) throws IOException{
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
