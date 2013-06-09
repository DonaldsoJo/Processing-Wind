package wind;

import processing.core.PVector;

public class WindCell extends CellBase {
	
	public WindCell(int col, int row) {
		super(col, row);
	}

	private PVector _wind = new PVector();
	@Override
	public PVector getWind() {
		return _wind;
	}

	@Override
	public void clearCell() {
		_wind.x = 0;
		_wind.y = 0;
	}

	public void changeHeading(float headingChange) {
//		System.out.println("Heading before:" + _wind.heading() + ", change:" + headingChange);
		_wind.rotate(headingChange);
//		System.out.println("Heading after:" + _wind.heading());
	}

}