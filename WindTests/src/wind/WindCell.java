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
	public PVector get_wind() {
		return _wind;
	}

	private WindMatrix _parent;
	
	private int _col;
	public int getCol() {
		return _col;
	}

	private int _row;
	public int getRow() {
		return _row;
	}

	public void update() {
		clearNext();
		// go round all neighbours
		// update the other matrix
		System.out.println("current left x " + _parent.getLeft(_col, _row).get_wind().x);
		_parent.otherCells()[_col][_row].get_wind().add(_parent.getLeft(_col, _row).get_wind());		
	}

	private void clearNext() {
		_parent.clearNext( _col, _row);
	}

	public WindCell getLeft() {
		return _parent.getLeft(_col, _row);
	}

	public WindCell getRight() {
		return _parent.getRight(_col, _row);
	}

}