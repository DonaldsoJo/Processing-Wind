package wind;

import processing.core.PVector;

public class WindCell {
	
	public WindCell( WindMatrix parent, int col, int row)
	{
		_parent = parent;
		_col = col;
		_row = row;
	}
	
	private PVector _wind = new PVector();
	private WindMatrix _parent;
	private int _col;
	private int _row;
	
	public PVector get_wind() {
		return _wind;
	}

	public void update() {
		clearWind();
		_wind.add(_parent.getLeft(_col, _row).get_wind());		
	}

	private void clearWind() {
		_wind.x = 0;
		_wind.y = 0;
	}

	public WindCell getLeft() {
		return _parent.getLeft(_col, _row);
	}

	public WindCell getRight() {
		return _parent.getRight(_col, _row);
	}

}