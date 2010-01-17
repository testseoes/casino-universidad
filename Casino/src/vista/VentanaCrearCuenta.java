package vista;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;




public class VentanaCrearCuenta implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFrame framecc = new JFrame("Crear Cuenta");
		JPanel contentPane = new JPanel(new SpringLayout());
		JLabel loggin = new JLabel("Loggin",JLabel.TRAILING);
		JLabel passw1 = new JLabel("Password",JLabel.TRAILING);
		JLabel passw2 = new JLabel("Password",JLabel.TRAILING);
		JTextField campo1= new JTextField(10);
		JPasswordField pass1 = new JPasswordField(10);
		JPasswordField pass2 = new JPasswordField(10);
		JButton botonCrear =new JButton ("Crear");
		JButton botonCancelar =new JButton ("Cancelar");
		JLabel statusMsg1 = new JLabel("Estado: ");
		JLabel statusMsg2 = new JLabel();
		
		
		//contentPane.add(tex1,SpringLayout.HEIGHT);
//		contentPane.add(pass1,BorderLayout.NORTH);
//		contentPane.add(pass2,BorderLayout.CENTER);
		contentPane.add(loggin);
		loggin.setLabelFor(campo1);
		contentPane.add(campo1);
		
		contentPane.add(passw1);
		passw1.setLabelFor(pass1);
		contentPane.add(pass1);
		
		contentPane.add(passw2);
		passw2.setLabelFor(pass2);
		contentPane.add(pass2);
		
		contentPane.add(botonCrear);
		contentPane.add(botonCancelar);
		
		contentPane.add(statusMsg1);
		contentPane.add(statusMsg2);
		
		
		SpringUtilities.makeCompactGrid(contentPane,
                5, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

//		contentPane.add(botonCrear,SpringLayout.SOUTH);
		contentPane.setOpaque(true);
		
//		framecc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framecc.setContentPane(contentPane);
		
//		framecc.setSize(300,200);
		framecc.pack();
		framecc.setLocation(150,150);
		framecc.setVisible(true);
;

	}

}
