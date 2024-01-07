package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objectChest extends SuperObject {

	/* constructor */
	public objectChest () {
		objectsName = "chest";
		
		try {
			objectsImage = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
