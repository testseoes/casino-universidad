package mvc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import utils.BarajaMesaVacia;

import bd.BaseDatos;
import blackjack.PrincipalBlack;

public class prueba {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws BarajaMesaVacia 
	 */
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException, BarajaMesaVacia {
		// TODO Auto-generated method stub
		BaseDatos bd= new BaseDatos();
		BufferedWriter fout = new BufferedWriter(new FileWriter("mesas.txt"));//fichero de out
		String [] array ={"11"};
		PrincipalBlack.iniciaBlack(array,fout,bd,1,1); //salon[mesa],fout,m_bd,1,mesa);
  		System.out.println(fout.toString());
	}

}
