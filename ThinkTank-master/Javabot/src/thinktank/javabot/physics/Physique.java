package thinktank.javabot.physics;

import java.util.ArrayList;


import thinktank.javabot.intelligences.Action;

import thinktank.javabot.sensors.DetectionLigneDroite;



public class Physique {
	
	public enum type{
		tank,
		vide,
		projectile,
		mur
	}
	

	private int tour = -1;

	
	public boolean isAffichageOn() {
		return map.isAffichageOn();
	}
	
	public void AffichageOn() {
		map.AffichageOn();
	}
	
	public void AffichageOff() {
		map.AffichageOff();
	}

	private Terrain map;

	
	public Physique(int lignes, int colonnes){
		map = new Terrain(lignes, colonnes);
	}
	public Terrain getMap(){
		return map;
	}
	
	public ObjetTT[][] getTerrain() 
	/**
	 * retourne la matrice du terrain
	 */
	{
		return map.getMap();
	}
	
	public int tailleX()
	/**
	 * retourne la taille abscisse de la matrice
	 */
	{
		return map.tailleX();
	}
	
	public int tailleY()
	/**
	 * retourne la taille ordonnée de la matrice
	 */
	{
		return map.tailleY();
	}
	
	public ObjetTT detail (int x, int y)
	/**
	 * retourne l'objet à la position (x,y)
	 */
	{
			return map.detail(x, y);
	}
	
	public boolean estLibre(int x, int y){
		return map.estLibre(x, y);
	}
	
	public ArrayList<Tank> getTanks()
	/**
	 * retourne la liste des Tanks
	 */
	{
		return map.getTanks();
	}
	
	public void addTank()
	/**
	 * ajoute un tank par defaut
	 */
	{
		map.addTank();
	}
	
	public void addTank(String filepath)
	/**
	 * ajoute un tank avec l'ia dans le fichier filepath
	 * @param filepath  position du fichier
	 */
	{
		map.addTank(filepath);
	}
	
	public void addTank(int x, int y, String filepath)
	/**
	 * ajoute un tank avec l'ia dans le fichier filepath à la position(x,y)
	 * @param x  coordonée x du tank
	 * @param y  coordonée y du tank
	 * @param filepath position du fichier
	 */
	{
		if(estLibre(x, y))
			map.addTank(x,y,filepath,this);
		else
			System.out.println("pas libre ! ");
	}
	
	/*public ArrayList<Projectile> getProjectiles()
	**
	 * retourne la liste des Projectiles
	 *
	{
		return map.getProjectiles();
	}*/

	
	public ArrayList<Mobile> getMobiles()
	/**
	 * retourne la liste des Mobiles
	 */
	{
		ArrayList<Mobile> m = new ArrayList<Mobile>();
		for(Tank t : getTanks())
			m.add(t);

	/*	for(Projectile p : getProjectiles())
			m.add(p);*/

		return m;
	}
	
	public type caseContent(int x, int y)
	/**
	 * retourne le type contenu dans la case(x,y)
	 */
	{
		return map.caseContent(x, y);
	}
	
	public void newMur(int x, int y){
		map.newMur(x, y);
	}
	
	public void iter(){
		/**
	 	* lance la prochaine action de tout les éléments du Terrain
	 	*/
		
		int i = 0;
	/*	if (map.getProjectile() == null)
		{
			int k = getTanks().size();
			
				for (int l = 0; l < k; l++ )
				{
					Tank t = getTanks().get(l);
				System.out.println("TANK!");
				
			
					t.lancerIA();
					t.reduireTempsRestant();
				}
		}*/
		/*
		
		for (Projectile p: map.getProjectiles())
		{
			p.avancer();
			if (!p.getMort())
				continue;
		}
		
	

		ArrayList<Projectile> ps = map.getProjectiles();
		if(ps.size() > 0){
			Projectile p = ps.get(i);
			while(p != null && i < ps.size()){
				p.avancer();
				if(i < ps.size()){
					if( !p.getMort()){
						i++;
						if(i < ps.size())
							p = ps.get(i);
					}
					else // si il est mort


						if(Projectile.getIdMort() == -1)
							p=ps.get(i);
						else{
							if(p.getId() > Projectile.getIdMort()){
								i = i--;
								if(i < ps.size())
									p = ps.get(i);
							}
							else{
								if(i < ps.size())
									p = ps.get(i);
							}
							Projectile.initIdMort();
						}
				}
			}
		}

		*/

		//Tank.getIntels().waitForAllActions();
		
		
		

		/*
		i = 0;
		ArrayList<Tank> ts = getTanks();
		System.out.println("size: "+ts.size());
		if(ts.size() > 0){
			
			Tank t = ts.get(i);
			while(t != null && i < ts.size()){
				t.getAction();
				if (i < ts.size()){
					if( !t.getMort()){
						i++;
						
						///if(i< ts.size())
						//{
							//t = ts.get(i);
					//	}

					}
				else
					t = ts.get(i);
				}
			}
		}

		*/
		Tank t;
		int mobId;
		if (map.getProjectile() != null)
		{
			mobId = map.getProjectile().avancer();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			/* Si un tank est détruit et qu'il était placé avant ou à à l'emplacement du tour courant dans la liste, on ne modifie pas le tour courant
			 * La suppression d'un élément d'un arraylist décale automatiquement tous les autres éléments vers la gauche.
			 * tour = tour - 1;
			 */
			if (mobId != -1)
			{
				for (int j = 0; j < getTanks().size() && j <= tour; j++ )
				{
					if (getTanks().get(j).getId() == mobId)
					{
						tour--;
						break;
					}
				}
			}
		}
		else
		{
			if (getTanks().size() != 0)
			{
				tour = (tour + 1) % getTanks().size();
				System.out.println("Tour numero "+tour);
					
					t = getTanks().get(tour);
					t.lancerIA();
					t.reduireTempsRestant();
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
					t.getAction();
				
					System.out.println("ttank");
			
			}
		
		
		}
	
	}
	
	

}


