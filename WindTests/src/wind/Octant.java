package wind;

public class Octant {

	public Octant(float heading) {
		_heading = heading;
	}

	private float _heading;
	
	public int rotatedCol(int col) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int rotatedRow(int row) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int octant() {
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
