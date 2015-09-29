/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinktank.javabot.graphics;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import thinktank.javabot.physics.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author Haroun
 */
@SuppressWarnings("serial")
public class GraphicInterface extends javax.swing.JFrame implements WindowListener, KeyListener {

    /**
     * Creates new form MainGameWindow
     */
	public static String TankChoice="";
	public static int xp=0;
	public static int yp=0;
	public static JFileChooser chooser = new JFileChooser();
	private static Tank selectedTank;
	/* Plusieurs valeurs possibles pour stoped:
	 * 0: Le jeu est en exécution
	 * 1: Le jeu est arrêté
	 * 2: L'arrêt est en cours et sera effectif à la fin des itérations de fluidité*/
	public static int stoped=1;
	public static boolean NextStepFlag=false;
	public static JTextPane textAreaCode;
	public static JTextArea textAreaOutput;
	public static JTextArea textAreaHelp;
	public static Highlighter currentLineExecution;
	public static Writer outPut = new StringWriter();
	public static GraphicInterface gui;
	public static boolean devModeActivated = false;
    public GraphicInterface() {
    	
        initComponents();
        GraphicInterface.gui = this;
        this.addWindowListener(this);
        this.addKeyListener(this);
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public static Tank getSelectedTank()
    {
    	return selectedTank;
    }
    
    public static void setSelectedTank(Tank t)
    {
    	checkCodeUpdates();
    	selectedTank = t;
    	updateCodeArea();
    	updateOutputArea();
    	if (selectedTank == null)
    	{
    		updateListTanksOnMap();
    	}
    	else
    	{
    		textAreaCode.setEditable(true);
    	}
    	GraphicInterface.gui.setFocusable(true);
	    GraphicInterface.gui.requestFocusInWindow();
    }
    
    public static void checkCodeUpdates()
    {
    	/* On vérifie si il faut mettre à jour la liste d'instructions.
    	 *  Y-a-t-il eu des modifications sur le script Python ?*/
    	if (selectedTank != null)
    	{
    		if (!textAreaCode.getText().equals(selectedTank.getIntel().getScript().getInstructions()))
    		{
    			selectedTank.getIntel().getScript().updateInstructions(textAreaCode.getText());
    		}
    		
    	}
    }
    public static void updateCodeArea()
    {
    	if (selectedTank != null)
    	{
    		textAreaCode.setText(selectedTank.getIntel().getScript().getInstructions());
    	}
    	else
    	{
    		updateListTanksOnMap();
    	}
    }
    
    public static void updateOutputArea()
    {
    	
    		textAreaOutput.setText(outPut.toString());
    }
    
    public static void updateListTanksOnMap()
    {
    	String initText = "Liste des tanks :\n";
    	textAreaCode.setText(initText);
    	int i = 1;
    	int position = initText.length() + 1;
    	for (Tank t: MainWindow.getPanneauDessin().getPhysique().getTanks())
    	{
    		textAreaCode.insertIcon(new ImageIcon("src/ressources/TankD"+t.tc+".png"));
        try {
        	String textTank = " Tank"+i+"\n";
			textAreaCode.getStyledDocument().insertString(position + (i - 1) * textTank.length() + (i-1), textTank, null);
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
        i++;
    	}
    	
    	textAreaCode.setEditable(false);
    }
    
    public static void updateHighlight(int line)
    {
    	
    	currentLineExecution = textAreaCode.getHighlighter();
    	currentLineExecution.removeAllHighlights();
    	try {
			currentLineExecution.addHighlight(selectedTank.getIntel().getScript().startPositionLine(line), selectedTank.getIntel().getScript().endPositionLine(line), new DefaultHighlighter.DefaultHighlightPainter(Color.cyan));
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void initComponents() {
    	// sert a rien
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setText("jLabel1");

        // jPanel1: Panneau de gauche de débogage
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setPreferredSize(new Dimension(350, 0));
        jPanel1.setMinimumSize(new Dimension(350, 0));
        jPanel1.setMaximumSize(new Dimension(350, 0)); 
        
        // jPanel5: Panneau de jeu
        jPanel5 = new javax.swing.JPanel();
        jPanel5.setPreferredSize(new Dimension(720, 520));
        jPanel5.setMinimumSize(new Dimension(720, 520));
        jPanel5.setMaximumSize(new Dimension(720, 520));
        jPanel5.setLayout(null);

        // jPanel6: Panneau de droite de sélection des tanks
        jPanel6 = new JPanel();
        jPanel6.setPreferredSize(new Dimension(720, 50));
        jPanel6.setMinimumSize(new Dimension(720, 50));
        jPanel6.setMaximumSize(new Dimension(720, 50));

        // jSplitPane3: Panneau de droite
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setTopComponent(jPanel5);        
        jSplitPane3.setRightComponent(jPanel6);
        
        // jSplitPane1: split Panneau de gauche
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane1.getLeftComponent().setMinimumSize(new Dimension());
    	//pane.setDividerLocation(0.0d);
        jSplitPane1.getRightComponent().setMinimumSize(new Dimension());
    	//pane.setDividerLocation(1.0d);
        jSplitPane1.setRightComponent(jSplitPane3);
        jSplitPane1.setLeftComponent(jPanel1);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        final JLabel TankBBrun = new JLabel("");
        MouseMotionListener mms = new MouseMotionListener()
        {

			@Override
			public void mouseDragged(MouseEvent e) {
				int x_origin_windowGame = (int) MainWindow.getContainer().getLocationOnScreen().getX();
				int y_origin_windowGame = (int) MainWindow.getContainer().getLocationOnScreen().getY();
				int x_end_windowGame = x_origin_windowGame + MainWindow.getContainer().getWidth();
				int y_end_windowGame = y_origin_windowGame + MainWindow.getContainer().getHeight();
				
				
					int x_absolute_position = e.getXOnScreen() - x_origin_windowGame;
					int y_absolute_position = e.getYOnScreen() - y_origin_windowGame;
					MainWindow.getPanneauDessin().lastRegisteredMousePosition.x = x_absolute_position;
					MainWindow.getPanneauDessin().lastRegisteredMousePosition.y = y_absolute_position;
				

				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
				
			}
        	
        };
        
        MouseListener ms=new MouseListener(){
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
				int x_origin_windowGame = (int) MainWindow.getContainer().getLocationOnScreen().getX();
				int y_origin_windowGame = (int) MainWindow.getContainer().getLocationOnScreen().getY();
				int x_end_windowGame = x_origin_windowGame + MainWindow.getContainer().getWidth();
				int y_end_windowGame = y_origin_windowGame + MainWindow.getContainer().getHeight();
				

				
				if (TankChoice != "")
				{
					if (e.getXOnScreen() >= x_origin_windowGame && e.getXOnScreen() <= x_end_windowGame && e.getYOnScreen() >= y_origin_windowGame && e.getYOnScreen() <= y_end_windowGame )
					{
						int x_absolute_position = e.getXOnScreen() - x_origin_windowGame;
						int y_absolute_position = e.getYOnScreen() - y_origin_windowGame;
						

					
					int returnVal = chooser.showOpenDialog(null);
				
			        if(returnVal == JFileChooser.APPROVE_OPTION) {
			           System.out.println("You chose to open this file: " +
			               chooser.getSelectedFile().getName());
				        setSelectedTank(MainWindow.phy.addTank(x_absolute_position/MainWindow.getPanneauDessin().getTailleCase(), y_absolute_position/MainWindow.getPanneauDessin().getTailleCase(),"src/ressources/"+GraphicInterface.chooser.getSelectedFile().getName()));

			       
			        }
			        
			        
					}
				}
				
				TankChoice = "";
				
			
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (stoped == 0)
					return;
				TankChoice=e.getSource().toString().split("Tank")[2].split(".png")[0].toString().substring(1);
				int x_origin_windowGame = (int) MainWindow.getContainer().getLocationOnScreen().getX();
				int y_origin_windowGame = (int) MainWindow.getContainer().getLocationOnScreen().getY();
				int x_end_windowGame = x_origin_windowGame + MainWindow.getContainer().getWidth();
				int y_end_windowGame = y_origin_windowGame + MainWindow.getContainer().getHeight();
				int x_absolute_position = e.getXOnScreen() - x_origin_windowGame;
				int y_absolute_position = e.getYOnScreen() - y_origin_windowGame;
				MainWindow.getPanneauDessin().lastRegisteredMousePosition.x = x_absolute_position;
				MainWindow.getPanneauDessin().lastRegisteredMousePosition.y = y_absolute_position;
			
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        };
        
        
        // Définition des icones de tank dans le menu de selection
        TankBBrun.setIcon(new ImageIcon(GraphicInterface.class.getResource("/ressources/TankBBrun.png")));
        
        JLabel TankBCyan = new JLabel("");
        TankBCyan.setIcon(new ImageIcon(GraphicInterface.class.getResource("/ressources/TankBCyan.png")));
        
        JLabel TankBJaune = new JLabel("");
        TankBJaune.setIcon(new ImageIcon(GraphicInterface.class.getResource("/ressources/TankBJaune.png")));
        
        JLabel TankBRed = new JLabel("");
        TankBRed.setIcon(new ImageIcon(GraphicInterface.class.getResource("/ressources/TankBRed.png")));
        
        JLabel TankBRose = new JLabel("");
        TankBRose.setIcon(new ImageIcon(GraphicInterface.class.getResource("/ressources/TankBRose.png")));
        
        JLabel TankBVert = new JLabel("");
        TankBVert.setIcon(new ImageIcon(GraphicInterface.class.getResource("/ressources/TankBVert.png")));
        
        JLabel TankBViolet = new JLabel("");
        TankBViolet.setIcon(new ImageIcon(GraphicInterface.class.getResource("/ressources/TankBViolet.png")));
        
        // Association de la gestion du drag & drop
       
        TankBBrun.addMouseListener(ms);
        TankBCyan.addMouseListener(ms);
        TankBJaune.addMouseListener(ms);
        TankBRed.addMouseListener(ms);
        TankBRose.addMouseListener(ms);
        TankBVert.addMouseListener(ms);
        TankBViolet.addMouseListener(ms);
        
        TankBBrun.addMouseMotionListener(mms);
        TankBCyan.addMouseMotionListener(mms);
        TankBJaune.addMouseMotionListener(mms);
        TankBRed.addMouseMotionListener(mms);
        TankBRose.addMouseMotionListener(mms);
        TankBVert.addMouseMotionListener(mms);
        TankBViolet.addMouseMotionListener(mms);
       
        
        // Definition des boutons du menu (start et stop)
        final JButton btnNextStep = new JButton("Etape suivante");
        final JButton btnStart = new JButton("Démarrer");
        btnStart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (stoped == 1)
        			stoped = 0; // Demarrage / Redémarrage
        		else if (stoped == 2)
        			return;
        		else
        			stoped = 2;
        		
        		btnNextStep.setVisible(!btnNextStep.isVisible());
        		if(stoped == 1 || stoped == 2)
        		{
        			btnStart.setText("Reprendre");
        			textAreaCode.setEditable(true);
        		}
        		else
        		{
        			checkCodeUpdates();
        			btnStart.setText("Pause");
        			textAreaCode.setEditable(false);
        		}
        		 GraphicInterface.gui.setFocusable(true);
     	       GraphicInterface.gui.requestFocusInWindow();
        	}
        });
             
        btnNextStep.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GraphicInterface.NextStepFlag=true;
        	}
        });
        
        // Organisation du Panel menu
        GroupLayout gl_jPanel6 = new GroupLayout(jPanel6);
        gl_jPanel6.setHorizontalGroup(
        	gl_jPanel6.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel6.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(TankBBrun)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TankBCyan, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TankBJaune, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TankBRed, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TankBRose, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TankBVert, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TankBViolet, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnStart)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnNextStep)
        			.addContainerGap(349, Short.MAX_VALUE))
        );
        gl_jPanel6.setVerticalGroup(
        	gl_jPanel6.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel6.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_jPanel6.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel6.createParallelGroup(Alignment.BASELINE)
        					.addComponent(btnStart)
        					.addComponent(btnNextStep))
        				.addComponent(TankBViolet, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        				.addComponent(TankBVert, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        				.addComponent(TankBRose, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        				.addComponent(TankBRed, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        				.addComponent(TankBJaune, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        				.addComponent(TankBCyan, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        				.addComponent(TankBBrun))
        			.addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel6.setLayout(gl_jPanel6);


        
        final JButton btnDevMode = new JButton("Mode Dev");
        btnDevMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GraphicInterface.devModeActivated == false) {
	        		jSplitPane1.setDividerLocation(1.0d);
	        		GraphicInterface.devModeActivated = true;
	        		btnDevMode.setText("Mode Jeu");
				}
				else {
	        		jSplitPane1.setDividerLocation(351);
	        		GraphicInterface.devModeActivated = false;
	        		btnDevMode.setText("Mode Dev");
				}
        	}
        });

        JButton btnImport = new JButton("Importer");
        
        JButton btnExport = new JButton("Exporter");
        

        textAreaCode = new JTextPane();
        updateListTanksOnMap();
        
        
   
        JScrollPane textAreaCodeScrollPane = new JScrollPane(textAreaCode);
        textAreaCodeScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        textAreaCodeScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        
        //JList<ComponentPlacement> JList_1 = new JList<ComponentPlacement>();
        
        JLabel lblErrors = new JLabel("Sortie");
        lblErrors.setHorizontalAlignment(SwingConstants.LEFT);
        
        textAreaOutput = new JTextArea();
        textAreaOutput.setLineWrap(true);
        textAreaOutput.setWrapStyleWord(true);
        textAreaOutput.setEditable(false);
        
        JScrollPane textAreaOutputScrollPane = new JScrollPane(textAreaOutput);
        textAreaOutputScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);      
        
        JLabel lblHelp = new JLabel("Aide");
        textAreaHelp = new JTextArea(
        		"noAction()\n" + 
        		"moveForward()\n" + 
        		"moveBackward()\n" +
        		"turnClockwise()\n" +
        		"turnCounterClockwise()\n" +
        		"shoot()");
        textAreaHelp.setEditable(false);  
        
        GroupLayout gl_jPanel1 = new GroupLayout(jPanel1);
        gl_jPanel1.setHorizontalGroup(
        	gl_jPanel1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1.createSequentialGroup()
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
        				.addComponent(textAreaCodeScrollPane, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_jPanel1.createSequentialGroup()
        							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblErrors)
        								//.addComponent(textAreaOutput, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
    									.addComponent(textAreaOutputScrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        								//.addComponent(JList_1, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            							.addComponent(textAreaHelp, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        								.addComponent(lblHelp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(gl_jPanel1.createSequentialGroup()
        						.addComponent(btnDevMode)
        						.addComponent(btnImport)
        						.addComponent(btnExport))))
        				//.addComponent(textAreaCode, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
    					.addComponent(textAreaCodeScrollPane, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
        			.addGap(3))
        );
        gl_jPanel1.setVerticalGroup(
        	gl_jPanel1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1.createSequentialGroup()
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnDevMode)
        				.addComponent(btnImport)
        				.addComponent(btnExport))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			//.addComponent(textAreaCode, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        			.addComponent(textAreaCodeScrollPane, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblHelp)
        				.addComponent(lblErrors))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
        				//.addComponent(JList_1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
            			.addComponent(textAreaHelp, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
        				//.addComponent(textAreaOutput, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
            			.addComponent(textAreaOutputScrollPane, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1.setLayout(gl_jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        pack();
        this.setFocusable(true);
        this.requestFocusInWindow();
        
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphicInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               gui =  new GraphicInterface();
               gui.setVisible(true);
                
                
            }
        });
    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		/* Suppression des fichiers temporaires à la fermeture de l'application */
		for (Tank t: MainWindow.getPanneauDessin().getPhysique().getTanks())
		{
			File file = new File("src/ressources/"+t.getIntel().getScript().getTmpFileName());
			file.delete();
		}
		System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		/* Suppression des fichiers temporaires à la fermeture de l'application */
		for (Tank t: MainWindow.getPanneauDessin().getPhysique().getTanks())
		{
			File file = new File("src/ressources/"+t.getIntel().getScript().getTmpFileName());
			file.delete();
		}
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (selectedTank != null && stoped == 1)
		{
			if (arg0.getID() == 402) // Touch suppr relachée
			{
				MainWindow.getPanneauDessin().getPhysique().destroyTank(selectedTank);
				setSelectedTank(null);
				MainWindow.getPanneauDessin().repaint();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		// TODO Auto-generated method stub
		
	}


}
