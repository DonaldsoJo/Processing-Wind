package wind;

import processing.core.PVector;

public class AlgorithmAddAll extends AlgorithmBase {

	public AlgorithmAddAll() {
		super();
//		System.out.println("AlgorithmAddAll");
	}

	public void calculateResultVector(NeighbourhoodMatrices neighbours) {
		NeighbourhoodMatrix current = (NeighbourhoodMatrix) neighbours.currentGenMatrix();
		PVector v = neighbours.nextGenMatrix().getCell(1, 1).getWind();
		v.add(current.topLeft().getWind());
		v.add(current.topMiddle().getWind());
		v.add(current.topRight().getWind());
		v.add(current.left().getWind());
		v.add(current.right().getWind());
		v.add(current.bottomLeft().getWind());
		v.add(current.bottomMiddle().getWind());
		v.add(current.bottomRight().getWind());
	}

}
