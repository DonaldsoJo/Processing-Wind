package wind;

import processing.core.PVector;


public class ObstacleCell extends CellBase {

	public ObstacleCell(int col, int row) {
		super(col, row);
	}

	@Override
	public PVector getWind() {
		return new PVector();
	}

	@Override
	public void clearCell() {
	}

}
