package wind;

import processing.core.PVector;

public class WindCell {
	
	public WindCell( WindMatrix windMatrix, int col, int row)
	{
//		_parent = windMatrix;
		_col = col;
		_row = row;
	}
	
	public WindCell(int col, int row) {
		_col = col;
		_row = row;
	}

	private PVector _wind = new PVector();
	public PVector get_wind() {
		return _wind;
	}

//	private WindMatrix _parent;
	
	private int _col;
	public int getCol() {
		return _col;
	}

	private int _row;
	public int getRow() {
		return _row;
	}

//	public void update() {
//		// clear the other cell
//		_parent.clearOther( _col, _row);
//		
//		// add all inbound vectors
//		PVector v = new PVector();
//		v.add( getLeft().get_wind());
//		
//		// update other
//		_parent.updateOther(_col, _row, v);
//	}
//
//	public WindCell getLeft() {
//		return _parent.getLeft(_col, _row);
//	}
//
//	public WindCell getRight() {
//		return _parent.getRight(_col, _row);
//	}

	public void clearCell() {
		_wind.x = 0;
		_wind.y = 0;
	}

}