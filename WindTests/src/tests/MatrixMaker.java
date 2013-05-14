package tests;

import wind.WindMatrices;
import wind.WindMatrix;

public class MatrixMaker
{
	public MatrixMaker()
	{
		_matrices = getWindCells();
	}
	
	private WindMatrices _matrices;
	
	private WindMatrices getWindCells() {
		WindMatrices matrix = new WindMatrices(3,3);
		return matrix;
	}
	
	public MatrixMaker setCell(int col, int row, float xval, float yval)
	{
		WindMatrix current = _matrices.currentMatrix();
		current.getCell(col, row).get_wind().x = xval;
		current.getCell(col, row).get_wind().y = yval;
		return this;
	}
	
	public WindMatrices matrices()
	{
		return _matrices;
	}

	public MatrixMaker centre(int xval, int yval) {
		setCell(1, 1, xval, yval);
		return this;
	}

	public MatrixMaker setOtherCell(int col, int row, float xval, float yval) {
		WindMatrix other = _matrices.otherMatrix();
		other.getCell(col, row).get_wind().x = xval;
		other.getCell(col, row).get_wind().y = yval;
		return this;
	}

	public MatrixMaker left( float xval, float yval) {
		setCell(0, 1, xval, yval);
		return this;
	}
}