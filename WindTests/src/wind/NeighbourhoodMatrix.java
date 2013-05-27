package wind;

public class NeighbourhoodMatrix extends WindMatrix {

	public NeighbourhoodMatrix(int cols, int rows) {
		super(cols, rows);
	}
	
	public NeighbourhoodMatrix() {
		super(3,3);
	}

	public NeighbourhoodMatrix cloneMatrix(){
		NeighbourhoodMatrix m = new NeighbourhoodMatrix();
		for(int col=0; col<m.getCells().length; col++)
			for (int row=0; row<getCells()[0].length; row++)
				m.setCell(col, row, this.getCell(col, row).get_wind().x, this.getCell(col, row).get_wind().y);
		return m;
	}

	public WindCell topLeft() {
		return getCell(0, 0);
	}

	public WindCell left() {
		return getCell(0, 1);
	}

	public WindCell right() {
		return getCell(2, 1);
	}

	public WindCell topMiddle() {
		return getCell(1, 0);
	}

	public WindCell topRight() {
		return getCell(2, 0);
	}

	public WindCell centre() {
		return getCell(1, 1);
	}

	public WindCell bottomLeft() {
		return getCell(0, 2);
	}

	public WindCell bottomMiddle() {
		return getCell(1, 2);
	}

	public WindCell bottomRight() {
		return getCell(2, 2);
	}
}
