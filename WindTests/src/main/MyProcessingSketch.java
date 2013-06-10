package main;

import processing.core.*;
import wind.AlgorithmApplyTemplate;
import wind.AlgorithmBase;
import wind.AlgorithmTemplateAndObstacles;
import wind.CellBase;
import wind.ColAndRow;
import wind.ObstacleCell;
import wind.TemplateMatrix;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;
import wind.AlgorithmBase.AlgorithmType;

public class MyProcessingSketch extends PApplet {

	private static final long serialVersionUID = 1L;
	private int _resolution = 10;
	private int _xOffset = 50;

	private WindMatrices _matrices;

	public void setup() {
		size(displayWidth, displayHeight);
//		size(310,210);
		frameRate(25);
//		frameRate(0.2f);
//		frameRate(1);
		// background(100);
		stroke(255, 255, 0);
		fill(255, 0, 0);

//		int arrayWidth = 21;
//		int arrayHeight = 21;
		int arrayWidth = width / _resolution;
		int arrayHeight = height / _resolution;

		AlgorithmBase alg = getAlgorithm();

		_matrices = new WindMatrices(arrayWidth, arrayHeight, alg);
		WindMatrix current = _matrices.currentGenMatrix();

		// TODO: load tests and demos in a friendlier way
		// TODO: colliding winds - cancel out? Push sideways? Similar to
		// obstacles
		// TODO: introduce obstacles
		// TODO: Patrick calls in to the wind library to generate the flow
		// field, supplies parameters, supply obstacles
		// TODO: normalize vectors on output

		loadInitialVectors(current);

	}

	public void draw() {
		background(0); // reset

		// add any new wind
		pump();

		// draw matrix
		System.out.println("Energy: " + _matrices.currentGenMatrix().energy());
		drawWindMatrix(_matrices.currentGenMatrix().getCells());

		// process the matrix
		_matrices.process();
	}

	private void pump() {
		WindMatrix m = _matrices.currentGenMatrix();
		int centre = m.getCells()[0].length / 2;
		int loBound = centre - centre / 2;
		int hiBound = centre + centre / 2;
		// System.out.println("centre=" + centre + " lo=" + loBound + " hi=" +
		// hiBound);
		for (int row = loBound; row <= hiBound; row++) {
			m.setWindCell(0, row, 10, 0);
		}
	}

	public void loadInitialVectors(WindMatrix current) {
		block();

		// current.setCell(8, 10, -30, 0);
		// current.setCell(12, 10, 30, 0);
		// current.setCell(10, 8, 0, -30);
		// current.setCell(10, 12, 0, 30);

		// current.setCell(0, 8, 20, 0);
		// current.setCell(0, 9, 20, 0);
		// current.setCell(0, 10, 20, 0);
		// current.setCell(0, 11, 20, 0);
		// current.setCell(0, 12, 20, 0);
		//
		// current.setCell(20, 8, -20, 0);
		// current.setCell(20, 9, -20, 0);
		// current.setCell(20, 10, -20, 0);
		// current.setCell(20, 11, -20, 0);
		// current.setCell(20, 12, -20, 0);

		// current.setCell(10, 10, 10, 10);
		// current.setCell(10, 10, 0, 10);

		// current.setCell(60, 60, -10, -10);
		// current.setCell(100, 60, 10, -10);
		// current.setCell(100, 100, 10, 10);
		// current.setCell(60, 100, -10, 10);

		// current.setCell(20,10,1,1);
		// current.setCell(10,10,20,0);
		// current.setCell(0,0,4,4);
		// current.setCell(0,10,4,0);
		// current.setCell(10,0,0,4);

		// current.setCell(0,0,3,0);
		// current.setCell(0,1,4,0);
		// current.setCell(0,2,5,0);
		// current.setCell(4,0,3,12);
		// current.setCell(5,1,4,12);
		// current.setCell(6,2,5,12);
		// current.setCell(7,0,3,6);
		// current.setCell(8,1,4,6);
		// current.setCell(9,2,5,6);
		// current.setCell(15,15,-3,0);
		// current.setCell(17,17,0,-3);
		// current.setCell(0,18,-5,-5);

		// current.setCell(0,8,3,3);
		// current.setCell(0,10,3,3);
		//
		// current.setCell(0,13,3,-3);
		// current.setCell(0,15,3,-3);
	}

	public void block() {
		WindMatrix current = _matrices.currentGenMatrix();
		WindMatrix next = _matrices.nextGenMatrix();
		int centre = current.getCells()[0].length/2;
		int loBound = centre-centre/2;
		int hiBound = centre+centre/2;

//		verticalBlock(current, next, loBound, hiBound);
//		diagonalBlock(current, next, loBound, hiBound);
		circleBlock(_matrices, loBound, hiBound);
	}

	private void circleBlock(WindMatrices matrices, int loBound, int hiBound) {
		for(int col=-15; col<=15; col++){
			int row = (int) Math.sqrt(225-col*col);
			int colInc = 60;
			int rowInc = 60;
			for (int extraRow=0; extraRow<6; extraRow++){
				matrices.setObstacle(col+colInc, row+rowInc+extraRow);
				matrices.setObstacle(col+colInc, -row+rowInc+extraRow);
			}
		}	
	}

	private void diagonalBlock(WindMatrix current, WindMatrix next,
			int loBound, int hiBound) {
		for(int col=10; col<=12; col++){
			int colIncrement = 0;
			for(int row=loBound; row<=hiBound; row++){
				current.setObstacle(col+colIncrement, row);
				next.setObstacle(col+colIncrement, row);
				colIncrement++;
			}
		}
	}

	private void verticalBlock(WindMatrix current, WindMatrix next, int loBound,
			int hiBound) {
		for(int col=10; col<=15; col++){
			for(int row=loBound; row<=hiBound; row++){
				current.setObstacle(col, row);
				next.setObstacle(col, row);
			}
		}
	}

	public AlgorithmBase getAlgorithm() {
		AlgorithmBase alg = AlgorithmBase
				.SelectAlgorithm(AlgorithmType.applyTemplateAndObstacles);
		TemplateMatrix template = new TemplateMatrix();
		// 0.88 0.1
		// 0.6 0.3

		// template.setCell(0, 1, 0.3f, 0); // tail wind
		// template.setCell(1, 0, 0, -1, 0.1f);
		template.setWindCell(2, 0, 1, -1, 0.3f);
		template.setWindCell(2, 1, 0.6f, 0);
		template.setWindCell(2, 2, 1, 1, 0.3f);
		// template.setCell(1, 2, 0, 1, 0.1f);
		((AlgorithmTemplateAndObstacles) alg).set_template(template); // TODO:
																		// this
																		// cast
																		// depends
																		// on
																		// the
																		// selection
																		// above
		return alg;
	}

	private int _colorIndex = 0;

	public void drawWindMatrix(CellBase[][] cells) {
		// changeColor();
		for (int col = 0; col < cells.length; col++)
			for (int row = 0; row < cells[col].length; row++) {
				drawCell(cells[col][row]);
			}
	}

	private void drawCell(CellBase cell) {
		// TODO: handle obstacle cells
		pushMatrix();
		// translate(cell.getCol()*_resolution + _resolution + _xOffset,
		// cell.getRow()*_resolution + _resolution);
		translate(cell.getCol() * _resolution + _xOffset, cell.getRow()
				* _resolution);
		if (cell instanceof WindCell) {
			stroke(255,255,0);
			line(0, 0, cell.getWind().x, cell.getWind().y);
		} else if (cell instanceof ObstacleCell) {
			stroke(0,0,255);
			rect(0, 0, 10, 10);
		} else {
			// TODO: do something?
		}

		popMatrix();
	}

	private void changeColor() {
		if (_colorIndex > 8)
			_colorIndex = 0;
		switch (_colorIndex++) {
		case 0:
			stroke(255, 0, 0);
			break;
		case 1:
			stroke(255, 126, 0);
			break;
		case 2:
			stroke(255, 255, 0);
			break;
		case 3:
			stroke(0, 255, 0);
			break;
		case 4:
			stroke(0, 255, 126);
			break;
		case 5:
			stroke(0, 255, 255);
			break;
		case 6:
			stroke(0, 0, 255);
		case 7:
			stroke(126, 0, 255);
		case 8:
			stroke(255, 0, 255);
			break;
		default:
			stroke(0, 0, 0);
			break;
		}
	}
}
