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

import bd.BaseDatos;

public class CasinoModel {
    //... Constants
	private static final int INITIAL_VALUE =1;
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
    private BufferedWriter fout;
	    
    //============================================================== constructor
    /** Constructor 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws IOException */
    public CasinoModel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
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
     * @throws InstantiationException 
     * @throws IOException */
    public void reset() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
//        numSesion = INITIAL_VALUE;
        m_bd=new BaseDatos();
        fout = new BufferedWriter(new FileWriter("mesas.txt"));
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
    		m_bd.iniciaUnaSesion(login);
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
    public int jugar(int mesa) throws IOException, BarajaMesaVacia, SQLException{
    	int res,estado=0; //la mesa está vacia 
    	String [] aux;
    	

//    	BaseDatos bd= new BaseDatos();
    	
//    	PrincipalBlack.iniciaBlack(aux,fout,m_bd,1,1); //salon[mesa],fout,m_bd,1,mesa);
//    	try {
//			PrincipalBlack.iniciaBlack(aux,fout,m_bd,1,1);
//		} catch (BarajaMesaVacia e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} //salon[mesa],fout,m_bd,1,mesa);
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
    	if (posiMesa[mesa]>0){
      		estado=1; //hay al menos una persona; 1 persona en el Uno
      		if ((posiMesa[mesa]>1)||(mesa%2==0)){
      			estado=2; //se puede jugar
      			aux = new String [posiMesa[mesa]];
      			for (int i=0;i<posiMesa[mesa];i++){
      				aux[i]=salon[mesa][i];
      			}
//      			System.out.println(salon[0][0]);
//      			for (int i=0;i<posiMesa[mesa];i++){
//      				aux.add(salon[mesa][i]);
//      			}
      			
//      	    	aux=new String [posiMesa[mesa]];
//      	    	aux=salon[mesa];
      		
      			if (mesa%2==0){//juega al black
//      				String [] array ={"11"};
//      				PrincipalBlack.iniciaBlack(array,fout,bd,1,1); //salon[mesa],fout,m_bd,1,mesa);
      		    	try {
      		    		System.out.println(aux);
      		    		System.out.println(mesa);
      		    		
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
          			System.out.println("Se JUgó");
      			}
      			else{  //juega al Uno
      				try {
      		    		System.out.println(aux);
      		    		System.out.println(mesa);
      		    		
      				PrincipalUno.iniciaUno(aux,fout,m_bd,1,mesa+1);
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
          			System.out.println("Se JUgó");
      			}
      				//PrincipalBlack.iniciaBlack(aux.toArray(new String[posiMesa[mesa]]),fout,m_bd,1,1); //salon[mesa],fout,m_bd,1,mesa);
      				//System.out.println("Se JUgó");
      			
      				
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

	public void Salir() {
		try {
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			m_bd.cierraSesion(numSesion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BDNoHayUsuarios e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
}