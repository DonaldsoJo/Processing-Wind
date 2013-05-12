package main;
import processing.core.*;
import wind.WindCell;
import wind.WindMatrix;

public class MyProcessingSketch extends PApplet
{
	
	private static final long serialVersionUID = 1L;
	
	WindMatrix _matrix;

	public void setup() {
		// TODO Auto-generated method stub
		
	    size(200,200);
	    background(0);
	    
	    _matrix = new WindMatrix(3,3);
	    _matrix.setCell(0,0,3,0);
	    _matrix.setCell(0,1,4,0);
	    _matrix.setCell(0,2,5,0);
	}
	
	public void draw() {
		// draw matrix
		WindCell[][] cells = _matrix.cells();
		
		for(int col=0; col<cells.length; col++ )
			for(int row=0; row<cells[col].length; row++)
			{
				drawCell( cells[col][row]);
			}			
		
		// update matrix
		_matrix.updateCells();
	}

	private void drawCell(WindCell cell) {
		println("draw cell");
// John modified this from the family machine as JohnMD
	}
}
