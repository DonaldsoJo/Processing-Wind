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
		
		handleAny(ENeighbour.TOPLEFT, current, other);
		handleAny(ENeighbour.TOPRIGHT, current, other);
		handleAny(ENeighbour.BOTRIGHT, current, other);
		handleAny(ENeighbour.BOTLEFT, current, other);
		
		
		v.add( resolveTopMiddle(current.topMiddle().get_wind()));
		v.add( resolveLeft(current.left().get_wind()));
		v.add( resolveRight(current.right().get_wind()));
		v.add( resolveBottomMiddle(current.bottomMiddle().get_wind()));

	}
	
	public void handleAny(ENeighbour neighbourName, NeighbourhoodMatrix current,
			NeighbourhoodMatrix other) {
		
		WindCell cSource = current.getCell(Utils.col(neighbourName), Utils.row(neighbourName)); 
		System.out.println("col " + cSource.getCol() + " row " + cSource.getRow());
		PVector vSource = cSource.get_wind();
		
		updateSpill(neighbourName, other, vSource, EDirection.clockwise);
		updateSpill(neighbourName, other, vSource, EDirection.anticlockwise);
		
//		ENeighbour nAntiClockwise = getNeighbour( neighbourName, EDirection.anticlockwise);
//		PVector vSpill2 = resolveAny(nAntiClockwise, vSource);
//		vSpill2.mult(0.1f);
//		WindCell updateCell2 = getTargetCell( other, nAntiClockwise);
//		updateCell2.get_wind().add(vSpill2);
		
//		ENeighbour nAntiClockwise = getNeighbour( neighbourName, EDirection.anticlockwise);
//		vSpill = resolveAny(nAntiClockwise, vSource);
//		vSpill.mult(0.1f);
//		other.left().get_wind().add(vSpill);
		
		PVector vMain = resolveAny(neighbourName, cSource.get_wind());
		vMain.mult(0.8f);
		PVector vTarget = other.getCell(1, 1).get_wind();
		vTarget.add( vMain);
	}

	public void updateSpill(ENeighbour neighbourName, NeighbourhoodMatrix other, PVector vSource, EDirection direction) {
		ENeighbour sourceNeighbour = getNeighbour( neighbourName, oppositeDirection(direction)); 
		PVector vSpill = resolveAny(sourceNeighbour, vSource);
		vSpill.mult(0.1f);

		ENeighbour updateNeighbour = getNeighbour( neighbourName, direction); 
		WindCell updateCell = getTargetCell( other, updateNeighbour);
		System.out.println("source " + neighbourName + " direction " + direction + " neighbour " + updateNeighbour + " target col " + updateCell.getCol() + " row " + updateCell.getRow() );
		System.out.println("x " + vSpill.x + " y " + vSpill.y);
		updateCell.get_wind().add(vSpill);
	}

	private EDirection oppositeDirection(EDirection direction) {
		if (direction == EDirection.clockwise) return EDirection.anticlockwise; 
		return EDirection.clockwise;
	}

	private WindCell getTargetCell(NeighbourhoodMatrix other, ENeighbour neighbour) {
		switch( neighbour) {
		case TOPLEFT: return other.topLeft();
		case TOP: return other.topMiddle();
		case TOPRIGHT: return other.topRight();
		case RIGHT: return other.right();
		case BOTRIGHT: return other.bottomRight();
		case BOTTOM: return other.bottomMiddle();
		case BOTLEFT: return other.bottomLeft();
		case LEFT: return other.left();
		}
		return null;
	}

//	public void topLeftHandling(NeighbourhoodMatrix current,
//			NeighbourhoodMatrix other) {
//		PVector v = other.getCell(1, 1).get_wind();
//		
//		PVector vTopLeft = resolveTopLeft(current.topLeft().get_wind());
//
//		PVector vLeftSpill = resolveTopMiddle(vTopLeft);
//		vLeftSpill.mult(0.1f);
//		other.left().get_wind().add(vLeftSpill);
//		
//		PVector vTopMiddleSpill = resolveLeft(vTopLeft);
//		vTopMiddleSpill.mult(0.1f);
//		other.topMiddle().get_wind().add(vTopMiddleSpill);
//
//		vTopLeft.mult(0.8f);
//		v.add( vTopLeft);
//	}

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

//	public void bottomLeftHandling(NeighbourhoodMatrix current,
//			NeighbourhoodMatrix other) {
//		PVector v = other.getCell(1, 1).get_wind();
//		
//		PVector vBottomLeft = resolveBottomLeft(current.bottomLeft().get_wind());
//
//		PVector vLeftSpill = resolveBottomMiddle(vBottomLeft);
//		vLeftSpill.mult(0.1f);
//		other.left().get_wind().add(vLeftSpill);
//		
//		PVector vBottomMiddleSpill = resolveLeft(vBottomLeft);
//		vBottomMiddleSpill.mult(0.1f);
//		other.bottomMiddle().get_wind().add(vBottomMiddleSpill);
//
//		vBottomLeft.mult(0.8f);
//		v.add( vBottomLeft);
//	}


}
