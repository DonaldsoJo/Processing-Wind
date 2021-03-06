package wind;

import processing.core.PVector;


public class AlgorithmApplyTemplate extends AlgorithmBase {
	
	@Override
	public void calculateResultVector(NeighbourhoodMatrices neighbours) {
		// get mag of source
		NeighbourhoodMatrix thisGen = (NeighbourhoodMatrix) neighbours.currentGenMatrix();
		NeighbourhoodMatrix nextgen = (NeighbourhoodMatrix) neighbours.nextGenMatrix();
		PVector source = thisGen.getCell(1, 1).getWind();
		
		// clone template as rectified matrix
		// TODO: do I really need this clone? Use the template as a readonly reference and have a new matrix instead? Returned from getRectified
		TemplateMatrix templateCopy = _template.cloneMatrix();
		
		applyTemplateToLocalCopy(source, templateCopy);
		
		transferResultToNextGen(nextgen, source, templateCopy);
				
	}

	public void transferResultToNextGen(NeighbourhoodMatrix nextgen,
			PVector source, TemplateMatrix templateCopy) {
		// transfer non-zero cells from rectified to correct target octant
		Octant oct = new Octant(source.heading());
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++) {
				PVector v = templateCopy.getCell(col, row).getWind();
				if (v.mag() > 0){
					Coords cr = oct.rotatedColAndRow(col, row);
					CellBase cell = nextgen.getCell(cr.col, cr.row);
					if (cell instanceof WindCell){
						handleWindCellTarget(v, cell);						
					}
					else if (cell instanceof ObstacleCell){
						// TODO: add obstacle handling
					}
				}
			}
	}

	public void handleWindCellTarget(PVector v, CellBase cell) {
		PVector target = cell.getWind();
		target.add(v);
	}

	public void applyTemplateToLocalCopy(PVector source,
			TemplateMatrix templateCopy) {
		// scalar multiply multiply template by mag to get rectified target
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++) {
				getRectifiedTarget(source, templateCopy, col, row);
			}
	}

	public PVector getRectifiedTarget(PVector source, NeighbourhoodMatrix result, int col, int row) {
		// TODO: tidy this up
		PVector tv = result.getCell(col, row).getWind();
		PVector sourceCopy = source.get();
		sourceCopy.mult(tv.mag());
		sourceCopy.rotate(tv.heading());
		
		if (sourceCopy.mag() > 0.1){
			tv.x = sourceCopy.x;
			tv.y = sourceCopy.y;
		}
		else
			tv.setMag(0);
		
//		result.getCell(col, row).getWind().x = sourceCopy.x;
//		result.getCell(col, row).getWind().y = sourceCopy.y;
		
		return sourceCopy;
	}
	
	private TemplateMatrix _template;

	public TemplateMatrix get_template() {
		return _template;
	}

	public void set_template(TemplateMatrix template) {
		_template = template;
	}

}
