package wind;

import processing.core.PVector;
import wind.AlgorithmBase.AlgorithmType;

public abstract class BaseMatrices {

	public BaseMatrices(int cols, int rows, AlgorithmBase updateAlgorithm) {
		super();
		_algorithm = updateAlgorithm;
		_matrices = setupMatrices(cols, rows);
		initMatrices(cols, rows);
	}

	protected abstract WindMatrix[] setupMatrices(int cols, int rows);

	public void initMatrices(int cols, int rows) {
		for(int col=0; col<cols; col++)
		{
			for(int row=0; row<rows; row++)
			{
				_matrices[0].getCells()[col][row] = new WindCell( col, row);
				_matrices[1].getCells()[col][row] = new WindCell( col, row);
			}
		}
	}
	
	int _currentMatrixIndex = 0;

	protected WindMatrix[] _matrices;

	protected AlgorithmBase _algorithm = AlgorithmBase.SelectAlgorithm(AlgorithmType.doNothing);
	
	public void setAlgorithm(AlgorithmBase algorithm) {
		_algorithm = algorithm;
	}

	public AlgorithmBase getAlgorithm() {
		return _algorithm;
	}

	public int currentMatrixIndex() {
		return _currentMatrixIndex;
	}

	public int otherMatrixIndex() {
		return 1-_currentMatrixIndex;
	}

	public WindMatrix currentGenMatrix()
	{
		return _matrices[currentMatrixIndex()];
	}

	public WindMatrix nextGenMatrix()
	{
		return _matrices[otherMatrixIndex()];
	}

	public void clearOtherCell(int col, int row) {
		nextGenMatrix().getCell(col, row).clearCell();
	}

	public void updateOtherCell(int col, int row, PVector newWind) {
		nextGenMatrix().setCell(col, row, newWind.x, newWind.y);
	}
}