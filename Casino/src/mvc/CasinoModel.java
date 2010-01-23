package mvc;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import blackjack.PrincipalBlack;
import uno.PrincipalUno;
import utils.BDNoHayUsuarios;
import utils.BarajaMesaVacia;
import utils.TipoJugadorBlack;

import bd.BaseDatos;

public class CasinoModel {
    //... Constants
	private static final int INITIAL_VALUE =1;
	private static final int MAX_JUG =8;
	private static final int N_MESAS =4;
    
    //... Member variable defining state of calculator.
	private CasinoView m_view;
    private BaseDatos m_bd;

    private int numSesion;
    private int [] posiMesa;
    
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
        m_bd=new BaseDatos();
//        m_bd.iniciarSesionCasino();
        fout = new BufferedWriter(new FileWriter("mesas.txt"));
        
    }
    public boolean iniciarUsuario(String login,String pass) throws SQLException, IOException {
        boolean correcto=false;
    	if ((m_bd.comprobarLogin(login))&(m_bd.comprobarPass(pass,login))){
    		correcto=true;
//    		m_bd.agregarJugadorSesion(login);
    	
    	}
    	return correcto;
    }
    public boolean crearUsuario(String login,String pass, String nombre,String apellido, int tipoJugadorUno, int tipoJugadorBlack, int plantarse) throws SQLException, IOException {
        boolean correcto=false;
        if (!m_bd.comprobarLogin(login)){
    		m_bd.crearUsuario(login, pass, nombre, apellido,tipoJugadorUno, tipoJugadorBlack, plantarse);
    		
    		correcto=true;
    	}
    	return correcto;
    }
    public int sentarUsuario(String login, int mesa) throws SQLException, IOException{
    	int estado=0; //el jugador no se encuentra 
       	if (m_bd.comprobarLogin(login)){
      		estado=1; //la mesa está completa
      		if (posiMesa[mesa]<MAX_JUG){
      			estado=2; //el usuario ya está insertado en esa mesa
      			if (!UsuarioEnMesa(login,mesa)){
      				estado=3;//se puede insertar
      				salon[mesa][posiMesa[mesa]]=login;
          			posiMesa[mesa]++;
//          			m_bd.agregarJugadorSesion(login);
      			}
      			
      		}
    	}
    	return estado;
    	
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

	public String[] TodosLogin(){
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
		TipoJugadorBlack jugador=null;
			try {
				jugador=m_bd.obtenerTipoJugadorBlack(login);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return(jugador.getTipo());
		}
	public int getCreditosUno (String login) {
		int creditosUno=0;
			try {
				m_bd.recuperarCreditoUno(creditosUno, login);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return(creditosUno);
	}
	public float getCreditosBlack (String login) {
		float creditosBlack=0,invertidos=0;
			try {
				m_bd.creditosBlack(login, invertidos, creditosBlack);
				creditosBlack=creditosBlack-invertidos;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return(creditosBlack);
		
	}
}