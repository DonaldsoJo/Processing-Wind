package main;
import processing.core.*;
import wind.AlgorithmBase;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;
import wind.AlgorithmBase.AlgorithmType;

public class MyProcessingSketch extends PApplet
{
	
	private static final long serialVersionUID = 1L;
	private int _resolution = 10;
	
	private WindMatrices _matrices;

	public void setup() {
	    size(displayWidth,displayHeight);
	    frameRate(1);
	    background(100);
	    
	    int arrayWidth = 21; 
	    int arrayHeight = 21; 
//	    int arrayWidth = width/_resolution; 
//	    int arrayHeight = height/_resolution; 
	    _matrices = new WindMatrices(arrayWidth,arrayHeight, AlgorithmBase.SelectAlgorithm(AlgorithmType.addAll));
	    WindMatrix current = _matrices.currentMatrix();
//	    current.setCell(20,10,1,1);
//	    current.setCell(10,10,20,0);
//	    current.setCell(0,0,4,4);

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
//	    background(100); // reset

		// draw matrix
		WindCell[][] cells = _matrices.currentMatrix().getCells();
		drawWindMatrix(cells);
		
		// update matrix and flip
		_matrices.clearOtherMatrix();
		_matrices.updateCells();
		_matrices.flipMatrices();
	}

	private int _colorIndex = 0;
	public void drawWindMatrix(WindCell[][] cells) {
		changeColor();
		for(int col=0; col<cells.length; col++ )
			for(int row=0; row<cells[col].length; row++)
			{
				drawCell( cells[col][row]);
			}
	}

	private void drawCell(WindCell cell) {
		pushMatrix();		
		translate(cell.getCol()*_resolution + _resolution, cell.getRow()*_resolution + _resolution);
		line(0,0,cell.get_wind().x,cell.get_wind().y);
		popMatrix();
	}
	
	private void changeColor()
	{
		if (_colorIndex > 8) _colorIndex = 0;
		switch(_colorIndex++)
		{
		case 0:
			stroke(255,0,0);
			break;
		case 1:
			stroke(255,126,0);
			break;
		case 2:
			stroke(255,255,0);
			break;
		case 3:
			stroke(0,255,0);
			break;
		case 4:
			stroke(0,255,126);
			break;
		case 5:
			stroke(0,255,255);
			break;
		case 6:
			stroke(0,0,255);
		case 7:
			stroke(126,0,255);
		case 8:
			stroke(255,0,255);
			break;
		default:
			stroke(0,0,0);
			break;			
		}
	}
}
