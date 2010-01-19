package blackjack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

import bd.BaseDatos;
import utils.BarajaMesaVacia;
import utils.TipoJugadorBlack;

public class PrincipalBlack {

	public static void iniciaBlack(String[] nombres,BufferedWriter fout,BaseDatos bd, int npartidas,int nmesa) throws IOException, BarajaMesaVacia, SQLException {
		final int NPARTIDAS = npartidas;
		Partida partida = new Partida();
		TipoJugadorBlack tipos[]=tiposDeJugadoresBlack(nombres,bd);
		
		partida.crearJugadores(nombres,tipos); // Configuración de jugadores a partir de la bd
	
		BlackJack juego = new BlackJack(partida,/* nombres, */fout);
		for (int i = 0; i < NPARTIDAS; i++){
	        bd.iniciaPartida(nmesa,"BlackJack",nombres);	
			juego.juega(bd);
			bd.finPartida();
			}
	}
	private static TipoJugadorBlack[] tiposDeJugadoresBlack(String[] nombres, BaseDatos bd)throws SQLException{
		TipoJugadorBlack tipos[]=new TipoJugadorBlack[nombres.length]; 
		for(int i=0;i<nombres.length;i++)tipos[i]=bd.obtenerTipoJugadorBlack(nombres[i]);
		return tipos;
	}
}
