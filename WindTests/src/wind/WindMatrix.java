package wind;

public class WindMatrix {
	private CellBase[][] _cells;

	public WindMatrix(int cols, int rows) {
		_cells = new CellBase[cols][rows];
		for(int c=0; c<cols; c++)
			for (int r=0; r<rows; r++)
				_cells[c][r] = new WindCell(c, r);
	}
	
	public CellBase[][] getCells() {
		return _cells;
	}

	public void setCells(WindCell[][] _cells) {
		this._cells = _cells;
	}

	public CellBase getCell(int col, int row) {
		if (indexInBounds(col, row)) return _cells[col][row];
		return new WindCell(col, row);
	}

	public boolean indexInBounds(int col, int row) {
		if ((col < 0) || (col >= _cells.length) || (row < 0) || (row >= _cells[0].length)) return false;
		return true;
	}

	public CellBase getLeft(int col, int row) {
		return getCell(col-1,row);
	}

	public CellBase getRight(int col, int row) {
		return getCell(col+1,row);
	}

	public void replaceCell(int col, int row, CellBase cell) {
		_cells[col][row] = cell;
	}

	public void setWindCell(int col, int row, float x, float y) {
		CellBase c = _cells[col][row];
		if (c instanceof WindCell){
			((WindCell) c).getWind().x = x;
			((WindCell) c).getWind().y = y;
		}	
	}

	public void setWindCell(int col, int row, float x, float y, float mag) {
		setWindCell(col, row, x, y);
		_cells[col][row].getWind().setMag(mag);
	}

	public float energy() {
		float energy = 0.0f;
		for(CellBase[] col : _cells)
			for(CellBase cell : col)
				if (cell instanceof WindCell) energy = energy + ((WindCell) cell).getWind().mag();
		return energy;
	}

	public void setObstacle(int col, int row) {
		_cells[col][row] = new ObstacleCell(col,row);
	}

}