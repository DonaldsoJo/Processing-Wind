package wind;

// just a change to try the commit
public class NeighbourhoodMatrices extends BaseMatrices {

	public NeighbourhoodMatrices(int cols, int rows) {
		super(cols, rows);
	}

	public WindCell topLeft() {
		return currentMatrix().getCell(0, 0);
	}

	public WindCell left() {
		return currentMatrix().getCell(0, 1);
	}

	public WindCell right() {
		return currentMatrix().getCell(2, 1);
	}

	public WindCell topMiddle() {
		return currentMatrix().getCell(1, 0);
	}

	public WindCell topRight() {
		return currentMatrix().getCell(2, 0);
	}

	public WindCell centre() {
		return currentMatrix().getCell(1, 1);
	}

	public WindCell bottomLeft() {
		return currentMatrix().getCell(0, 2);
	}

	public WindCell bottomMiddle() {
		return currentMatrix().getCell(1, 2);
	}

	public WindCell bottomRight() {
		return currentMatrix().getCell(2, 2);
	}


}
