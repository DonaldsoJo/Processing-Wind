package wind;

public class NeighbourhoodMatrix extends WindMatrix {

	public NeighbourhoodMatrix(int cols, int rows) {
		super(cols, rows);
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
