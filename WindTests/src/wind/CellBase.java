package wind;

import processing.core.PVector;

public abstract class CellBase {

	public CellBase(int col, int row) {
		super();
		_col = col;
		_row = row;
	}

	public abstract PVector getWind();
	public abstract void clearCell();

	protected int _col;
	protected int _row;

	public int getCol() {
		return _col;
	}

	public int getRow() {
		return _row;
	}

}