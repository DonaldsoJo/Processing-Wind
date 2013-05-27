package wind;

public class Octant {

	public Octant(float heading) {
		_heading = heading;
		_octant = fromHeadingToOctant();
	}

	private float _heading;
	private int _octant;
	
	public int octant() {
		return _octant;
	}
	
	private ColAndRow[] _colsAndRowsIndexedByOctant = {
			new ColAndRow(2,1), // octant 0
			new ColAndRow(2,2),
			new ColAndRow(1,2),
			new ColAndRow(0,2), // octant 3
			new ColAndRow(0,1),
			new ColAndRow(0,0),
			new ColAndRow(1,0), // octant 6
			new ColAndRow(2,0)
	};
	
	private int[][] _octantIndexedByColAndRow = {
			{5, 6, 7},
			{4,-1, 0},
			{3, 2, 1}
	};

	public ColAndRow rotatedColAndRow(int col, int row) {
		int startOctant = _octantIndexedByColAndRow[row][col];
		int targetOctant = (startOctant + _octant) % 8;
		System.out.println("start=" + startOctant + " target=" + targetOctant);
		return _colsAndRowsIndexedByOctant[targetOctant];
	}

	private int fromHeadingToOctant() {
		if ((_heading >= 0) && (_heading < Math.PI/4)) return 0;
		if ((_heading >= Math.PI/4) && (_heading < Math.PI/2)) return 1;
		if ((_heading >= Math.PI/2) && (_heading < 3*Math.PI/4)) return 2;
		if ((_heading >= 3*Math.PI/4) && (_heading <= (float)Math.PI)) return 3;
		if ((_heading >= (float)-Math.PI) && (_heading <= -3*Math.PI/4)) return 4;
		if ((_heading > -3*Math.PI/4) && (_heading <= -Math.PI/2)) return 5;
		if ((_heading > -Math.PI/2) && (_heading <= -Math.PI/4)) return 6;
		if ((_heading > -Math.PI/4) && (_heading < 0)) return 7;
		return 0;
	}

}
