package mvc;



import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import vista.SpringUtilities;

public class JugarView extends javax.swing.JFrame {

	private CasinoModel m_model;
    /** Creates new form CalcView */
    public JugarView() {
        initComponents();
    }
    
    private void initComponents() {
        
    	this.getContentPane().setLayout(new BorderLayout());
    	        
		framecc = new javax.swing.JFrame("Jugar");
		contentPane = new javax.swing.JPanel(new SpringLayout());
		mesa = new javax.swing.JLabel("Mesa",JLabel.TRAILING);
			
		campoMesa= new javax.swing.JTextField(15);
				
		botonJugar =new javax.swing.JButton ("Jugar");
		botonCancelar =new javax.swing.JButton ("Cancelar");
		statusMsg1 = new javax.swing.JLabel("Estado: ");
		statusMsg2 = new javax.swing.JLabel();
		
		
		contentPane.add(mesa);
		mesa.setLabelFor(campoMesa);
		contentPane.add(campoMesa);
		
		contentPane.add(botonJugar);
		contentPane.add(botonCancelar);
		
		contentPane.add(statusMsg1);
		contentPane.add(statusMsg2);
		
		
		SpringUtilities.makeCompactGrid(contentPane,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

		contentPane.setOpaque(true);
		
		framecc.setContentPane(contentPane);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//		framecc.setSize(300,150);
		framecc.pack();
		framecc.setLocation(130,330);
		framecc.setVisible(true);
    }// </editor-fold>//GEN-END:initComponents
            
    private javax.swing.JFrame framecc;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel mesa;
    private javax.swing.JTextField campoMesa;
    
    private javax.swing.JButton botonCancelar; 
    private javax.swing.JButton botonJugar; 
    private javax.swing.JLabel statusMsg1;
    private javax.swing.JLabel statusMsg2;
	
    public String getMesaInput() {
        return campoMesa.getText();
    }
    public void setEstado(String mensajeEstado) {
        statusMsg2.setText(mensajeEstado);
}
    
    public void addJugarListener(ActionListener mal) {
        botonJugar.addActionListener(mal);
    }
    public void addCancelarListener(ActionListener mal) {
        botonCancelar.addActionListener(mal);
    }

	public void cerrar() {
		framecc.dispose();
    	framecc.setVisible(false);		
	}
}
