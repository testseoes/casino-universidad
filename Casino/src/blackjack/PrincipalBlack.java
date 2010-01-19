package blackjack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

import bd.BaseDatos;
import utils.BarajaMesaVacia;

public class PrincipalBlack {

	public static void iniciaBlack(String[] nombres,BufferedWriter fout,BaseDatos bd, int npartidas,int nmesa) throws IOException, BarajaMesaVacia, SQLException {
		final int NPARTIDAS = npartidas;
		Partida partida = new Partida();
		partida.crearJugadores(nombres); // Lo hago fuera de uno para que
											// los jugadores se comporten
											// igual en todas las partidas
		BlackJack juego = new BlackJack(partida,/* nombres, */fout);
		for (int i = 0; i < NPARTIDAS; i++){
	        bd.iniciaPartida(nmesa,"BlackJack",nombres);	
			juego.juega(bd);
			bd.finPartida();
			}
	}
}
