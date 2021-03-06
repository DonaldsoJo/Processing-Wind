package wind;

import processing.core.PVector;
import wind.AlgorithmBase.AlgorithmType;

public abstract class BaseMatrices {

	public BaseMatrices(int cols, int rows, AlgorithmBase updateAlgorithm) {
		super();
		_algorithm = updateAlgorithm;
		_matrices = setupMatrices(cols, rows);
		_noofCols = cols;
		_noofRows = rows;
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
	
	int _noofCols;
	public int getNoofCols() {
		return _noofCols;
	}

	int _noofRows;
	public int getNoofRows() {
		return _noofRows;
	}

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

	public int nextGenMatrixIndex() {
		return 1-_currentMatrixIndex;
	}

	public WindMatrix currentGenMatrix()
	{
		return _matrices[currentMatrixIndex()];
	}

	public WindMatrix nextGenMatrix()
	{
		return _matrices[nextGenMatrixIndex()];
	}

	public void clearNextGenCell(int col, int row) {
		
		CellBase cell = nextGenMatrix().getCell(col, row);
		if (cell instanceof WindCell) ((WindCell) cell).clearCell();
	}

	public void updateNextGenCell(int col, int row, PVector newWind) {
		nextGenMatrix().setWindCell(col, row, newWind.x, newWind.y);
	}
}