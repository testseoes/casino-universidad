package Casino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import bd.BaseDatos;
import blackjack.PrincipalBlack;
import uno.PrincipalUno;
import utils.BDNoHayUsuarios;
import utils.BarajaMesaVacia;

public class Casino {
	final static int npartidas=5; //en cada mesa se jugarán este nº de partidas. tb se podría pedir en elegirJuegoMesa para elegir el nº partidas a jugar en cada mesa.
	
	public static void main(String[] args) throws IOException,SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDNoHayUsuarios{
		BufferedReader entrada = new BufferedReader (new InputStreamReader(System.in)); //entrada x pantalla
		BufferedWriter fout = new BufferedWriter(new FileWriter("mesas.txt"));//fichero de out
		BaseDatos bd= new BaseDatos();
		ArrayList<String> mesa1= new ArrayList<String>();
		ArrayList<String> mesa2= new ArrayList<String>();
		ArrayList<String> mesa3= new ArrayList<String>();
		ArrayList<String> mesa4= new ArrayList<String>();
		int numsesion;
		
		String[] nombres = meterUsuarios(entrada,bd); //nombres son los logins de los jugadores
		/*
		 ATENCION!!! no se comprueba q:
		 	1.- al crear un usuario se repita un login ya existente
		 	(posible solución= manejar la excepción concreta de SQL)
		 	
		 	2.- al crear un usuario que los datos sean válidos (Ej// plantarse con 25 )
		 	(posible solución= añadir comprobadores)
		 	
		 	3.- al se logee un usuario ya logeado (Ej// paco puede jugar a la vez en varias mesas o incluso en su misma mesa)
		 	(posible solucion= añadir comprobadores)
		 	
		 ATENCIÓN: tp se han manejado las posibles excepciones IO, SQL, Exception
		 	
		 */
		
		numsesion=bd.iniciaSesion(nombres);
		  
		//repartir nombres en mesas. MAX 4 MESAS.
		repartirNombresMesas(nombres, mesa1, mesa2, mesa3, mesa4, entrada);
		
		//elegir el juego q se jugará en cada mesa y jugar. no es más q lanzar el principal según el juego q sea, con los datos correspondientes.	
		try {
			elegirJuegoMesa(mesa1,1,bd,fout,entrada);
			elegirJuegoMesa(mesa2,2,bd,fout,entrada);
			elegirJuegoMesa(mesa3,3,bd,fout,entrada);
			elegirJuegoMesa(mesa4,4,bd,fout,entrada);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BarajaMesaVacia e){
			e.printStackTrace();
		} finally {
			fout.close();
			bd.cierraSesion(numsesion);
		}
	}
	
	//estos métodos son estáticos x q los quiero usar en el main
	//realmente no deberían ser estáticos.
	static private void elegirJuegoMesa(ArrayList<String> mesa,int nmesa,BaseDatos bd,BufferedWriter fout,BufferedReader entrada) throws IOException, BarajaMesaVacia, SQLException{
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
						PrincipalUno.iniciaUno(mesa.toArray(new String[mesa.size()]),fout,bd,npartidas,nmesa);
						correcto=true;
					}else
						System.out.println("Para jugar al Uno al menos hace faltan 2 jugadores.");
				}else if(opcion==2){
					fout.write("------------------------------------------\n");
					fout.write("          MESA "+nmesa+" -> JUEGO BLACK\n");
					PrincipalBlack.iniciaBlack(mesa.toArray(new String[mesa.size()]),fout,bd,npartidas,nmesa);
					correcto=true;
				}
			}
		}
	}
	static private void repartirNombresMesas(String[] nombres, ArrayList<String> mesa1, ArrayList<String> mesa2
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
	static private String[] meterUsuarios(BufferedReader entrada, BaseDatos bd) throws SQLException, IOException{
		int numjugadores;
		ArrayList<String> usuarios =new ArrayList<String>();
		String login,pass;
		
		System.out.println("Introduzca Nº Jugadores.");
		numjugadores=Integer.parseInt(entrada.readLine());
		for(int i=0; i<numjugadores ; i++){
			System.out.println("Introduzca Login"+(i+1)+".");
			login=entrada.readLine();
			if(bd.comprobarLogin(login)){
				System.out.println("Introduzca la Pass.");
				pass=entrada.readLine();
				for(int j=0;j<3 && !bd.comprobarPass(login, pass);j++){
					System.out.println("ERROR EN LA PASS. Introduzcala de nuevo.");
					pass=entrada.readLine();
				}
				if(bd.comprobarPass(login, pass))usuarios.add(login);
				else System.out.println("Se ha equivocado 4 veces, el usuario no se logeará.");
			}else{
				System.out.println("El usuario no existe, deberá crearlo.");
				usuarios.add(crearUsuario(bd,entrada));
			}
		}
		return usuarios.toArray(new String[usuarios.size()]);
	}
	
	private static String crearUsuario(BaseDatos bd, BufferedReader entrada) throws IOException, SQLException {
		System.out.println("Introduzca el nuevo Login.");
		String login=entrada.readLine();
		System.out.println("Introduzca la nueva Pass.");
		String pass=entrada.readLine();
		System.out.println("Introduzca el nombre.");
		String nombre=entrada.readLine();
		System.out.println("Introduzca el apellido.");
		String apellido=entrada.readLine();
		System.out.println("Introduzca el tipo de jugador que será en el UNO(1=Numero 2=Color 3=Especial).");
		int tipojugadoruno=Integer.parseInt(entrada.readLine());
		System.out.println("Introduzca si se doblará en el Black Jack(false=no true=si).");
		boolean doblar=Boolean.parseBoolean(entrada.readLine());
		System.out.println("Introduzca si separará en el Black Jack(false=no true=si).");
		boolean separar=Boolean.parseBoolean(entrada.readLine());
		System.out.println("Introduzca la cantidad con la que se plantará en el Black Jack.");
		int plantarse=Integer.parseInt(entrada.readLine());
		bd.crearUsuario(login,pass,nombre,apellido,tipojugadoruno,doblar,separar,plantarse);
		return login;
	}
}
