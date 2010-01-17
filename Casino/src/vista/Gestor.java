package vista;

import java.awt.*;

import javax.swing.*;

public class Gestor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3980781781700819707L;
	JFrame frame;
	//JPanel contentPane;
	JMenuBar barraMenu;
	JMenu menuArchivo;
	JMenuItem inicioAuto;
	JMenuItem reiniciar;
	JMenuItem salir;
	JMenu menuUsuario;
	JMenuItem crearCuenta;
	JMenuItem iniciarSesion;
	JMenuItem cerrarSesion;
	JMenu menuVer;
	JMenuItem verUsuario;
	JMenuItem verPartida;
	JMenuItem verMesa;	
	
	
	public Gestor(String titulo){
		super(titulo);
	}
	void Inicializar(){
		
		this.getContentPane().setLayout(new BorderLayout());
		
		frame = new JFrame();
		//contentPane = new JPanel(new BorderLayout());
		barraMenu=new JMenuBar();
		menuArchivo=new JMenu("Archivo");
		inicioAuto=new JMenuItem("Inicio Automático");
		reiniciar=new JMenuItem("Reiniciar");
		salir=new JMenuItem("Salir");
		menuUsuario=new JMenu("Ususuario");
		crearCuenta=new JMenuItem("Crear Cuenta");
		iniciarSesion=new JMenuItem("Iniciar Sesion");
		cerrarSesion=new JMenuItem("Cerrar Sesion");
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
		menuVer.add(verUsuario);
		menuVer.add(verPartida);
		menuVer.add(verMesa);
		
		inicioAuto.addActionListener(new ControladorInicioAuto());
		salir.addActionListener(new ControladorSalir());
		crearCuenta.addActionListener(new VentanaCrearCuenta());
		
		barraMenu.add(menuArchivo);
		barraMenu.add(menuUsuario);
		barraMenu.add(menuVer);
		
		this.setJMenuBar(barraMenu);
		
		
	}
	public static void main(String[] args) {
		Gestor g=new Gestor("Gestor Casino");
		g.Inicializar();
		g.setSize(500,400);
		g.setLocation(100,100);
		g.setVisible(true);
		
	}

}
