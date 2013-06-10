package wind;

import processing.core.PVector;
import wind.AlgorithmBase.AlgorithmType;

public class WindMatrices extends BaseMatrices implements IFlowField
{
	public WindMatrices(int cols, int rows)
	{
		super( cols, rows, createAlgorithm());
	}
	
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
		for(CellBase[] cols : currentGenMatrix().getCells())
			for(CellBase cell: cols)
			{
				NeighbourhoodMatrices neighbours = getNeighbours( cell);
				neighbours.update();
			}
	}

	public NeighbourhoodMatrices getNeighbours( CellBase cell) {
		NeighbourhoodMatrices neighbours = new NeighbourhoodMatrices( _algorithm);
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++)
			{
				CellBase c = currentGenMatrix().getCell(col + cell.getCol() - 1, row + cell.getRow() - 1);
				neighbours.currentGenMatrix().replaceCell(col,row,c);
				
				c = nextGenMatrix().getCell(col + cell.getCol() - 1, row + cell.getRow() - 1);
				neighbours.nextGenMatrix().replaceCell(col,row,c);
			}
		return neighbours;
	}

	public void clearNextGenMatrix() {
		for (CellBase[] cols : nextGenMatrix().getCells())
			for (CellBase cell : cols)
				cell.clearCell();
	}
	
	public void process()
	{
		clearNextGenMatrix();
		updateCells();
		flipMatrices();
	}

	public PVector[][] flowField() {
		// TODO add test
		PVector[][] flowVectors = new PVector[getNoofCols()][getNoofRows()];
		for(int col=0; col<getNoofCols(); col++)
			for(int row=0; row<getNoofRows(); row++) {
				PVector v = currentGenMatrix().getCells()[col][row].getWind();
				v.normalize();
				flowVectors[col][row] = v;
			}
		return flowVectors;
	}

	/* (non-Javadoc)
	 * @see wind.IFlowField#getFlowField(int, int)
	 */
	@Override
	public PVector[][] getFlowField( int iterations) {
		pump(currentGenMatrix());
		for (int i=0; i<iterations; i++){
			process();
			pump(currentGenMatrix());
		}
		return flowField();
	}

	private static void pump( WindMatrix m) {
		int centre = m.getCells()[0].length/2;
		int loBound = centre-centre/2;
		int hiBound = centre+centre/2;
		for(int row=loBound; row<=hiBound; row++){
			m.setWindCell(0, row, 10, 0);
		}
	}

	private static AlgorithmBase createAlgorithm() {
		AlgorithmBase alg = AlgorithmBase.SelectAlgorithm(AlgorithmType.applyTemplate);
		TemplateMatrix template = new TemplateMatrix();
		template.setWindCell(2, 0, 1, -1, 0.1f);
		template.setWindCell(2, 1, 0.88f, 0);
		template.setWindCell(2, 2, 1, 1, 0.1f);
	    ((AlgorithmApplyTemplate) alg).set_template(template);
		return alg;
	}

	@Override
	public void setTemplateCell(int col, int row, float x, float y, float mag) {
		// TODO Auto-generated method stub
		
	}

	public void setObstacle(int col, int row) {
		currentGenMatrix().setObstacle(col, row);
		nextGenMatrix().setObstacle(col, row);
	}
}