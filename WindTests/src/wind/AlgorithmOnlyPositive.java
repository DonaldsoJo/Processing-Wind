package wind;

import processing.core.PVector;

public class AlgorithmOnlyPositive extends AlgorithmBase {

	public AlgorithmOnlyPositive() {
		super();
//		System.out.println("AlgorithmOnlyPositive");
	}

	@Override
	public void calculateResultVector(NeighbourhoodMatrices neighbours) {
		NeighbourhoodMatrix current = (NeighbourhoodMatrix) neighbours.currentGenMatrix();
		PVector v = neighbours.nextGenMatrix().getCell(1, 1).get_wind();
		v.add( resolveTopLeft(current.topLeft().get_wind()));
		v.add( resolveTopMiddle(current.topMiddle().get_wind()));
		v.add( resolveTopRight(current.topRight().get_wind()));
		v.add( resolveLeft(current.left().get_wind()));
		v.add( resolveRight(current.right().get_wind()));
		v.add( resolveBottomLeft(current.bottomLeft().get_wind()));
		v.add( resolveBottomMiddle(current.bottomMiddle().get_wind()));
		v.add( resolveBottomRight(current.bottomRight().get_wind()));
	}


}
