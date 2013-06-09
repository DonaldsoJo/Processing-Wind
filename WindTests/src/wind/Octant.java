package wind;

public class Octant {

	public Octant(float heading) {
		_heading = heading;
		_octant = fromHeadingToOctant();
	}

	public Octant(ColAndRow index) {
		_index = index;
		_octant = _octantIndexedByColAndRow[index.r][index.c];
	}

	private float _heading;
	private int _octant;
	private ColAndRow _index;
	
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
//		System.out.println("start=" + startOctant + " target=" + targetOctant);
		return _colsAndRowsIndexedByOctant[targetOctant];
	}
	
	private final float onePiOver8 = (float)(Math.PI/8);
	private final float threePiOver8 = (float)(3*Math.PI/8);
	private final float fivePiOver8 = (float)(5*Math.PI/8);
	private final float sevenPiOver8 = (float)(7*Math.PI/8);

	private int fromHeadingToOctant() {
		if ((_heading > -onePiOver8) && (_heading <= onePiOver8)) return 0;
		if ((_heading > onePiOver8) && (_heading <= threePiOver8)) return 1;
		if ((_heading > threePiOver8) && (_heading <= fivePiOver8)) return 2;
		if ((_heading > fivePiOver8) && (_heading <= sevenPiOver8)) return 3;
		if ((_heading > sevenPiOver8) && (_heading <= (float)Math.PI) || (_heading >= -(float)Math.PI) && (_heading <= -sevenPiOver8)) return 4;
		if ((_heading > -sevenPiOver8) && (_heading <= -fivePiOver8)) return 5;
		if ((_heading > -fivePiOver8) && (_heading <= -threePiOver8)) return 6;
		if ((_heading > -threePiOver8) && (_heading <= -onePiOver8)) return 7;
		return 0;
	}

	public Octant clockwise() {
		int oct = _octant + 1;
		if (oct > 7) oct = oct - 8;
		return new Octant(_colsAndRowsIndexedByOctant[oct]);
	}

	public Octant anticlockwise() {
		int oct = _octant - 1;
		if (oct < 0) oct = oct + 8;
		return new Octant(_colsAndRowsIndexedByOctant[oct]);
	}

	public ColAndRow getIndex() {
		// TODO Auto-generated method stub
		return _colsAndRowsIndexedByOctant[_octant];
	}

}
