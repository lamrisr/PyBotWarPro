/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinktank.javabot.graphics;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Haroun
 */
public class GraphicInterface extends javax.swing.JFrame {

    /**
     * Creates new form MainGameWindow
     */
	private static JFileChooser chooser = new JFileChooser();
	public static boolean stoped=true;
	public static boolean NextStepFlag=false;
    public GraphicInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
        jLabel1 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new JPanel();

        jPanel1 = new javax.swing.JPanel();
        
        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);


        jSplitPane3.setTopComponent(jPanel5);
        
        jPanel5.setPreferredSize(new Dimension(720, 520));
        jPanel5.setMinimumSize(new Dimension(720, 520));
        jPanel5.setMaximumSize(new Dimension(720, 520));
        jPanel5.setLayout(null);
        jPanel6.setPreferredSize(new Dimension(720, 50));
        jPanel6.setMinimumSize(new Dimension(720, 50));
        jPanel6.setMaximumSize(new Dimension(720, 50));
        jPanel1.setPreferredSize(new Dimension(180, 0));
        jPanel1.setMinimumSize(new Dimension(180, 0));
        jPanel1.setMaximumSize(new Dimension(180, 0));
        jSplitPane1.getLeftComponent().setMinimumSize(new Dimension());
    	//pane.setDividerLocation(0.0d);
        jSplitPane1.getRightComponent().setMinimumSize(new Dimension());
    	//pane.setDividerLocation(1.0d);


        jSplitPane3.setRightComponent(jPanel6);
        
        final JLabel TankBBrun = new JLabel("");
        MouseListener ms=new MouseListener(){
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				//e.getComponent().getLocationOnScreen().getY()
				System.out.println("Psition selectionnée: "+(e.getX())/28+","+((e.getY()+536)/20));
				int xp=(e.getX())/28;
				int yp=((e.getY()+536)/20);
				int returnVal = chooser.showOpenDialog(null);

		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		           System.out.println("You chose to open this file: " +
		                chooser.getSelectedFile().getName());
		       
		        }
		        
		        
		        MainWindow.window.phy.addTank(xp, yp,"ressources/"+chooser.getSelectedFile().getName());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
        
        TankBBrun.addMouseListener(ms);
        TankBCyan.addMouseListener(ms);
        TankBJaune.addMouseListener(ms);
        TankBRed.addMouseListener(ms);
        TankBRose.addMouseListener(ms);
        TankBVert.addMouseListener(ms);
        TankBViolet.addMouseListener(ms);
        
        final JButton btnNextStep = new JButton("Next Step");
        final JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		stoped=!stoped;
        		btnNextStep.setVisible(!btnNextStep.isVisible());
        		if(stoped)
        			btnStart.setText("Start");
        		else
        			btnStart.setText("Stop");
        		
        	}
        });
        
        
        btnNextStep.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GraphicInterface.NextStepFlag=true;
        	}
        });
        
        
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

        jSplitPane1.setRightComponent(jSplitPane3);

        jSplitPane1.setLeftComponent(jPanel1);
        
        JButton btnDevMode = new JButton("Developer Mode");
        btnDevMode.addActionListener(new ActionListener() {
        	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
        		jSplitPane1.setDividerLocation(1.0d);
        	}
        });
        
        JTextArea textArea = new JTextArea();
        
        JList JList_1 = new JList();
        
        JLabel lblErrors = new JLabel("Errors");
        lblErrors.setHorizontalAlignment(SwingConstants.LEFT);
        
        JTextArea textArea_2 = new JTextArea();
        
        JLabel lblHelp = new JLabel("Help");
        GroupLayout gl_jPanel1 = new GroupLayout(jPanel1);
        gl_jPanel1.setHorizontalGroup(
        	gl_jPanel1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1.createSequentialGroup()
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_jPanel1.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_jPanel1.createSequentialGroup()
        							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblErrors)
        								.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        								.addComponent(JList_1, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        								.addComponent(lblHelp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
        						.addComponent(btnDevMode)))
        				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
        			.addGap(3))
        );
        gl_jPanel1.setVerticalGroup(
        	gl_jPanel1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel1.createSequentialGroup()
        			.addComponent(btnDevMode)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblHelp)
        				.addComponent(lblErrors))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
        				.addComponent(JList_1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
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
                new GraphicInterface().setVisible(true);
                
            }
        });
    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
}
