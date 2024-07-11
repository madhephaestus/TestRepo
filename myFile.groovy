import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine
import com.neuronrobotics.bowlerstudio.vitamins.Vitamins

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Transform

File stlToImport =ScriptingEngine.fileFromGit(
		"https://github.com/NeuronRobotics/NASACurisoity.git"
		, "STL/upper-arm.STL");
CSG toSlice = Vitamins.get(stlToImport,true);
String name="upperArm";
toSlice.setName(name);
toSlice.addSlicePlane(new Transform().rotY(90).movex(30));
toSlice.addSlicePlane(new Transform().movez(toSlice.getMaxZ()-0.5));
toSlice.addSlicePlane(new Transform().movez(toSlice.getMaxZ()-0.51));
toSlice.addSlicePlane(new Transform());

CSG cube = new Cube(50).toCSG().movez(30);

return [toSlice,cube]