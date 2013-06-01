package wind;

import processing.core.PVector;

public class AlgorithmAddAll extends AlgorithmBase {

	public AlgorithmAddAll() {
		super();
	}

	public void calculateResultVector(NeighbourhoodMatrices neighbours) {
		NeighbourhoodMatrix current = (NeighbourhoodMatrix) neighbours.currentGenMatrix();
		PVector v = neighbours.nextGenMatrix().getCell(1, 1).getWind();
		for (int col=0; col<3; col++)
			for (int row=0; row<3; row++)
			{
				v.add(current.getCell(col, row).getWind());
			}
	}

}
