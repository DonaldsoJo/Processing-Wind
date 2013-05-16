package wind;

import javax.print.attribute.standard.MediaSize.Other;

import processing.core.PVector;

public class NeighbourhoodMatrices extends BaseMatrices {

	public NeighbourhoodMatrices(int cols, int rows) {
		super(cols, rows);
	}

	public void update() {
		WindCell c = this.otherMatrix().getCell(1, 1);
		c.clearCell();
		PVector v = c.get_wind();
		NeighbourhoodMatrix current = (NeighbourhoodMatrix) currentMatrix();
		v.add(current.topLeft().get_wind());
		v.add(current.topMiddle().get_wind());
		v.add(current.topRight().get_wind());
		v.add(current.left().get_wind());
		v.add(current.right().get_wind());
		v.add(current.bottomLeft().get_wind());
		v.add(current.bottomMiddle().get_wind());
		v.add(current.bottomRight().get_wind());
	}

	@Override
	public NeighbourhoodMatrix[] setupMatrices(int cols, int rows) {
		NeighbourhoodMatrix matrices[] = new NeighbourhoodMatrix[2];
		matrices[0] = new NeighbourhoodMatrix(cols, rows);
		matrices[1] = new NeighbourhoodMatrix(cols, rows);
		return matrices;
	}

}
