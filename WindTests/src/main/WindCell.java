package main;

import processing.core.PVector;

public class WindCell {
	
	private PVector _wind = new PVector();
	private WindCell[][] _parent;
	
	public PVector get_wind() {
		return _wind;
	}

	public void update() {
		clearWind();
//		_wind.add(leftNeighbour);
		
	}

	private void clearWind() {
		_wind.x = 0;
		_wind.y = 0;
	}

}