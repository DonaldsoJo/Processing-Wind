package wind;

public enum ENeighbour {
	TOPLEFT(0),
	TOP(1),
	TOPRIGHT(2),
	LEFT(3),
	CENTRE(4),
	RIGHT(5),
	BOTLEFT(6),
	BOTTOM(7),
	BOTRIGHT(8);
	
	private final int _index;
	
	private ENeighbour(int index) {
		_index = index;
	}
	
	public int getIndex() {
		return _index;
	}
}
