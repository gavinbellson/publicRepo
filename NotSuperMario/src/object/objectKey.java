package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objectKey extends SuperObject {

	/* constructor */
	public objectKey () {
		objectsName = "key";
		
		try {
			objectsImage = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
