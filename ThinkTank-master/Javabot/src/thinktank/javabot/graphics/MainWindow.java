package thinktank.javabot.graphics;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import thinktank.javabot.physics.Physique;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private PanneauDessin game;

	private static JButton editer = new JButton("AJOUTER TANK");
	 
	private static JFileChooser chooser = new JFileChooser();
    

	private static JTextField inputX = new JTextField(); 
	private static JTextField inputY = new JTextField();
	
	private static JLabel labelX = new JLabel("  X : ");
	private static JLabel labelY = new JLabel("  Y : ");
	

	/* Coordonnées saisies par le user */
	private static int setX;
	private static int setY;
	
	private JPanel container = new JPanel();
	private JPanel c2 = new JPanel();
	
	private static Physique phy;
	
	
	/**
	 * Affichage principal de l'application
	 **/
	public MainWindow() {
		
		int lx = 30;
		int ly = 22;
		phy = new Physique(lx, ly);
		game = new PanneauDessin(phy);
		
		this.setTitle("JavaBot");
		this.setSize(740, 560);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	
		inputX.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputX.getMinimumSize().height));
        add(inputX);
        inputY.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputY.getMinimumSize().height));
        add(inputY);
        

		container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
		

		c2.setLayout(new BoxLayout(c2, BoxLayout.LINE_AXIS));
		//c2.setLayout(new FlowLayout());
		c2.setSize(new Dimension(10,10));

		c2.add(editer);
		
		c2.add(labelX);
		c2.add(inputX);
		
		c2.add(labelY);
		c2.add(inputY);

		c2.setSize(25, 25);

		
		container.add(game);
		container.add(c2);
		
		
		this.setContentPane(container);
		this.setResizable(true);
		this.setVisible(true); 
		
		game.repaint();
		GraphicInterface NewGame=new GraphicInterface();
		
		
		NewGame.jPanel5.add(container);
		//NewGame.jPanel5.add(c2);
		NewGame.setVisible(true);
		this.setVisible(false); 
	
	}
	
	/**Graphics
	 * Fonction main principale
	 **/
	public static void main(String args[]) {
		final MainWindow window = new MainWindow();
		
		// MAP HARD CODE
		for(int i = 5; i < 10; i++)
		{
			window.phy.newMur(i,5);
	
		}
		for(int i = 18; i < 25; i++)
		{
			window.phy.newMur(i,15);
	
		}
		for(int i = 5; i < 10 ; i++)
		{
			window.phy.newMur(30,i);
	
		}
		for(int i = 28; i < 33; i++)
		{
			//window.phy.newMur(i,20);
	
		}
		for(int i = 10; i < 15 ; i++)
		{
			window.phy.newMur(8,i);
	
		}
		
		
		editer.addActionListener(new ActionListener()
		{
	         
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		         

		    	
		        int returnVal = chooser.showOpenDialog(null);

		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		           System.out.println("You chose to open this file: " +
		                chooser.getSelectedFile().getName());
		       
		        }
		        
		        setX = Integer.valueOf(inputX.getText());
		        setY = Integer.valueOf(inputY.getText());
		        
		        window.phy.addTank(setX, setY,"ressources/"+chooser.getSelectedFile().getName());
		    }
		});
		
		while(true){
			if(!GraphicInterface.stoped){
				window.phy.iter();
			}
			window.game.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}