package wind;

import processing.core.PVector;


public class WindMatrices extends BaseMatrices
{
	public WindMatrices(WindCell[][] cells)
	{
		_matrices[0].setCells(cells);
		// TODO: other cells needed as well
	}
	
	public WindMatrices(int cols, int rows)
	{
		_matrices = new WindMatrix[2];
		_matrices[0] = new WindMatrix(cols, rows, this);
		_matrices[1] = new WindMatrix(cols, rows, this);
		
//		_matrices[0].setCells(new WindCell[cols][rows]);
//		_matrices[1].setCells(new WindCell[cols][rows]);
		
		for(int col=0; col<cols; col++)
		{
			for(int row=0; row<rows; row++)
			{
				_matrices[0].getCells()[col][row] = new WindCell( _matrices[0], col, row);
				_matrices[1].getCells()[col][row] = new WindCell( _matrices[1], col, row);
			}
		}
	}
	
	public WindMatrix currentMatrix()
	{
		return _matrices[currentMatrixIndex()];
	}

	public WindMatrix otherMatrix()
	{
		return _matrices[otherMatrixIndex()];
	}

	public void clearNext(int col, int row) {
		_matrices[otherMatrixIndex()].clearOther(col, row);
	}

	public void clearOther(int col, int row) {
		otherMatrix().getCell(col, row).clearCell();
		
	}

	public void updateOther(int col, int row, PVector newWind) {
		otherMatrix().setCell(col, row, newWind.x, newWind.y);
	}
}