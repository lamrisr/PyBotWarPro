package thinktank.javabot.graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jnr.ffi.Struct.sa_family_t;

public class SpriteSet {
	
	Image img[] = new Image[4]; 
	
	
	public SpriteSet() {
		
		try {

		    img[0] = ImageIO.read(new File("ressources/TankHCyan.png"));
		    img[1] = ImageIO.read(new File("ressources/TankBCyan.png"));
		    img[2] = ImageIO.read(new File("ressources/TankGCyan.png"));
		    img[3] = ImageIO.read(new File("ressources/TankDCyan.png"));

		} catch (IOException e) {
			System.out.println(e);
		}
    
    }


	public Image getImg(int num) {
		return img[num];
	}
	

}
