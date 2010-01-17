package uno;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

import bd.BaseDatos;
import utils.BarajaMesaVacia;

public class PrincipalUno {

	public static void iniciaUno(String[] nombres,BufferedWriter fout, int npartidas, int nmesa) throws IOException, BarajaMesaVacia, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{	
		final int NPARTIDAS = npartidas;
		Partida partida = new Partida();
		BaseDatos bd=new BaseDatos();
		partida.crearJugadores(nombres); // Lo hago fuera de uno para que
										// los jugadores se comporten
										// igual en todas las partidas
		
		
		Uno juego = new Uno(partida,/* nombres, */fout);
		for (int i = 1; i <= NPARTIDAS; i++){ 
			bd.iniciaPartida(nmesa,"Uno",nombres);
			bd.invertirCredito(1, nombres);
			juego.juega();	
			bd.finPartida();
		}
	}
	
}
