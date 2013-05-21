package tests;

import wind.AlgorithmBase;
import wind.NeighbourhoodMatrices;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;
import wind.AlgorithmBase.AlgorithmType;

public class MatrixMaker
{

	public MatrixMaker()
	{
		createMatrices(3, 3);
	}
	
	public MatrixMaker(int cols, int rows) {
		createMatrices(cols, rows);
	}

	public void createMatrices(int cols, int rows) {
		_cols = cols;
		_rows = rows;
		_matrices = new WindMatrices(cols,rows, AlgorithmBase.SelectAlgorithm(AlgorithmType.addAll));
	}

	private WindMatrices _matrices;
	private int _cols;
	private int _rows;
	
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

	public MatrixMaker centre(int xval, int yval) throws Exception {
		if ((_cols != 3)||(_rows != 3)) throw new Exception("Centre can only be used from a 3*3 matrix");
		setCell(1, 1, xval, yval);
		return this;
	}

	public MatrixMaker setOtherCell(int col, int row, float xval, float yval) {
		WindMatrix other = _matrices.otherMatrix();
		other.getCell(col, row).get_wind().x = xval;
		other.getCell(col, row).get_wind().y = yval;
		return this;
	}

	public MatrixMaker left( float xval, float yval) throws Exception {
		if ((_cols != 3)||(_rows != 3)) throw new Exception("Centre can only be used from a 3*3 matrix");
		setCell(0, 1, xval, yval);
		return this;
	}

	public NeighbourhoodMatrices neighbours(int i, int j) {
		return _matrices.getNeighbours(new WindCell(1,1));
	}
}