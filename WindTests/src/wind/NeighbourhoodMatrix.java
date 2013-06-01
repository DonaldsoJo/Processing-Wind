package wind;

public class NeighbourhoodMatrix extends WindMatrix {

	public NeighbourhoodMatrix(int cols, int rows) {
		super(cols, rows);
	}
	
	public NeighbourhoodMatrix() {
		super(3,3);
	}

	public NeighbourhoodMatrix cloneMatrix(){
		NeighbourhoodMatrix m = new NeighbourhoodMatrix();
		for(int col=0; col<m.getCells().length; col++)
			for (int row=0; row<getCells()[0].length; row++)
				// TODO: use .get()?
				m.setCell(col, row, this.getCell(col, row).getWind().x, this.getCell(col, row).getWind().y);
		return m;
	}

}
