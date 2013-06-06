package wind;

import processing.core.PVector;

public abstract class CellBase {

	public abstract PVector getWind();
	public abstract void clearCell();

	protected int _col;
	protected int _row;

	public int getCol() {
		return _col;
	}

	public CellBase() {
		super();
	}

	public int getRow() {
		return _row;
	}

}