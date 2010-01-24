package vista;




import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import modelo.CasinoModel;

public class CrearCuentaView extends javax.swing.JFrame {
    private CasinoModel m_model;
    /** Creates new form CalcView */
    public CrearCuentaView() {
        initComponents();
    }
    
    private void initComponents() {
        
    	this.getContentPane().setLayout(new BorderLayout());
    	        
		framecc = new javax.swing.JFrame("Crear Cuenta");
		contentPane = new javax.swing.JPanel(new SpringLayout());
		loggin = new javax.swing.JLabel("Loggin",JLabel.TRAILING);
		passw1 = new javax.swing.JLabel("Password",JLabel.TRAILING);
		passw2 = new javax.swing.JLabel("Password",JLabel.TRAILING);
		nombre = new javax.swing.JLabel("Nombre",JLabel.TRAILING);
		apellido = new javax.swing.JLabel("Apellido",JLabel.TRAILING);
		tipoJugadorUno = new javax.swing.JLabel("Tipo de Jugador Uno",JLabel.TRAILING);
		tipoJugadorBlack = new javax.swing.JLabel("Tipo de Jugador BlackJack",JLabel.TRAILING);
		plantarse = new javax.swing.JLabel("Puntuación plantarse BlackJack",JLabel.TRAILING);
	
		campo1= new javax.swing.JTextField(25);
		pass1 = new javax.swing.JPasswordField(25);
		pass2 = new javax.swing.JPasswordField(25);
		campoNombre= new javax.swing.JTextField(25);
		campoApellido= new javax.swing.JTextField(25);
		campoTipoJugadorUno= new javax.swing.JTextField(25);
		campoTipoJugadorUno.setText("1=Numero; 2=Color; 3=Especial");
		campoTipoJugadorBlack= new javax.swing.JTextField(28);
		campoTipoJugadorBlack.setText("1=dobla y separa; 2=separa; 3=dobla; 4=no hace nada");
		campoPlantarse= new javax.swing.JTextField(25);
		campoPlantarse.setText("número entre 1 y 21");
		
		botonCrear =new javax.swing.JButton ("Crear");
		botonCancelar =new javax.swing.JButton ("Cancelar");
		statusMsg1 = new javax.swing.JLabel("Estado: ");
		statusMsg2 = new javax.swing.JLabel();

		
		contentPane.add(loggin);
		loggin.setLabelFor(campo1);
		contentPane.add(campo1);
		
		contentPane.add(passw1);
		passw1.setLabelFor(pass1);
		contentPane.add(pass1);
		
		contentPane.add(passw2);
		passw2.setLabelFor(pass2);
		contentPane.add(pass2);
		
		contentPane.add(nombre);
		nombre.setLabelFor(campoNombre);
		contentPane.add(campoNombre);
		
		contentPane.add(apellido);
		apellido.setLabelFor(campoApellido);
		contentPane.add(campoApellido);
		
		contentPane.add(tipoJugadorUno);
		tipoJugadorUno.setLabelFor(campoTipoJugadorUno);
		contentPane.add(campoTipoJugadorUno);
		
		contentPane.add(tipoJugadorBlack);
		tipoJugadorBlack.setLabelFor(campoTipoJugadorBlack);
		contentPane.add(campoTipoJugadorBlack);
		
		contentPane.add(plantarse);
		plantarse.setLabelFor(campoPlantarse);
		contentPane.add(campoPlantarse);
		
		contentPane.add(botonCrear);
		contentPane.add(botonCancelar);
		
		contentPane.add(statusMsg1);
		contentPane.add(statusMsg2);

		
		SpringUtilities.makeCompactGrid(contentPane,
                10, 2, //rows, cols
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
    private javax.swing.JLabel loggin;
    private javax.swing.JLabel passw1;
    private javax.swing.JLabel passw2;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel apellido;
    private javax.swing.JLabel tipoJugadorUno;
    private javax.swing.JLabel tipoJugadorBlack;
    private javax.swing.JLabel plantarse;
    private javax.swing.JTextField campo1;
    private javax.swing.JPasswordField pass1;
    private javax.swing.JPasswordField pass2;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoApellido;
    private javax.swing.JTextField campoTipoJugadorUno;
    private javax.swing.JTextField campoTipoJugadorBlack;
    private javax.swing.JTextField campoPlantarse;
    
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton botonCancelar; 
    private javax.swing.JLabel statusMsg1;
    private javax.swing.JLabel statusMsg2;

	
    public String getUserInput() {
        return campo1.getText();
    }
    public String getPass1Input() {
        return pass1.getText();
    }
    public String getPass2Input() {
        return pass2.getText();
    }
    public String getNombreInput() {
        return campoNombre.getText();
    }
    public String getApellidoInput() {
        return campoApellido.getText();
    }
    public String getTipoJugadorUnoInput() {
        return campoTipoJugadorUno.getText();
    }
    public String getTipoJugadorBlackInput() {
        return campoTipoJugadorBlack.getText();
    }
//    public void addTipoJugadorBlackListener(ContainerListener ac) {
//        campoTipoJugadorBlack.addContainerListener(ac);
//    }

    public String getPlantarseInput() {
        return campoPlantarse.getText();
    }

    public void setEstado(String mensajeEstado) {
        statusMsg2.setText(mensajeEstado);
}
    
    public void addCrearListener(ActionListener mal) {
        botonCrear.addActionListener(mal);
    }
    public void addCancelarListener(ActionListener mal) {
        botonCancelar.addActionListener(mal);
    }
	public void cerrar() {
		framecc.dispose();
    	framecc.setVisible(false);		
	}
}
