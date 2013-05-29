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
		PVector v = neighbours.nextGenMatrix().getCell(1, 1).getWind();
		v.add( resolveTopLeft(current.topLeft().getWind()));
		v.add( resolveTopMiddle(current.topMiddle().getWind()));
		v.add( resolveTopRight(current.topRight().getWind()));
		v.add( resolveLeft(current.left().getWind()));
		v.add( resolveRight(current.right().getWind()));
		v.add( resolveBottomLeft(current.bottomLeft().getWind()));
		v.add( resolveBottomMiddle(current.bottomMiddle().getWind()));
		v.add( resolveBottomRight(current.bottomRight().getWind()));
	}


}
