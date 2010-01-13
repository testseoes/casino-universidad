package Casino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import bd.BaseDatos;
import uno.PrincipalUno;
import utils.BDNoHayUsuarios;
import utils.BarajaMesaVacia;

public class Casino {
	final static int npartidas=30; //en cada mesa se jugarán este nº de partidas.
	
	public static void main(String[] args) throws IOException,SQLException, BDNoHayUsuarios {
		BufferedReader entrada = new BufferedReader (new InputStreamReader(System.in)); //entrada x pantalla
		BufferedWriter fout = new BufferedWriter(new FileWriter("mesas.txt"));//fichero de out
		BaseDatos bd= new BaseDatos(entrada);
		ArrayList<String> mesa1= new ArrayList<String>();
		ArrayList<String> mesa2= new ArrayList<String>();
		ArrayList<String> mesa3= new ArrayList<String>();
		ArrayList<String> mesa4= new ArrayList<String>();
		
		String[] nombres = bd.meterUsuarios();
		bd.iniciaSesion();
		
		//repartir nombres en mesas. MAX 4 MESAS.
		repartirNombresMesas(nombres, mesa1, mesa2, mesa3, mesa4, entrada);
		
		//elegir el juego q se jugará en cada mesa	
		
		try {
			elegirJuegoMesa(mesa1,1,fout,entrada);
			elegirJuegoMesa(mesa2,2,fout,entrada);
			elegirJuegoMesa(mesa3,3,fout,entrada);
			elegirJuegoMesa(mesa4,4,fout,entrada);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BarajaMesaVacia e){
			e.printStackTrace();
		} finally {
			fout.close();
		}
	}
	
	//estos métodos son estáticos x q los quiero usar en el main
	//realmente no deberían ser estáticos.
	static void elegirJuegoMesa(ArrayList<String> mesa,int nmesa,BufferedWriter fout,BufferedReader entrada) throws IOException, BarajaMesaVacia{
		String str;
		if(!mesa.isEmpty()){
			int opcion=0;
			boolean correcto=false;
			while(!correcto){
				System.out.println("Introduzca a que juego se jugará en la mesa "+nmesa+" (1=uno 2=blackjack).");
				str = entrada.readLine();
				opcion = Integer.parseInt(str);
				if(opcion==1){
					if(mesa.size()>=2){
						fout.write("------------------------------------------\n");
						fout.write("          MESA "+nmesa+" -> JUEGO UNO\n");
						PrincipalUno.iniciaUno(mesa.toArray(new String[mesa.size()]),fout,npartidas,nmesa);
						correcto=true;
					}else
						System.out.println("Para jugar al Uno al menos hace faltan 2 jugadores.");
				}else if(opcion==2){
					fout.write("------------------------------------------\n");
					fout.write("          MESA "+nmesa+" -> JUEGO BLACK\n");
					PrincipalUno.iniciaUno(mesa.toArray(new String[mesa.size()]),fout,npartidas,nmesa);
					correcto=true;
				}
			}
		}
	}
	static void repartirNombresMesas(String[] nombres, ArrayList<String> mesa1, ArrayList<String> mesa2
			, ArrayList<String> mesa3, ArrayList<String> mesa4, BufferedReader entrada){
		for(int i=0; i<nombres.length; i++){
			int nmesa=0;
			boolean correcto=false;
			while(!correcto){
				System.out.println("Introduzca el nº de mesa de "+nombres[i]+".");
				try {
					String str = entrada.readLine();
					nmesa = Integer.parseInt(str);
				}catch (IOException e) {
					System.err.println("IO ERROR: " + e);
				}
			
				switch(nmesa){
				case 1:
					mesa1.add(nombres[i]);
					correcto=true;
					break;
				case 2:
					mesa2.add(nombres[i]);
					correcto=true;
					break;
				case 3:
					mesa3.add(nombres[i]);
					correcto=true;
				break;
				case 4:
					mesa4.add(nombres[i]);
					correcto=true;
				break;
				default:
					System.out.println("Error al introducir el número, recuerde que solo hay 4 mesas.");
				}
			}
		}
	}
}
