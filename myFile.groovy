import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Transform

CSG cube = new Cube(0) (100).toCSG()
cube.addSlicePlane(new Transform());

return [cube,cube.movez(200)]