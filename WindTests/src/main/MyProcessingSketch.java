package main;
import processing.core.*;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;

public class MyProcessingSketch extends PApplet
{
	
	private static final long serialVersionUID = 1L;
	
	private WindMatrices _matrices;

	public void setup() {
	    size(200,200);
	    background(0);
	    
	    _matrices = new WindMatrices(3,3);
	    WindMatrix current = _matrices.currentMatrix();
	    current.setCell(0,0,3,0);
	    current.setCell(0,1,4,0);
	    current.setCell(0,2,5,0);
	}
	
	public void draw() {
		// draw matrix
		WindCell[][] cells = _matrices.currentMatrix().getCells();
		
		for(int col=0; col<cells.length; col++ )
			for(int row=0; row<cells[col].length; row++)
			{
				drawCell( cells[col][row]);
			}			
		
		// update matrix
		_matrices.currentMatrix().updateCells();
	}

	private void drawCell(WindCell cell) {
		println("draw cell");
	}
}
