import com.neuronrobotics.bowlerstudio.vitamins.Vitamins
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cylinder


CSG makePlate() {
	
double plateWidth = 100
double plateLength = 150
double plateThickness = 5
double holeRadius = 5
double holeOffset = 10
    // Create the base plate
    CSG plate = new Cylinder(plateWidth/2, plateLength/2, plateThickness, (int)30).toCSG()
    
    // Create a single hole
    CSG hole = new Cylinder(holeRadius, plateThickness, (int)30).toCSG()
    
    // Position and subtract holes
    plate = plate.difference([
        hole.movex(plateWidth/2 - holeOffset).movey(plateLength/2 - holeOffset),
        hole.movex(-(plateWidth/2 - holeOffset)).movey(plateLength/2 - holeOffset),
        hole.movex(plateWidth/2 - holeOffset).movey(-(plateLength/2 - holeOffset)),
        hole.movex(-(plateWidth/2 - holeOffset)).movey(-(plateLength/2 - holeOffset))
    ])
    
    return plate.toZMin()
}

return makePlate()