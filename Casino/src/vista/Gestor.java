package vista;

import java.awt.*;

import javax.swing.*;

public class Gestor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3980781781700819707L;
	JFrame frame;
	JPanel contentPane;
	JMenuBar barraMenu;
	JMenu menuArchivo;
	static JMenuItem inicioAuto;
	JMenuItem reiniciar;
	static JMenuItem salir;
	JMenu menuUsuario;
	JMenuItem crearCuenta;
	JMenuItem iniciarSesion;
	JMenuItem cerrarSesion;
	JMenuItem jugar;
	JMenu menuVer;
	JMenuItem verUsuario;
	JMenuItem verPartida;
	JMenuItem verMesa;	
	
	JPanel statusPanel;
	JLabel statusMsg1;
	JLabel statusMsg2;
	
	JTextPane juegosPane;
	JScrollPane scrollPane,scrollPaneLeft,scrollPaneRight;
	JList list;
	JPanel rightPanel;
	JSplitPane splitPane;
	
	
	public Gestor(String titulo){
		super(titulo);
	}
	void Inicializar(){
		
		this.getContentPane().setLayout(new BorderLayout());
		
		frame = new JFrame();
		contentPane = new JPanel(new BorderLayout());
		barraMenu=new JMenuBar();
		menuArchivo=new JMenu("Archivo");
		inicioAuto=new JMenuItem("Inicio Automático");
		reiniciar=new JMenuItem("Reiniciar");
		salir=new JMenuItem("Salir");
		menuUsuario=new JMenu("Ususuario");
		crearCuenta=new JMenuItem("Crear Cuenta");
		iniciarSesion=new JMenuItem("Iniciar Sesion");
		cerrarSesion=new JMenuItem("Cerrar Sesion");
		jugar=new JMenuItem("Jugar");
		menuVer=new JMenu("Ver");
		verUsuario=new JMenuItem("Ver Usuario");
		verPartida=new JMenuItem("Ver Partida");
		verMesa=new JMenuItem("Ver Mesa");
		
		menuArchivo.add(inicioAuto);
		menuArchivo.add(reiniciar);
		menuArchivo.addSeparator();
		menuArchivo.add(salir);
		menuUsuario.add(crearCuenta);
		menuUsuario.add(iniciarSesion);
		menuUsuario.add(cerrarSesion);
		menuUsuario.add(jugar);
		menuVer.add(verUsuario);
		menuVer.add(verPartida);
		menuVer.add(verMesa);
		
		inicioAuto.addActionListener(new ControladorMenu());
		salir.addActionListener(new ControladorMenu());
		crearCuenta.addActionListener(new VentanaCrearCuenta());
		iniciarSesion.addActionListener(new VentanaIniciarSesion());
		cerrarSesion.addActionListener(new VentanaCerrarSesion());
		
		barraMenu.add(menuArchivo);
		barraMenu.add(menuUsuario);
		barraMenu.add(menuVer);
		
		this.setJMenuBar(barraMenu);
		
		/*****Status bar*****/
		statusPanel = new JPanel();
		statusPanel.setLayout(new BorderLayout());
		statusMsg1 = new JLabel("Estado: ");
		statusMsg2 = new JLabel();
		statusPanel.add(statusMsg1, BorderLayout.WEST);
		statusPanel.add(statusMsg2, BorderLayout.CENTER);
		//Agrega el panel de satus al sur del contenedor
		this.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		
		
		juegosPane = new JTextPane();
		juegosPane.setText("resultados Partida 1 en mesa 1 :");
		scrollPaneLeft = new JScrollPane(juegosPane);

		/*****List*****/
		list=new JList();
		scrollPaneRight=new JScrollPane(list);
		
		/*****leftPanel*****/
		rightPanel=new JPanel(new BorderLayout());
		rightPanel.add(scrollPaneRight,BorderLayout.CENTER);
		rightPanel.add(new JLabel("Usuarios Conectados"),BorderLayout.NORTH);

		/*****Split Panel*****/
		//Define un contenedor con division izq-der
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setRightComponent(rightPanel);
		splitPane.setLeftComponent(scrollPaneLeft);
		splitPane.setDividerLocation(400);
		this.getContentPane().add(splitPane, BorderLayout.CENTER);

	}
	public static void main(String[] args) {
		Gestor g=new Gestor("Gestor Casino");
		g.Inicializar();
		g.setSize(700,600);
		g.setLocation(100,100);
		g.setVisible(true);
		
	}

}
