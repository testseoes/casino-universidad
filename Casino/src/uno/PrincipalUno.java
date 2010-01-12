package uno;

import java.io.BufferedWriter;
import java.io.IOException;
import utils.BarajaMesaVacia;

public class PrincipalUno {

	public static void iniciaUno(String[] nombres,BufferedWriter fout, int npartidas, int nmesa) throws IOException, BarajaMesaVacia{	
		final int NPARTIDAS = npartidas;
		Partida partida = new Partida();
		partida.crearJugadores(nombres); // Lo hago fuera de uno para que
										// los jugadores se comporten
										// igual en todas las partidas
		Uno juego = new Uno(partida,/* nombres, */fout);
		for (int i = 1; i <= NPARTIDAS; i++) juego.juega();	
	}
}
