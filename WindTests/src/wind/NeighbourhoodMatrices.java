package wind;

import javax.print.attribute.standard.MediaSize.Other;

import processing.core.PVector;

// just a change to try the commit - and another small change

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

	public void update() {
		WindCell c = this.otherMatrix().getCell(1, 1);
		c.clearCell();
		PVector v = c.get_wind();
		v.add(topLeft().get_wind());
		v.add(topMiddle().get_wind());
		v.add(topRight().get_wind());
		v.add(left().get_wind());
		v.add(right().get_wind());
		v.add(bottomLeft().get_wind());
		v.add(bottomMiddle().get_wind());
		v.add(bottomRight().get_wind());
	}


}
