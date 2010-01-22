package mvc;


import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import vista.SpringUtilities;

public class UsuarioView extends javax.swing.JFrame {
    private static final String INITIAL_VALUE = "1";
    private CasinoModel m_model;
    /** Creates new form CalcView */
    public UsuarioView() {
        initComponents();
    }
    
    private void initComponents() {
        
    	this.getContentPane().setLayout(new BorderLayout());
    	        
		framecc = new javax.swing.JFrame("Datos de Usuario");
		contentPane = new javax.swing.JPanel(new SpringLayout());
		
		mesa1 = new javax.swing.JLabel("Mesa1");
		mesa2 = new javax.swing.JLabel("Mesa2");
		lista1=new javax.swing.JTextArea();		
		lista2=new javax.swing.JTextArea();		
		botonJugar1=new javax.swing.JButton("Jugar");		
		botonJugar2=new javax.swing.JButton("Jugar");		
		

		contentPane.add(mesa1);
		contentPane.add(mesa2);
		
		
		contentPane.add(lista1);
		contentPane.add(lista2);
		contentPane.add(botonJugar1);
		contentPane.add(botonJugar2);
		
		SpringUtilities.makeCompactGrid(contentPane,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

		contentPane.setOpaque(true);
		
		framecc.setContentPane(contentPane);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//		framecc.setSize(300,400);
		framecc.pack();
		framecc.setLocation(150,150);
		framecc.setVisible(true);
    }// </editor-fold>//GEN-END:initComponents
            
    private javax.swing.JFrame framecc;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel mesa1;
    private javax.swing.JLabel mesa2;
    private javax.swing.JButton botonJugar1;
    private javax.swing.JButton botonJugar2;
    private javax.swing.JTextArea lista1;    
    private javax.swing.JTextArea lista2;    
	
//    public String getLoginInput() {
//        return campoLogin.getText();
//    }
//    public String getMesaInput() {
//        return campoMesa.getText();
//    }
//    public void setEstado(String mensajeEstado) {
//        statusMsg2.setText(mensajeEstado);
//}
//    
//    public void addSentarListener(ActionListener mal) {
//        botonSentar.addActionListener(mal);
//    }
//    public void addCancelarListener(ActionListener mal) {
//        botonCancelar.addActionListener(mal);
//    }
	public void cerrar() {
		framecc.dispose();
    	framecc.setVisible(false);		
	}
}
