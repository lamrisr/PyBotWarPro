package thinktank.javabot.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import thinktank.javabot.intelligences.Action;
import thinktank.javabot.physics.ObjetTT;
import thinktank.javabot.physics.Physique;
import thinktank.javabot.physics.Tank;


@SuppressWarnings("serial")
public class PanneauDessin extends JPanel {

	private static int tailleCase = 24;
	Physique physique;
	ObjetTT contenu;
	SpriteSet sp = new SpriteSet();
	Sprite mur = new Sprite("ressources/mur.png");
	Sprite sol = new Sprite("ressources/sol.png");
	Sprite projectile = new Sprite("ressources/shot.png");
	AffineTransformOp op;
	
	public PanneauDessin(Physique physique){
		super();
		this.physique =  physique;
	}
	
	private void paintLifeStick(Graphics g, int x, int y, int vie)
	{
		g.setColor(Color.CYAN);
		g.fillRect(x, y - 10, 24*vie/100, 5);
		g.setColor(Color.WHITE);
		g.drawRect(x, y - 10, 24, 5);
	}
	
	private AffineTransformOp computeRotation(Image image, Action action, int avancement)
	{
		double rotationRequired;
		if (action == Action.turnClockwise)
		{
			rotationRequired = Math.toRadians (-90 + ((100 - avancement) / 100.0) * 90);
			System.out.println("r: " + (-90 + ((100 - avancement) / 100.0) * 90));
		}
		else if (action == Action.turnCounterClockwise)
		{
			rotationRequired = Math.toRadians (-90 + (avancement - 10));
		}
		else
		{
			rotationRequired = Math.toRadians (0);
		}
		 
		double locationX = image.getWidth(null) / 2;
		double locationY = image.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op;
	}
	
	/**
	 * Fonctions de dessins pour les divers éléments du jeu
	 * @param g Outil de dessin permettant de définir l'affichage à l'écran
	 **/
	public void paintComponent(Graphics g) 
    {     
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int lx = 42;
		int ly = 24;
		
		for(int i = 0; i < lx; i++){
			for(int j = 0; j< ly; j++){
				
				int x = i * tailleCase;
				int y = j * tailleCase;
				
				contenu = physique.detail(i, j);
				
				if(contenu.getType() == Physique.type.vide){
					//g.drawImage(sol.getImg(),x,y,tailleCase,tailleCase,null);
					
				}
				else if(contenu.getType() == Physique.type.mur){
					g.drawImage(mur.getImg(),x,y,tailleCase,tailleCase,null);
				}
				else if(contenu.getType() == Physique.type.tank){
					
					
					int dy = ((Tank)contenu).getDirection().getDy();
					int dx = ((Tank)contenu).getDirection().getDx();
					int ni = 0;
					
				/*	if(dx < 0)
						ni = 2;
					if(dx > 0)
						ni = 3;
					if(dy < 0)
						ni = 0;
					if(dy > 0)
						ni = 1;
						*/
					
					if (dy > 0)
						ni = 1;
					else if (dx != 0)
						ni = dx < 0 ? 2 : 3;
					
					
					int vie = Integer.valueOf(((Tank)contenu).getPointsDeVie());
					int posx = x;
					int posy = y;
					if (((Tank)contenu).getDeplacementStatus() == Action.moveForward || 
							((Tank)contenu).getDeplacementStatus() == Action.moveBackward)
					{
						switch (ni)
						{
							case 0: //HAUT
								posx = x ;
								posy = (int) (y + (((Tank)contenu).getAvancement() / 100.0) * tailleCase);
							
							
							break;
							
							case 1: //BAS
								posx = x ;
								posy = (int) (y - (((Tank)contenu).getAvancement() / 100.0) * tailleCase);
							
							break;
							
							case 2: //GAUCHE
								posx = (int) (x + (((Tank)contenu).getAvancement() / 100.0) * tailleCase);
								posy = y;
		
							break;
							
							default: //DROITE
							
								posx = (int) (x - (((Tank)contenu).getAvancement() / 100.0) * tailleCase);
								posy = y;
							
							break;
					}
							}
					 op = computeRotation(
							sp.getImg(ni), 
							(((Tank)contenu).getDeplacementStatus()), 
							(((Tank)contenu).getAvancement())
									);
					g.drawImage(op.filter(sp.getImg(ni), null), posx, posy, /*tailleCase, tailleCase,*/ null);
					paintLifeStick(g, posx, posy, vie);
					
				}
				else if(contenu.getType() == Physique.type.projectile){
					g.drawImage(projectile.getImg() ,x,y,tailleCase,tailleCase,null);
				}		
			}
		}
		
		
		
    }


}
