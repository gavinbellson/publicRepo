package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objectDoor extends SuperObject {
	/* constructor */
	public objectDoor () {
		objectsName = "door";
		
		try {
			objectsImage = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
