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

	@Override
	public WindMatrix[] setupMatrices(int cols, int rows) {
		WindMatrix matrices[] = new WindMatrix[2];
		matrices[0] = new WindMatrix(cols, rows);
		matrices[1] = new WindMatrix(cols, rows);
		return matrices;
	}

}