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

public class SentarMesaView extends javax.swing.JFrame {
    private static final String INITIAL_VALUE = "1";
    private CasinoModel m_model;
    /** Creates new form CalcView */
    public SentarMesaView() {
        initComponents();
    }
    
    private void initComponents() {
        
    	this.getContentPane().setLayout(new BorderLayout());
    	        
		framecc = new javax.swing.JFrame("Sentar En Una Mesa");
		contentPane = new javax.swing.JPanel(new SpringLayout());
		login = new javax.swing.JLabel("Loggin",JLabel.TRAILING);
		mesa = new javax.swing.JLabel("Mesa",JLabel.TRAILING);
			
		campoLogin= new javax.swing.JTextField(25);
		campoMesa= new javax.swing.JTextField(25);
				
		botonSentar =new javax.swing.JButton ("Sentar");
		botonCancelar =new javax.swing.JButton ("Cancelar");
		statusMsg1 = new javax.swing.JLabel("Estado: ");
		statusMsg2 = new javax.swing.JLabel();
		
		
		contentPane.add(login);
		login.setLabelFor(campoLogin);
		contentPane.add(campoLogin);
		
		contentPane.add(mesa);
		mesa.setLabelFor(campoMesa);
		contentPane.add(campoMesa);
		
		contentPane.add(botonSentar);
		contentPane.add(botonCancelar);
		
		contentPane.add(statusMsg1);
		contentPane.add(statusMsg2);
		
		
		SpringUtilities.makeCompactGrid(contentPane,
                4, 2, //rows, cols
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
    private javax.swing.JLabel login;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JLabel mesa;
    private javax.swing.JTextField campoMesa;
    
    private javax.swing.JButton botonCancelar; 
    private javax.swing.JButton botonSentar; 
    private javax.swing.JLabel statusMsg1;
    private javax.swing.JLabel statusMsg2;
	
    public String getLoginInput() {
        return campoLogin.getText();
    }
    public String getMesaInput() {
        return campoMesa.getText();
    }
    public void setEstado(String mensajeEstado) {
        statusMsg2.setText(mensajeEstado);
}
    
    public void addSentarListener(ActionListener mal) {
        botonSentar.addActionListener(mal);
    }
    public void addCancelarListener(ActionListener mal) {
        botonCancelar.addActionListener(mal);
    }
	public void cerrar() {
		framecc.dispose();
    	framecc.setVisible(false);		
	}
}
