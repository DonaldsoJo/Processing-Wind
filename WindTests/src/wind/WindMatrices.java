package wind;

import processing.core.PVector;


public class WindMatrices extends BaseMatrices
{
	public WindMatrices(int cols, int rows)
	{
		super( cols, rows);
	}
	
	public void flipMatrices() {
		_currentMatrixIndex = otherMatrixIndex();
	}

}