package tests;

import wind.WindMatrix;

public class MatrixMaker
{
	public MatrixMaker()
	{
		_matrix = getWindCells();
	}
	
	private WindMatrix _matrix;
	
	private WindMatrix getWindCells() {
		WindMatrix matrix = new WindMatrix(3,3);
		return matrix;
	}
	
	public MatrixMaker setCell(int col, int row, float xval, float yval)
	{
		_matrix.getCell(col, row).get_wind().x = xval;
		_matrix.getCell(col, row).get_wind().y = yval;
		return this;
	}
	
	public WindMatrix matrix()
	{
		return _matrix;
	}

	public MatrixMaker centre(int xval, int yval) {
		setCell(1, 1, xval, yval);
		return this;
	}
}