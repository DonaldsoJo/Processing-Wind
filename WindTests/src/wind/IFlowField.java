package wind;

import processing.core.PVector;

public interface IFlowField {

	public abstract PVector[][] getFlowField( int iterations);
	// public abstract void setWindCell(int col, int row, float x, float y, float mag);
	public abstract void setTemplateCell(int col, int row, float x, float y, float mag);
	// set Obstacles
	// set Pump parameters

}