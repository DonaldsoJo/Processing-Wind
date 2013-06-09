package wind;

import processing.core.PVector;


public class AlgorithmTemplateAndObstacles extends AlgorithmBase {
	
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
		Octant sourceOctant = new Octant(source.heading());
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++) {
				PVector vTargetIncrement = templateCopy.getCell(col, row).getWind();
				if (vTargetIncrement.mag() > 0){
					CellBase targetCell = getRotatedCellAtOctant(nextgen, sourceOctant, col, row);
					if (targetCell instanceof WindCell){
						updateWindCellTarget(vTargetIncrement, targetCell);						
					}
					else if (targetCell instanceof ObstacleCell){
						handleObstacleCellTarget(vTargetIncrement, targetCell, nextgen);						
					}
				}
			}
	}

	public CellBase getRotatedCellAtOctant(NeighbourhoodMatrix nextgen, Octant sourceOctant, int col, int row) {
		ColAndRow sourceCr = sourceOctant.rotatedColAndRow(col, row);
		CellBase targetCell = nextgen.getCell(sourceCr.c, sourceCr.r);
		return targetCell;
	}
	
	public enum Rotation{ clockwise, antiClockwise};

	public void handleObstacleCellTarget(PVector vTarget, CellBase targetCell, NeighbourhoodMatrix nextgen) {
		// half the inbound vector - the wind goes both ways round the obstacle
		PVector clockwiseV = splitVector(vTarget, (float) (Math.PI/4));
		PVector anticlockwiseV = splitVector(vTarget, (float) (-Math.PI/4));
		
		// starting from the target octant (it is an obstacle) look clockwise for the first wind cell
		assignSplitWindToNextWindCell(clockwiseV, targetCell, Rotation.clockwise, nextgen);
		
		// look anti-clockwise for the first wind cell
		assignSplitWindToNextWindCell(anticlockwiseV, targetCell, Rotation.antiClockwise, nextgen);
	}

	public PVector splitVector(PVector vTarget, float rotation) {
		PVector v = vTarget.get();
		v.setMag(v.mag()/2);
		v.rotate(rotation);
		return v;
	}

	public static void assignSplitWindToNextWindCell(
			PVector vTarget, CellBase targetCell, Rotation direction, NeighbourhoodMatrix nextgen) {
		ColAndRow firstCell = findFirstCell( targetCell, direction, nextgen);	
		if (firstCell != null){
			CellBase newTargetCell = nextgen.getCell(firstCell.c, firstCell.r);
			newTargetCell.getWind().add(vTarget);			
		}
	}

	public static ColAndRow findFirstCell(CellBase targetCell, Rotation direction, NeighbourhoodMatrix nextgen) {
		// find where this cell is in the neighbours - TOD: consider passing this in as we surely know it?
		
		ColAndRow cellIndexInNeighbours = findMatchingCellInNeighbours(targetCell, nextgen);
		Octant o = new Octant(cellIndexInNeighbours);
		
		o = (direction == Rotation.clockwise) ? o.clockwise() : o.anticlockwise();
		cellIndexInNeighbours = o.getIndex();
		CellBase c = nextgen.getCell(cellIndexInNeighbours.c, cellIndexInNeighbours.r);
		
		// get next cell in the direction until a wind found or reached end
		int cellsInspected = 1;
		while( (c instanceof ObstacleCell) && (cellsInspected <= 4 )) {
			cellsInspected++;
			o = (direction == Rotation.clockwise) ? o.clockwise() : o.anticlockwise();
			cellIndexInNeighbours = o.getIndex();
			c = nextgen.getCell(cellIndexInNeighbours.c, cellIndexInNeighbours.r);
		}
		return (c instanceof WindCell) ? cellIndexInNeighbours : null;
	}

	public static ColAndRow findMatchingCellInNeighbours(CellBase targetCell,
			NeighbourhoodMatrix nextgen) {
//		System.out.println("Target col=" + targetCell.getCol() + " row=" + targetCell.getRow());
		for(int col=0; col<nextgen.getCells().length; col++)
			for(int row=0; row<nextgen.getCells()[0].length; row++){
//				System.out.println("Index col=" + col + " row=" + row);
				CellBase c = nextgen.getCell(col, row);
//				System.out.println("Source col=" + c.getCol() + " row=" + c.getRow());
				if ((targetCell.getCol() == c.getCol()) && (targetCell.getRow() == c.getRow())) return new ColAndRow(col, row);
			}
		return null; // TODO: do we come here from edge cases? Where the target cell is outside the array?
	}

	public void updateWindCellTarget(PVector vTarget, CellBase cell) {
		cell.getWind().add(vTarget);
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
