package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class VentanaIniciarSesion implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frameis = new JFrame("Iniciar Sesion");
		JPanel contentPane = new JPanel(new SpringLayout());
		JLabel loggin = new JLabel("Loggin",JLabel.TRAILING);
		JLabel passw1 = new JLabel("Password",JLabel.TRAILING);
		JTextField campo1= new JTextField(10);
		JPasswordField pass1 = new JPasswordField(10);
		JButton botonIniciar =new JButton ("Iniciar");
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
		

		contentPane.add(botonIniciar);
		contentPane.add(botonCancelar);
		
		contentPane.add(statusMsg1);
		contentPane.add(statusMsg2);
		
//		frameis.c
//		botonCancelar.addActionListener(frameis.setDefaultCloseOperation(operation));
		
		SpringUtilities.makeCompactGrid(contentPane,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

//		contentPane.add(botonCrear,SpringLayout.SOUTH);
		contentPane.setOpaque(true);
		
//		framecc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameis.setContentPane(contentPane);
		
//		framecc.setSize(300,200);
		frameis.pack();
		frameis.setLocation(150,150);
		frameis.setVisible(true);
		



	}
}