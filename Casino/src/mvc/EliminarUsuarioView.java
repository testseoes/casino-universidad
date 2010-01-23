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

public class EliminarUsuarioView extends javax.swing.JFrame {

	private CasinoModel m_model;
    /** Creates new form CalcView */
    public EliminarUsuarioView() {
        initComponents();
    }
    
    private void initComponents() {
        
    	this.getContentPane().setLayout(new BorderLayout());
    	        
		framecc = new javax.swing.JFrame("Eliminar Cuenta de Usuario");
		contentPane = new javax.swing.JPanel(new SpringLayout());
		login = new javax.swing.JLabel("Loggin",JLabel.TRAILING);
		pass = new javax.swing.JLabel("Password",JLabel.TRAILING);
			
		campoLogin = new javax.swing.JTextField(30);
		campoPass = new javax.swing.JPasswordField(30);
				
		botonEliminar =new javax.swing.JButton ("Eliminar Cuenta");
		botonCancelar =new javax.swing.JButton ("Cancelar");
		
		statusMsg1 = new javax.swing.JLabel("Estado: ");
		statusMsg2 = new javax.swing.JLabel();
		
		
		contentPane.add(login);
		login.setLabelFor(campoLogin);
		contentPane.add(campoLogin);
		
		contentPane.add(pass);
		pass.setLabelFor(campoPass);
		contentPane.add(campoPass);
		
		contentPane.add(botonEliminar);
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
		framecc.setLocation(130,330);
		framecc.setVisible(true);
    }// </editor-fold>//GEN-END:initComponents
            
    private javax.swing.JFrame framecc;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel login;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JLabel pass;
    private javax.swing.JPasswordField campoPass;
    
    private javax.swing.JButton botonCancelar; 
    private javax.swing.JButton botonEliminar; 
    private javax.swing.JLabel statusMsg1;
    private javax.swing.JLabel statusMsg2;
	
    public String getLoginInput() {
        return campoLogin.getText();
    }
    public String getPassInput() {
        return campoPass.getText();
    }
    public void setEstado(String mensajeEstado) {
        statusMsg2.setText(mensajeEstado);
}
    
    public void addEliminarListener(ActionListener mal) {
        botonEliminar.addActionListener(mal);
    }
    public void addCancelarListener(ActionListener mal) {
        botonCancelar.addActionListener(mal);
    }
	public void cerrar() {
		framecc.dispose();
    	framecc.setVisible(false);		
	}
}
