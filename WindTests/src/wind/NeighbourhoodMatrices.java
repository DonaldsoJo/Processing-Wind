package wind;

public class NeighbourhoodMatrices extends BaseMatrices {

	public NeighbourhoodMatrices(int cols, int rows, AlgorithmBase updateAlgorithm) {
		super(cols, rows, updateAlgorithm);
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

}
