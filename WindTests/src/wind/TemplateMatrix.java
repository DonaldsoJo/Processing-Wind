package wind;

public class TemplateMatrix extends NeighbourhoodMatrix {
	
	public TemplateMatrix cloneMatrix() {
		TemplateMatrix m = new TemplateMatrix();
		for(int col=0; col<m.getCells().length; col++)
			for (int row=0; row<getCells()[0].length; row++)
				// TODO: use .get()?
				m.setWindCell(col, row, this.getCell(col, row).getWind().x, this.getCell(col, row).getWind().y);
		return m;
	}

}
