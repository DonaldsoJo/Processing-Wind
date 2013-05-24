package wind;

import processing.core.PVector;

public class AlgorithmFractionalFlowAndSideSpills extends AlgorithmBase {

	@Override
	public void calculateResultVector(NeighbourhoodMatrices neighbours) {
//		AlgorithmBase onlyPositive = new AlgorithmOnlyPositive();
//		onlyPositive.calculateResultVector(v, neighbours);
		
		NeighbourhoodMatrix current = (NeighbourhoodMatrix) neighbours.currentMatrix();
		NeighbourhoodMatrix other = (NeighbourhoodMatrix) neighbours.otherMatrix();
		PVector v = other.getCell(1, 1).get_wind();
		
//		handleAny(ENeighbour.TOPLEFT, current, other);
		topLeftHandling(current, other);
		bottomLeftHandling(current, other);
		
		
		v.add( resolveTopMiddle(current.topMiddle().get_wind()));
		v.add( resolveTopRight(current.topRight().get_wind()));
		v.add( resolveLeft(current.left().get_wind()));
		v.add( resolveRight(current.right().get_wind()));
		v.add( resolveBottomMiddle(current.bottomMiddle().get_wind()));
		v.add( resolveBottomRight(current.bottomRight().get_wind()));

	}
	
	public void handleAny(ENeighbour neighbourCell, NeighbourhoodMatrix current,
			NeighbourhoodMatrix other) {
		PVector v = other.getCell(1, 1).get_wind();
		
		PVector vSource = resolveAny(neighbourCell, current.getCell(Utils.col(neighbourCell), Utils.row(neighbourCell)).get_wind());
		
		PVector vSpill = resolveAny(getNeighbour( neighbourCell, EDirection.clockwise), vSource);
		vSpill.mult(0.1f);
		other.left().get_wind().add(vSpill);
		
		vSpill = resolveAny(getNeighbour( neighbourCell, EDirection.anticlockwise), vSource);
		vSpill.mult(0.1f);
		other.topMiddle().get_wind().add(vSpill);
		
		vSource.mult(0.8f);
		v.add( vSource);
		// just a temp comment
	}

	public void topLeftHandling(NeighbourhoodMatrix current,
			NeighbourhoodMatrix other) {
		PVector v = other.getCell(1, 1).get_wind();
		
		PVector vTopLeft = resolveTopLeft(current.topLeft().get_wind());

		PVector vLeftSpill = resolveTopMiddle(vTopLeft);
		vLeftSpill.mult(0.1f);
		other.left().get_wind().add(vLeftSpill);
		
		PVector vTopMiddleSpill = resolveLeft(vTopLeft);
		vTopMiddleSpill.mult(0.1f);
		other.topMiddle().get_wind().add(vTopMiddleSpill);

		vTopLeft.mult(0.8f);
		v.add( vTopLeft);
	}

	public enum EDirection{clockwise, anticlockwise};
	
	public static ENeighbour getNeighbour(ENeighbour neighbourCell, EDirection direction) {
		switch( neighbourCell) {
		case TOPLEFT: if (direction == EDirection.clockwise) {return ENeighbour.TOP;} else return ENeighbour.LEFT;
		case TOP: if (direction == EDirection.clockwise) {return ENeighbour.TOPRIGHT;} else return ENeighbour.TOPLEFT;
		case TOPRIGHT: if (direction == EDirection.clockwise) {return ENeighbour.RIGHT;} else return ENeighbour.TOP;
		case RIGHT: if (direction == EDirection.clockwise) {return ENeighbour.BOTRIGHT;} else return ENeighbour.TOPRIGHT;
		case BOTRIGHT: if (direction == EDirection.clockwise) {return ENeighbour.BOTTOM;} else return ENeighbour.RIGHT;
		case BOTTOM: if (direction == EDirection.clockwise) {return ENeighbour.BOTLEFT;} else return ENeighbour.BOTRIGHT;
		case BOTLEFT: if (direction == EDirection.clockwise) {return ENeighbour.LEFT;} else return ENeighbour.BOTTOM;
		case LEFT: if (direction == EDirection.clockwise) {return ENeighbour.TOPLEFT;} else return ENeighbour.BOTLEFT;
		}
		return null;
	}

	public void bottomLeftHandling(NeighbourhoodMatrix current,
			NeighbourhoodMatrix other) {
		PVector v = other.getCell(1, 1).get_wind();
		
		PVector vBottomLeft = resolveBottomLeft(current.bottomLeft().get_wind());

		PVector vLeftSpill = resolveBottomMiddle(vBottomLeft);
		vLeftSpill.mult(0.1f);
		other.left().get_wind().add(vLeftSpill);
		
		PVector vBottomMiddleSpill = resolveLeft(vBottomLeft);
		vBottomMiddleSpill.mult(0.1f);
		other.bottomMiddle().get_wind().add(vBottomMiddleSpill);

		vBottomLeft.mult(0.8f);
		v.add( vBottomLeft);
	}


}
