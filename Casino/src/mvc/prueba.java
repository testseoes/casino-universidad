package mvc;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
		FileReader fr=new FileReader("mesas.txt");
		String [] array ={"11"};
		PrincipalBlack.iniciaBlack(array,fout,bd,1,1); //salon[mesa],fout,m_bd,1,mesa);
  		System.out.println(fout.toString());
  		fout.close();
        Reader r = null;
        try {
            String str;
            r = new FileReader("mesas.txt");
            StringBuffer sb = new StringBuffer();
            char[] b = new char[1024];
            int n;
            while ((n = r.read(b)) > 0) {
                sb.append(b, 0, n);
            }
            str = sb.toString();
            System.out.println(str);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                r.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }


}
