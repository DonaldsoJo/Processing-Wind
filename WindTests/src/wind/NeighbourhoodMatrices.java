package wind;

public class NeighbourhoodMatrices extends BaseMatrices {

	public NeighbourhoodMatrices(AlgorithmBase updateAlgorithm) {
		super(3, 3, updateAlgorithm);
	}
	
	public void update() {
		_algorithm.calculateResultVector(this);
	}

	@Override
	public NeighbourhoodMatrix[] setupMatrices(int cols, int rows) {
		NeighbourhoodMatrix matrices[] = new NeighbourhoodMatrix[2];
		matrices[0] = new NeighbourhoodMatrix();
		matrices[1] = new NeighbourhoodMatrix();
		return matrices;
	}

	public void setObstacle(int col, int row) {
		currentGenMatrix().setObstacle(col,row);
		nextGenMatrix().setObstacle(col,row);
	}

}
