package wind;

import processing.core.PVector;

public class WindMatrix {
	private WindCell[][] _cells;
	private WindMatrices _parent;

	public WindMatrix(int cols, int rows, WindMatrices parent) {
		_cells = new WindCell[cols][rows];
		_parent = parent;
	}
	
	public WindCell[][] getCells() {
		return _cells;
	}

	public void setCells(WindCell[][] _cells) {
		this._cells = _cells;
	}

	public WindCell getCell(int col, int row) {
		if (indexInBounds(col, row)) return _cells[col][row];
		return new WindCell(this, col, row);
	}

	public boolean indexInBounds(int col, int row) {
		if ((col < 0) || (col >= _cells.length) || (row < 0) || (row >= _cells[0].length)) return false;
		return true;
	}

	public WindCell getLeft(int col, int row) {
		return getCell(col-1,row);
	}

	public WindCell getRight(int col, int row) {
		return getCell(col+1,row);
	}

	public void updateCells() {
		for(WindCell[] cols : _cells)
			for(WindCell cell: cols)
				cell.update();
	}

	public void setCell(int col, int row, float x, float y) {
		_cells[col][row].get_wind().x = x;
		_cells[col][row].get_wind().y = y;
	}

	public void clearOther(int col, int row) {
		_parent.clearOther(col, row);
	}

	public void updateOther( int col, int row, PVector newWind) {
		_parent.updateOther( col, row, newWind);
	}
}