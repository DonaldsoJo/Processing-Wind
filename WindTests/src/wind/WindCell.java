package wind;

import processing.core.PVector;

public class WindCell {
	
	public WindCell(int col, int row) {
		_col = col;
		_row = row;
	}

	private PVector _wind = new PVector();
	public PVector get_wind() {
		return _wind;
	}

	private int _col;
	public int getCol() {
		return _col;
	}

	private int _row;
	public int getRow() {
		return _row;
	}

	public void clearCell() {
		_wind.x = 0;
		_wind.y = 0;
	}

}