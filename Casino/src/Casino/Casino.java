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
	
	public static void main(String[] args) throws IOException,SQLException, BDNoHayUsuarios {
		BufferedReader entrada = new BufferedReader (new InputStreamReader(System.in));
		BaseDatos bd= new BaseDatos(entrada);
		ArrayList<String> mesa1= new ArrayList<String>();
		ArrayList<String> mesa2= new ArrayList<String>();
		ArrayList<String> mesa3= new ArrayList<String>();
		ArrayList<String> mesa4= new ArrayList<String>();
		String str=null;
		
		String[] nombres = bd.meterUsuarios();
		bd.iniciaSesion();
		
		//repartir nombres en mesas. MAX 4 MESAS.
		for(int i=0; i<nombres.length; i++){
			int nmesa=0;
			boolean correcto=false;
			while(!correcto){
				System.out.println("Introduzca el nº de mesa de "+nombres[i]+".");
				try {
					str = entrada.readLine();
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
		
		//elegir el juego q se jugará en cada mesa	
		int npartidas=30;
		try {
			BufferedWriter fout = new BufferedWriter(new FileWriter("PartidaUno.txt"));
			if(!mesa1.isEmpty()){
				int opcion=0;
				boolean correcto=false;
				while(!correcto){
					System.out.println("Introduzca a que juego se jugará en la mesa 1 (1=uno 2=blackjack).");
					try {
						str = entrada.readLine();
						opcion = Integer.parseInt(str);
					}catch (IOException e) {
						System.err.println("IO ERROR: " + e);
					}
					if(opcion==1){
						if(mesa1.size()>=2){
							fout.write("------------------------------------------\n");
							fout.write("          MESA 1 -> JUEGO UNO\n");
							fout.write("          ------------------\n");
							PrincipalUno.iniciaUno((String[])mesa1.toArray(),fout,npartidas,1);
							correcto=true;
						}else
							System.out.println("Para jugar al Uno al menos hace faltan 2 jugadores.");
					}else if(opcion==2){
						fout.write("------------------------------------------\n");
						fout.write("          MESA 1 -> JUEGO BLACK\n");
						fout.write("          ------------------\n");
						PrincipalUno.iniciaUno((String[])mesa1.toArray(),fout,npartidas,1);
						correcto=true;
					}
				}
			}
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BarajaMesaVacia e){
			e.printStackTrace();
		}
	}		
}
