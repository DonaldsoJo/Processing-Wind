package wind;

public class WindMatrices extends BaseMatrices
{
	public WindMatrices(int cols, int rows, AlgorithmBase updateAlgorithm)
	{
		super( cols, rows, updateAlgorithm);
	}
	
	public void flipMatrices() {
		_currentMatrixIndex = nextGenMatrixIndex();
	}

	@Override
	public WindMatrix[] setupMatrices(int cols, int rows) {
		WindMatrix matrices[] = new WindMatrix[2];
		matrices[0] = new WindMatrix(cols, rows);
		matrices[1] = new WindMatrix(cols, rows);
		return matrices;
	}

	public void updateCells() {
		for(WindCell[] cols : currentGenMatrix().getCells())
			for(WindCell cell: cols)
			{
				NeighbourhoodMatrices neighbours = getNeighbours( cell);
				neighbours.update();
			}
	}

	public NeighbourhoodMatrices getNeighbours( WindCell cell) {
		NeighbourhoodMatrices neighbours = new NeighbourhoodMatrices(3, 3, _algorithm);
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++)
			{
				WindCell c = currentGenMatrix().getCell(col + cell.getCol() - 1, row + cell.getRow() - 1);
				neighbours.currentGenMatrix().replaceCell(col,row,c);
				
				c = nextGenMatrix().getCell(col + cell.getCol() - 1, row + cell.getRow() - 1);
				neighbours.nextGenMatrix().replaceCell(col,row,c);
			}
		return neighbours;
	}

	public void clearNextGenMatrix() {
		for (WindCell[] cols : nextGenMatrix().getCells())
			for (WindCell cell : cols)
				cell.clearCell();
	}
	
	public void process()
	{
		clearNextGenMatrix();
		updateCells();
		flipMatrices();
	}

}