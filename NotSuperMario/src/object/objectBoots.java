package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objectBoots extends SuperObject {
	/* constructor */
	public objectBoots () {
		objectsName = "boots";
		
		try {
			objectsImage = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
