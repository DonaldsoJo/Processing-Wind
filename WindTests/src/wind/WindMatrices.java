package wind;

public class WindMatrices extends BaseMatrices
{
	public WindMatrices(int cols, int rows, AlgorithmBase updateAlgorithm)
	{
		super( cols, rows, updateAlgorithm);
	}
	
	public void flipMatrices() {
		_currentMatrixIndex = otherMatrixIndex();
	}

	@Override
	public WindMatrix[] setupMatrices(int cols, int rows) {
		WindMatrix matrices[] = new WindMatrix[2];
		matrices[0] = new WindMatrix(cols, rows);
		matrices[1] = new WindMatrix(cols, rows);
		return matrices;
	}

	public void updateCells() {
		for(WindCell[] cols : currentMatrix().getCells())
			for(WindCell cell: cols)
			{
//				System.out.println();
//				System.out.println("  in updateCells of WindMatrices - before handling cell " + cell.getCol() + "," + cell.getRow());

				NeighbourhoodMatrices neighbours = getNeighbours( cell);
				neighbours.update();
				
//				System.out.println();
//				System.out.println("  in updateCells of WindMatrices - just handled cell " + cell.getCol() + "," + cell.getRow());
//				tests.Utils.printMatrixNonZeroValues(neighbours.currentMatrix(), "  Current");
//				tests.Utils.printMatrixNonZeroValues(neighbours.otherMatrix(), "  Other");
			}
	}

	public NeighbourhoodMatrices getNeighbours( WindCell cell) {
		NeighbourhoodMatrices neighbours = new NeighbourhoodMatrices(3, 3, _algorithm);
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++)
			{
				WindCell c = currentMatrix().getCell(col + cell.getCol() - 1, row + cell.getRow() - 1);
				neighbours.currentMatrix().replaceCell(col,row,c);
				
				c = otherMatrix().getCell(col + cell.getCol() - 1, row + cell.getRow() - 1);
				neighbours.otherMatrix().replaceCell(col,row,c);
			}
		return neighbours;
	}

	public void clearOtherMatrix() {
		for (WindCell[] cols : otherMatrix().getCells())
			for (WindCell cell : cols)
				cell.clearCell();
	}
	
	public void process()
	{
		clearOtherMatrix();
		updateCells();
		flipMatrices();

	}

}