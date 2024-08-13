import com.neuronrobotics.bowlerstudio.scripting.BlenderLoader
import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine
import com.neuronrobotics.bowlerstudio.vitamins.Vitamins
import eu.mihosoft.vrl.v3d.*
double linkLen=50
CSG servo = Vitamins.get("hobbyServo", "mg92b")
				
CSG horn = Vitamins.get("hobbyServoHorn", "standardMicro1")
					.toZMax()
					.rotz(90)
//Take a slice around the motor
CSG servoSlice = servo.getBoundingBox().toZMax().intersect(servo.getBoundingBox().toZMin().movez(horn.getMinZ()))
CSG hornBlock = horn.scalex(2).scaley(2)
//Make a slice around the horn
CSG servoBlock=servoSlice.scalex(2)
						.movex(linkLen)
servo=servo.movex(linkLen)

//Prodce a simple link
CSG linkBlank = hornBlock.union(servoBlock).hull()
String url="https://github.com/madhephaestus/TestRepo.git"
File blender = ScriptingEngine.fileFromGit(url, "pipelineTest.blend")
if(!blender.exists()) {
	ScriptingEngine.ignore(url, "**.blend1");
	BlenderLoader.toBlenderFile(linkBlank, blender);
	com.neuronrobotics.bowlerstudio.BowlerStudio.createFileTab(blender);
}
linkBlank = ScriptingEngine.inlineFileScriptRun(blender, null);

// take slices out of the modified link
CSG finishedLink = linkBlank.difference(servo,horn)

return [finishedLink,servo,horn]




