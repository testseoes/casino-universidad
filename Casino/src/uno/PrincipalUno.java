package uno;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import bd.BaseDatos;
import utils.BarajaMesaVacia;

public class PrincipalUno {

	public static void iniciaUno(String[] nombres,BufferedWriter fout,BaseDatos bd, int npartidas, int nmesa) throws IOException, BarajaMesaVacia, SQLException {	
		final int NPARTIDAS = npartidas;
		Partida partida = new Partida();
		int tipos[] = tiposDeJugadoresUno(nombres,bd);
		partida.crearJugadores(nombres,tipos); // Lo hago fuera de uno para que
										// los jugadores se comporten
										// igual en todas las partidas
		
		
		Uno juego = new Uno(partida,/* nombres, */fout);
		for (int i = 1; i <= NPARTIDAS; i++){ 
			bd.iniciaPartida(nmesa,"Uno",nombres);
			bd.invertirCreditoUno(1, nombres);
			juego.juega(bd);	
			bd.finPartida();
		}
	}
	private static int[] tiposDeJugadoresUno(String[] nombres, BaseDatos bd ) throws SQLException{
		int tipos[]= new int[nombres.length];
		for(int i=0;i<nombres.length;i++)tipos[i]=bd.obtenerTipoJugadorUno(nombres[i]);
		return tipos;
	}
}
