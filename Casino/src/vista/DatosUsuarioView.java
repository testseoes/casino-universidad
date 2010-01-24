package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import modelo.CasinoModel;


public class DatosUsuarioView extends javax.swing.JFrame {

	private CasinoModel m_model;
    /** Creates new form CalcView */
    public DatosUsuarioView() {
        initComponents();
    }
    
    private void initComponents() {
        
    	this.getContentPane().setLayout(new BorderLayout());
    	        
		framecc = new javax.swing.JFrame("Ver Datos de Usuario");
		contentPane = new javax.swing.JPanel(new SpringLayout());
		login = new javax.swing.JLabel("Loggin",JLabel.TRAILING);
		pass = new javax.swing.JLabel("Password",JLabel.TRAILING);
		tipoUno = new javax.swing.JLabel("Tipo de Jugador Uno :",JLabel.TRAILING);
		creditosUno = new javax.swing.JLabel("Créditos Invertidos :",JLabel.TRAILING);
		tipoBlack = new javax.swing.JLabel("Tipo de Jugador Black :",JLabel.TRAILING);
		creditosBlack = new javax.swing.JLabel("Créditos Recuperados :",JLabel.TRAILING);
			
		campoLogin = new javax.swing.JTextField(30);
		campoPass = new javax.swing.JPasswordField(30);
		campoTipoUno = new javax.swing.JLabel();
		campoCreditosUno = new javax.swing.JLabel();
		campoTipoBlack = new javax.swing.JLabel();
		campoCreditosBlack = new javax.swing.JLabel();
				
		botonDatos =new javax.swing.JButton ("Ver Datos");
		botonCancelar =new javax.swing.JButton ("Cancelar");
		
		statusMsg1 = new javax.swing.JLabel("Estado: ");
		statusMsg2 = new javax.swing.JLabel();
		
		
		contentPane.add(login);
		login.setLabelFor(campoLogin);
		contentPane.add(campoLogin);
		
		contentPane.add(pass);
		pass.setLabelFor(campoPass);
		contentPane.add(campoPass);
		
		contentPane.add(tipoUno);
		tipoUno.setLabelFor(campoTipoUno);
		contentPane.add(campoTipoUno);
		
		contentPane.add(tipoBlack);
		tipoBlack.setLabelFor(campoTipoBlack);
		contentPane.add(campoTipoBlack);
		
		contentPane.add(creditosUno);
		creditosUno.setLabelFor(campoCreditosUno);
		contentPane.add(campoCreditosUno);
		
		contentPane.add(creditosBlack);
		creditosBlack.setLabelFor(campoCreditosBlack);
		contentPane.add(campoCreditosBlack);
		
		contentPane.add(botonDatos);
		contentPane.add(botonCancelar);
		
		contentPane.add(statusMsg1);
		contentPane.add(statusMsg2);
		
		
		SpringUtilities.makeCompactGrid(contentPane,
                8, 2, //rows, cols
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
    private javax.swing.JLabel tipoUno;
    private javax.swing.JLabel campoTipoUno;
    private javax.swing.JLabel tipoBlack;
    private javax.swing.JLabel campoTipoBlack;
    private javax.swing.JLabel creditosUno;
    private javax.swing.JLabel creditosBlack;
    private javax.swing.JLabel campoCreditosUno;
    private javax.swing.JLabel campoCreditosBlack;
    
    
    private javax.swing.JButton botonCancelar; 
    private javax.swing.JButton botonDatos; 
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
    public void setTipoBlack(String mensajeEstado) {
        campoTipoBlack.setText(mensajeEstado + " (1=dobla y separa; 2=separa; 3=dobla; 4=no hace nada)");
    }
    
    public void setCreditosUno(String mensajeEstado) {
        campoCreditosUno.setText(mensajeEstado);
    }
    
    public void setTipoUno(String mensajeEstado) {
        campoTipoUno.setText(mensajeEstado + " (1=Numero; 2=Color; 3=Especial)");
    }
    
    public void setCreditosBlack(String mensajeEstado) {
        campoCreditosBlack.setText(mensajeEstado);
    }
    
    public void addDatosListener(ActionListener mal) {
        botonDatos.addActionListener(mal);
    }
    public void addCancelarListener(ActionListener mal) {
        botonCancelar.addActionListener(mal);
    }
	public void cerrar() {
		framecc.dispose();
    	framecc.setVisible(false);		
	}
}
