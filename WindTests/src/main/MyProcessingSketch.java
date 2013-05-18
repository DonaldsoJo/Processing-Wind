package main;
import processing.core.*;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;

public class MyProcessingSketch extends PApplet
{
	
	private static final long serialVersionUID = 1L;
	private int _resolution = 10;
	
	private WindMatrices _matrices;

	public void setup() {
	    size(400,200);
	    background(200);
	    
	    _matrices = new WindMatrices(20,20);
	    WindMatrix current = _matrices.currentMatrix();
	    current.setCell(0,0,3,0);
	    current.setCell(0,1,4,0);
	    current.setCell(0,2,5,0);
	    
	    current.setCell(4,0,3,12);
	    current.setCell(5,1,4,12);
	    current.setCell(6,2,5,12);
	    
	    current.setCell(7,0,3,6);
	    current.setCell(8,1,4,6);
	    current.setCell(9,2,5,6);
	}
	
	public void draw() {
		// draw matrix
		WindCell[][] cells = _matrices.currentMatrix().getCells();
		drawWindMatrix(cells);
		
		// update matrix and flip
		_matrices.updateCells();
		_matrices.flipMatrices();
	}

	public void drawWindMatrix(WindCell[][] cells) {
		for(int col=0; col<cells.length; col++ )
			for(int row=0; row<cells[col].length; row++)
			{
				drawCell( cells[col][row]);
			}
	}

	private void drawCell(WindCell cell) {
		// save where we are
		pushMatrix();
		
		// move to the cell coordinate
		translate(cell.getCol()*_resolution + 50, cell.getRow()*_resolution);

		// draw the vector
		line(0,0,cell.get_wind().x,cell.get_wind().y);
		
		// go back
		popMatrix();
	}
}
