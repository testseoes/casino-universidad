package modelo;
/*
 * CalcModel.java
 *
 * Created on 9 de noviembre de 2007, 12:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



// structure/calc-mvc/CalcModel.java
// Fred Swartz - December 2004
// Model
//     This model is completely independent of the user interface.
//     It could as easily be used by a command line or web interface.

//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import blackjack.PrincipalBlack;
import uno.PrincipalUno;
import utils.BDNoHayUsuarios;
import utils.BarajaMesaVacia;
import vista.CasinoView;

import bd.BaseDatos;

public class CasinoModel {
    //... Constants
	private static final int MAX_JUG =8;
	private static final int N_MESAS =4;
	private static final int MAX_CONECTADOS =40;
    
    //... Member variable defining state of calculator.
	private CasinoView m_view;
    private BaseDatos m_bd;

    private int numSesion;
    private int numConectados;
    private int [] posiMesa;
    private String [] conectados;
    
    private String [][] salon;
    private BufferedWriter fout;
	    
    //============================================================== constructor
    /** Constructor 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws IOException */
    public CasinoModel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
        posiMesa=new int [N_MESAS];
        for (int i=0;i<N_MESAS;i++){
        	posiMesa[i]=0;
        }
        salon= new String [N_MESAS][MAX_JUG];
        conectados = new String [MAX_CONECTADOS];
        numConectados=0;
        m_bd=new BaseDatos();
       
        try {
			numSesion=m_bd.iniciaSesionCasino();
			
			
		} catch (BDNoHayUsuarios e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        fout = new BufferedWriter(new FileWriter("mesas.txt"));
        
    }
    public int iniciarSesion(String login) throws SQLException {
        int estado=0;//ya ha iniciado sesion
    	if (!UsuarioIniciado(login)){
    			estado=1;//hay demasiados usuarios conectados
    		if (numConectados <MAX_CONECTADOS){
				estado=2; // conectado con Exito
				conectados[numConectados]=login;
				numConectados++;	
			    if (!m_bd.comprobarLoginSesion(login, numSesion))
			    	m_bd.insertaJugadorSesion(login,numSesion);
    		}
    	} 
    	return estado;
    }

    public String [] GetConectados(){
    	return conectados;
    }
    public boolean crearUsuario(String login,String pass, String nombre,String apellido, int tipoJugadorUno, int tipoJugadorBlack, int plantarse) throws SQLException, IOException {
        boolean correcto=false;
        if (!m_bd.comprobarLogin(login)){
    		m_bd.crearUsuario(login, pass, nombre, apellido,tipoJugadorUno, tipoJugadorBlack, plantarse);
  			m_bd.insertaJugadorSesion(login,numSesion);
  			conectados[numConectados]=login;
  			numConectados++;	
  			correcto=true;
    	}
    	return correcto;
    }
    public int sentarUsuario(String login, int mesa) throws SQLException, IOException{
    	int estado=0; //el jugador no se encuentra 
       	if (m_bd.comprobarLogin(login)){
      		estado=1; //el jugador no ha inicado sesion
      		if (UsuarioIniciado(login)){
      			estado=2;// la mesa está completa
      			if (posiMesa[mesa]<MAX_JUG){
      				estado=3;  //el usuario ya está insertado en esa mesa
      				if (!UsuarioEnMesa(login,mesa)){
      					estado=4;//se puede insertar
      					salon[mesa][posiMesa[mesa]]=login;
      					posiMesa[mesa]++;
      				}
      			
      			}
      		}	
    	}
    	return estado;
    	
    }
    private boolean UsuarioIniciado(String login){
    	boolean iniciado=false;
    	for (int i=0; i<numConectados;i++){
    		if (login.equals(conectados[i])) iniciado=true;
    	}
    	return iniciado;
    
    }
    private boolean UsuarioEnMesa(String login, int mesa) {
		boolean encontrado=false;
		for (int i=0;i<posiMesa[mesa];i++){
			if (login.equals(salon[mesa][i])) encontrado=true;
		}
    	// TODO Auto-generated method stub
		return encontrado;
	}

	public int jugar(int mesa, BufferedWriter fOut) throws IOException, BarajaMesaVacia, SQLException{
    	int res,estado=0; //la mesa está vacia 
    	String [] aux;
    	fout = new BufferedWriter(new FileWriter("mesas.txt"));
    	if (posiMesa[mesa]>0){
      		estado=1; //hay al menos una persona; 1 persona en el Uno
      		if ((posiMesa[mesa]>1)||(mesa%2==0)){
      			estado=2; //se puede jugar
      			aux = new String [posiMesa[mesa]];
      			for (int i=0;i<posiMesa[mesa];i++){
      				aux[i]=salon[mesa][i];
      			}
      			if (mesa%2==0){//juega al black
      		    	try {
      		    		PrincipalBlack.iniciaBlack(aux,fout,m_bd,1,mesa+1);
      			} catch (BarajaMesaVacia e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			} //salon[mesa],fout,m_bd,1,mesa);
      			catch (IOException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			} catch (SQLException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}	
          			
      			}
      			else{  //juega al Uno
      				try {
      					PrincipalUno.iniciaUno(aux,fout,m_bd,1,mesa+1);
      			} 
      				
      			catch (BarajaMesaVacia e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			} //salon[mesa],fout,m_bd,1,mesa);
      			catch (IOException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			} catch (SQLException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}	
          			}
      			fout.close();	
      			posiMesa[mesa]=0;
      			
      		}
    	}
    	return estado;
    	
    }

	public String[] getTodosLogin(){
		String[] aux=null;
		try {
			aux=m_bd.extraerTodosLogin();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}
	public void Salir() {
		try {
			m_bd.cierraSesion(numSesion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BDNoHayUsuarios e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean comprobarPass(String login, String pass) {
		// TODO Auto-generated method stub
		boolean coincide=false;
		try {
			coincide= m_bd.comprobarPass(login, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coincide;
		
	}
	public void eliminarUsuario(String login) {
		
		try {
			m_bd.eliminarUnUsuario(login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getTipoUno (String login) {
		
		int tipoUno=0;
		try {
			tipoUno=m_bd.obtenerTipoJugadorUno(login);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return(tipoUno);
		
	}
	public int getTipoBlack (String login) {
		blackjack.TipoJugadorBlack jugador=null;
			try {
				jugador=m_bd.obtenerTipoJugadorBlack(login);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return(jugador.getTipo());
		}
	public int getCreditosInvertidos (String login) throws SQLException {
		return m_bd.obtenerInvertidoTotal(login);
	}
	public int getCreditosRecuperados (String login) throws SQLException {
		return m_bd.obtenerRecuperadoTotal(login);
	}
	public boolean cerrarSesion(String login) {
		boolean encontrado=false;
		// TODO Auto-generated method stub
		int i=0;
		while ((!encontrado)&&(i<numConectados)){
    		if (login.equals(conectados[i])){
    			encontrado=true;
    			conectados[i]=conectados[numConectados-1];
    			conectados[numConectados-1]=null;
    		}
    		i++;
    	}
    		
		return encontrado;
	}

}