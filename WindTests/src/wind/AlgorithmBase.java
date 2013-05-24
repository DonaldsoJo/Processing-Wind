package wind;

import processing.core.PVector;

public abstract class AlgorithmBase {

	public AlgorithmBase() {
		super();
	}

	public abstract void calculateResultVector(NeighbourhoodMatrices neighbours);

	public enum AlgorithmType{addAll, onlyPositive, doNothing, fractionalFlowAndSideSpills};
	
	public static AlgorithmBase SelectAlgorithm(AlgorithmType atype) {
		switch(atype)
		{
		case addAll:
			return new AlgorithmAddAll();
		case onlyPositive:
			return new AlgorithmOnlyPositive();
		case fractionalFlowAndSideSpills:
			return new AlgorithmFractionalFlowAndSideSpills();
		case doNothing:
			return new AlgorithmDoNothing();
		default:
			return new AlgorithmWarning();
		}
	}
	
	public static PVector resolveAny(ENeighbour neighbour, PVector v) {
		switch( neighbour) {
		case TOPLEFT: return resolveTopLeft(v);
		case TOP: return resolveTopMiddle(v);
		case TOPRIGHT: return resolveTopRight(v);
		case RIGHT: return resolveRight(v);
		case BOTRIGHT: return resolveBottomRight(v);
		case BOTTOM: return resolveBottomMiddle(v);
		case BOTLEFT: return resolveBottomLeft(v);
		case LEFT: return resolveLeft(v);
		}
		return null;
	}

	public static PVector resolveTopLeft(PVector v) {
		PVector result = new PVector();
		if ((v.heading() > (float)(-Math.PI/4)) && (v.heading() < (float)(3*Math.PI/4)))
		{
			result.x = (float) (v.mag() * Math.cos(Math.PI/4));
			result.y = (float) (v.mag() * Math.sin(Math.PI/4));
		}
		return result;
	}

	public static PVector resolveTopMiddle(PVector v) {
		PVector result = new PVector();
		if (v.y > 0) result.y = v.y;
		return result;
	}
	
	public static PVector resolveTopRight(PVector v) {
		PVector result = new PVector();
		if (((v.heading() > (float)(Math.PI/4)) && (v.heading() <= (float)Math.PI)) || 
			((v.heading() > (float)-Math.PI) && (v.heading() < (float)(-3*Math.PI/4)))) // NB brackets round second PI expression before float cast
		{
			result.x = -(float) (v.mag() * Math.cos(Math.PI/4));
			result.y = (float) (v.mag() * Math.sin(Math.PI/4));
		}
		return result;
	}

	public static PVector resolveLeft(PVector v) {
		PVector result = new PVector();
		if (v.x > 0) result.x = v.x;
		return result;
	}

	public static PVector resolveRight(PVector v) {
		PVector result = new PVector();
		if (v.x < 0) result.x = v.x;
		return result;
	}

	public static PVector resolveBottomLeft(PVector v) {
		PVector result = new PVector();
		if ((v.heading() > (float)(-3*Math.PI/4)) && (v.heading() < (float)(Math.PI/4)))
		{
			result.x = (float) (v.mag() * Math.cos(Math.PI/4));
			result.y = -(float) (v.mag() * Math.sin(Math.PI/4));
		}
		return result;
	}

	public static PVector resolveBottomMiddle(PVector v) {
		PVector result = new PVector();
		if (v.y < 0) result.y = v.y;
		return result;
	}

	public static PVector resolveBottomRight(PVector v) {
		PVector result = new PVector();
		if (((v.heading() > (float)(3*Math.PI/4)) && (v.heading() <= (float)Math.PI)) || 
			((v.heading() > (float)-Math.PI) && (v.heading() < (float)(-Math.PI/4))))
		{
			result.x = -(float) (v.mag() * Math.cos(Math.PI/4));
			result.y = -(float) (v.mag() * Math.sin(Math.PI/4));
		}
		return result;
	}

}