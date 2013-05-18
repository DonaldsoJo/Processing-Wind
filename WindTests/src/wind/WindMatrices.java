package wind;


public class WindMatrices extends BaseMatrices
{
	public WindMatrices(int cols, int rows)
	{
		super( cols, rows);
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
				NeighbourhoodMatrices neighbours = getNeighbours( cell);
				neighbours.update();
			}
	}

	public NeighbourhoodMatrices getNeighbours( WindCell cell) {
		NeighbourhoodMatrices neighbours = new NeighbourhoodMatrices(3, 3);
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

}