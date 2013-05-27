package wind;

import processing.core.PVector;

public class AlgorithmAddAll extends AlgorithmBase {

	public AlgorithmAddAll() {
		super();
//		System.out.println("AlgorithmAddAll");
	}

	public void calculateResultVector(NeighbourhoodMatrices neighbours) {
		NeighbourhoodMatrix current = (NeighbourhoodMatrix) neighbours.currentGenMatrix();
		PVector v = neighbours.nextGenMatrix().getCell(1, 1).get_wind();
		v.add(current.topLeft().get_wind());
		v.add(current.topMiddle().get_wind());
		v.add(current.topRight().get_wind());
		v.add(current.left().get_wind());
		v.add(current.right().get_wind());
		v.add(current.bottomLeft().get_wind());
		v.add(current.bottomMiddle().get_wind());
		v.add(current.bottomRight().get_wind());
	}

}
