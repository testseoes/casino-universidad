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
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;

import blackjack.PrincipalBlack;
import uno.PrincipalUno;
import utils.BarajaMesaVacia;

import bd.BaseDatos;

public class CasinoModel {
    //... Constants
	private static final String INITIAL_VALUE = "1";
	private static final int MAX_JUG =8;
	private static final int N_MESAS =4;
    
    //... Member variable defining state of calculator.
    private BigInteger m_total;  // The total current value state.
    private BaseDatos m_bd;
    //private BufferedWriter fout;
    private int numSesion;
    private int [] posiMesa;
    
    private String [][] salon;
    private String [] mesa2;
    private String [] mesa3;
    private String [] mesa4;
	    
    //============================================================== constructor
    /** Constructor 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException */
    public CasinoModel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        reset();
        posiMesa=new int [N_MESAS];
        for (int i=0;i<N_MESAS;i++){
        	posiMesa[i]=0;
        }
        salon= new String [N_MESAS][MAX_JUG];
        mesa2= new String [MAX_JUG];
        mesa3= new String [MAX_JUG];
        mesa4= new String [MAX_JUG];
    	
        
    }
    
    //==================================================================== reset
    /** Reset to initial value. 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException */
    public void reset() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        m_total = new BigInteger(INITIAL_VALUE);
        m_bd=new BaseDatos();
    }
    
    //=============================================================== multiplyBy
    /** Multiply current total by a number.
     *@param operand Number (as string) to multiply total by.
     */
    public void multiplyBy(String operand) {
        m_total = m_total.multiply(new BigInteger(operand));
    }
    public boolean iniciarUsuario(String login,String pass) throws SQLException, IOException {
        boolean correcto=false;
    	if ((m_bd.comprobarLogin(login))&(m_bd.comprobarPass(pass,login))){
    		correcto=true;
    		m_bd.iniciaUnaSesion(login);
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
      			estado=2; //se incluye en la mesa satisfactoriamente
      			salon[mesa][posiMesa[mesa]]=login;
      			posiMesa[mesa]++;
      			
      		}
    	}
    	return estado;
    	
    }
    public int jugar(int mesa, BufferedWriter fout) throws SQLException, IOException, BarajaMesaVacia{
    	int estado=0; //la mesa está vacia 
       	if (posiMesa[mesa]>0){
      		estado=1; //hay al menos una persona; 1 persona en el Uno
      		if ((posiMesa[mesa]>1)||(mesa%2==0)){
      			estado=2; //se puede jugar
      			if (mesa%2==0){//juega al black
      				
      			}
      			else{  //juega al Uno
      				PrincipalBlack.iniciaBlack(salon[mesa].toArray(new String[mesa.size()]),fout,m_bd,1,mesa); //salon[mesa],fout,m_bd,1,mesa);
      			}
      				
      			posiMesa[mesa]=0;
      			
      		}
    	}
    	return estado;
    	
    }
//    public void JuegaUno(ArrayList<String> mesa,int nmesa,BaseDatos bd,BufferedWriter fout) throws IOException, BarajaMesaVacia, SQLException{
//    	PrincipalUno.iniciaUno(mesa.toArray(new String[mesa.size()]),fout,bd,1,nmesa);
//    	
//    }
    
    //================================================================= setValue
    /** Set the total value.
     *@param value New value that should be used for the calculator total.
     */
    public void setValue(String value) {
        m_total = new BigInteger(value);
    }
    
    //================================================================= getValue
    /** Return current calculator total. */
    public String getValue() {
        return m_total.toString();
    }
}