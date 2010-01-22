package mvc;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import vista.SpringUtilities;


public class CasinoView extends javax.swing.JFrame {
    private static final String INITIAL_VALUE = "1";
    private CasinoModel m_model;
    /** Creates new form CalcView */
    public CasinoView(String titulo, CasinoModel model) {
        super(titulo);
    	m_model = model;
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        
    	this.getContentPane().setLayout(new BorderLayout());
    	
        
        m_inicioAuto = new javax.swing.JMenuItem("Inicio Autom�tico");
        m_reset = new javax.swing.JMenuItem("Resetear");
        m_salir = new javax.swing.JMenuItem("Salir");
        m_archivo = new javax.swing.JMenu("archivo");
        m_usuario=new javax.swing.JMenu("Ususuario");
        m_partida=new javax.swing.JMenu("Partida");
		m_crearCuenta=new javax.swing.JMenuItem("Crear Cuenta e iniciar sesion");
		m_datos=new javax.swing.JMenuItem("Ver Datos De Usuario");
		m_cerrarSesion=new javax.swing.JMenuItem("Cerrar Sesion");
		m_sentarMesa=new javax.swing.JMenuItem("Sentar en una Mesa");
		m_jugar=new javax.swing.JMenuItem("Jugar");
		m_barra = new javax.swing.JMenuBar();
        
        
		m_archivo.add(m_inicioAuto);
        m_archivo.add(m_reset);
        m_archivo.addSeparator();
        m_archivo.add(m_salir);
        m_barra.add(m_archivo);
        m_usuario.add(m_crearCuenta);
        m_usuario.add(m_datos);
        m_usuario.add(m_cerrarSesion);
        m_usuario.add(m_sentarMesa);
        m_barra.add(m_usuario);
        m_partida.add(m_jugar);
        m_barra.add(m_partida);
        
        this.setJMenuBar(m_barra);
        /*****Status bar*****/
		statusPanel = new javax.swing.JPanel();
		statusPanel.setLayout(new BorderLayout());
		statusMsg1 = new javax.swing.JLabel("Estado: ");
		statusMsg2 = new javax.swing.JLabel();
		statusPanel.add(statusMsg1, BorderLayout.WEST);
		statusPanel.add(statusMsg2, BorderLayout.CENTER);
		//Agrega el panel de satus al sur del contenedor
		this.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		
		
		juegosPane = new javax.swing.JTextPane();
		contentPaneMesas = new javax.swing.JPanel(new SpringLayout());
		
		mesa1 = new javax.swing.JLabel("Mesa  1");
		mesa2 = new javax.swing.JLabel("Mesa  2");
		mesa3 = new javax.swing.JLabel("Mesa  3");
		mesa4 = new javax.swing.JLabel("Mesa  4");
		lista1=new javax.swing.JTextArea();		
		lista2=new javax.swing.JTextArea();		
		lista3=new javax.swing.JTextArea();		
		lista4=new javax.swing.JTextArea();		
		botonJugar1=new javax.swing.JButton("Jugar");		
		botonJugar2=new javax.swing.JButton("Jugar");		
		botonJugar3=new javax.swing.JButton("Jugar");		
		botonJugar4=new javax.swing.JButton("Jugar");		
		

		contentPaneMesas.add(mesa1);
		contentPaneMesas.add(mesa2);
		contentPaneMesas.add(mesa3);
		contentPaneMesas.add(mesa4);
		
		
		contentPaneMesas.add(lista1);
		contentPaneMesas.add(lista2);
		contentPaneMesas.add(lista3);
		contentPaneMesas.add(lista4);
		contentPaneMesas.add(botonJugar1);
		contentPaneMesas.add(botonJugar2);
		contentPaneMesas.add(botonJugar3);
		contentPaneMesas.add(botonJugar4);
		
		SpringUtilities.makeCompactGrid(contentPaneMesas,
                3, 4, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

		juegosPane.setText("Aqu� se ir� mostrando el transcurso de la partida :");
		
		splitPaneIzq = new javax.swing.JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneIzq.setTopComponent(contentPaneMesas);
		splitPaneIzq.setBottomComponent(juegosPane);
		splitPaneIzq.setDividerLocation(100);
		
		scrollPaneLeft = new javax.swing.JScrollPane(splitPaneIzq);

		/*****List*****/
		lista=new javax.swing.JTextArea();
		scrollPaneRight=new javax.swing.JScrollPane(lista);
		
		/*****rightPanel*****/
		rightPanel=new javax.swing.JPanel(new BorderLayout());
		rightPanel.add(scrollPaneRight,BorderLayout.CENTER);
		rightPanel.add(new javax.swing.JLabel("Usuarios Conectados"),BorderLayout.NORTH);

		/*****Split Panel*****/
		//Define un contenedor con division izq-der
		splitPane = new javax.swing.JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setRightComponent(rightPanel);
		splitPane.setLeftComponent(scrollPaneLeft);
		splitPane.setDividerLocation(520);
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		muestraUsuariosBd();
    }// </editor-fold>//GEN-END:initComponents
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    
    private javax.swing.JMenuItem m_inicioAuto;
    private javax.swing.JMenuItem m_reset;
    private javax.swing.JMenuItem m_salir;
    private javax.swing.JMenu m_usuario;
    private javax.swing.JMenuItem m_crearCuenta;
    private javax.swing.JMenuItem m_datos;
    private javax.swing.JMenuItem m_cerrarSesion;
    private javax.swing.JMenuItem m_sentarMesa;
    private javax.swing.JMenu m_archivo;
    private javax.swing.JMenu m_partida;
    private javax.swing.JMenuItem m_jugar;
    
    private javax.swing.JMenuBar m_barra;
    
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel statusMsg1;
    private javax.swing.JLabel statusMsg2;
	
    private javax.swing.JTextPane juegosPane;
    private javax.swing.JScrollPane scrollPane,scrollPaneLeft,scrollPaneRight;
    private javax.swing.JTextArea lista;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JSplitPane splitPaneIzq;
    
    private javax.swing.JPanel contentPaneMesas;
    private javax.swing.JLabel mesa1;
    private javax.swing.JLabel mesa2;
    private javax.swing.JLabel mesa3;
    private javax.swing.JLabel mesa4;
    private javax.swing.JButton botonJugar1;
    private javax.swing.JButton botonJugar2;
    private javax.swing.JButton botonJugar3;
    private javax.swing.JButton botonJugar4;
    private javax.swing.JTextArea lista1;    
    private javax.swing.JTextArea lista2;    
    private javax.swing.JTextArea lista3;    
    private javax.swing.JTextArea lista4;    

    // End of variables declaration//GEN-END:variables


    
    public void addInicioAutoListener(ActionListener mal) {
        m_inicioAuto.addActionListener(mal);
    }
    public void addResetListener(ActionListener mal) {
        m_reset.addActionListener(mal);
    }
    public void addSalirListener(ActionListener mal) {
        m_salir.addActionListener(mal);
    }
    
    public void addCrearCuentaListener(ActionListener mal) {
        m_crearCuenta.addActionListener(mal);
    }
    public void addDatosListener(ActionListener mal) {
        m_datos.addActionListener(mal);
    }
    public void addCerrarSesionListener(ActionListener mal) {
        m_cerrarSesion.addActionListener(mal);
    }
    public void addSentarMesaListener(ActionListener mal) {
        m_sentarMesa.addActionListener(mal);
    }
    public void addJugarListener(ActionListener mal) {
        m_jugar.addActionListener(mal);
    }
    public void addJugarMesa1Listener(ActionListener mal) {
    	botonJugar1.addActionListener(mal);
    }
    public void addJugarMesa2Listener(ActionListener mal) {
    	botonJugar2.addActionListener(mal);
    }
    public void addJugarMesa3Listener(ActionListener mal) {
    	botonJugar3.addActionListener(mal);
    }
    public void addJugarMesa4Listener(ActionListener mal) {
    	botonJugar4.addActionListener(mal);
    }
	

    public void setUsuario(String login) {
		//lista.SetText(login);
		// TODO Auto-generated method stub
		
	}
    public void setEstado(String estado) {
    	statusMsg2.setText(estado);
    }
	private void muestraUsuariosBd(){
		String [] aux=m_model.TodosLogin();
		for (int i=0;i<aux.length;i++) addUsuario(aux[i]);
	}
    public void addUsuario(String login) {
		
		lista.setText(login +'\n' + lista.getText());
	}
	public void addUsuarioMesa(String login, int mesa) {

		switch(mesa){
		case 1:
			lista1.setText(login +'\n' + lista1.getText());
			break;
		case 2:
			lista2.setText(login +'\n' + lista2.getText());
			break;
		case 3:
			lista3.setText(login +'\n' + lista3.getText());
		break;
		case 4:
			lista4.setText(login +'\n' + lista4.getText());
		}
	}
	public void BorraMesa(int mesa) {
		switch(mesa){
		case 1:
			lista1.setText(null);
			break;
		case 2:
			lista2.setText(null);
			break;
		case 3:
			lista3.setText(null);
		break;
		case 4:
			lista4.setText(null);
		}
	}
	

	public void setJuegosPane(String stringOut) {
		// TODO Auto-generated method stub
		juegosPane.setText(stringOut);
		
	}


//    public void addClearListener(ActionListener cal) {
//        m_clearBtn.addActionListener(cal);
//    }
}
