package wind;

import processing.core.PVector;


public class AlgorithmApplyTemplate extends AlgorithmBase {
	
	@Override
	public void calculateResultVector(NeighbourhoodMatrices neighbours) {
		// get mag of source
		NeighbourhoodMatrix thisGen = (NeighbourhoodMatrix) neighbours.currentGenMatrix();
		NeighbourhoodMatrix nextgen = (NeighbourhoodMatrix) neighbours.nextGenMatrix();
		PVector source = thisGen.centre().get_wind();
		PVector sourceNormal = source.normalize(null);
		
		// clone template as rectified matrix
		NeighbourhoodMatrix result = _template.cloneMatrix();
		
		// scalar multiply multiply template by mag to get rectified target
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++) {
				PVector v = result.getCell(col, row).get_wind();
				v.mult(source.mag());
				v.dot(sourceNormal);
			}
		
		// transfer non-zero cells from rectified to correct target octant
		Octant oct = new Octant(source.heading());
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++) {
				PVector v = result.getCell(col, row).get_wind();
				if (v.mag() > 0){
					ColAndRow cr = oct.rotatedColAndRow(col, row);
					PVector t = nextgen.getCell(cr.c, cr.r).get_wind();
					t.x = v.x;
					t.y = v.y;
				}
			}
				
	}
	
	private NeighbourhoodMatrix _template;

	public NeighbourhoodMatrix get_template() {
		return _template;
	}

	public void set_template(NeighbourhoodMatrix template) {
		_template = template;
	}

}
