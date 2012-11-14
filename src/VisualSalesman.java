import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class VisualSalesman extends JFrame{
	private static final long serialVersionUID = 1L;
	JPanel canvasPanel;
	JComboBox algoChooser;
	JButton runSolverButton;
	JPanel eastPanel;
	Canvas canvas;
	Graphics g;
	
	public VisualSalesman (){
		try {
			buildGui (TestCaseFactory.getSavedTestCases (), TSP.algorithms);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pack ();
		this.setVisible (true);
		
	}
	
	private void buildGui (ArrayList<GraphFile> graphs, Algorithm[] algorithms){
		this.setLayout (new BorderLayout ());
		canvasPanel = new JPanel ();
		canvas = new Canvas ();
		g = canvas.getGraphics ();
		canvasPanel.add (canvas);
		this.add (canvasPanel, BorderLayout.NORTH);
		
		JPanel southPanel = new JPanel ();
		algoChooser = new JComboBox<Algorithm> (algorithms);
		this.add (southPanel, BorderLayout.SOUTH);
		
	}

}
