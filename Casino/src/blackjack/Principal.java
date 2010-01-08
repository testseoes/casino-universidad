package blackjack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import utils.BarajaMesaVacia;

public class Principal {

	public static void main(String[] args) throws IOException, BarajaMesaVacia {
		String[] nombres = { "(1)Cartman", "(2)   Kyle", "(3)  Kenny",
				"(4)   Stan" };
		final int NPARTIDAS = 50;
		try {
			BufferedWriter fout = new BufferedWriter(new FileWriter(
					"PartidaBlackJack.txt"));
			Partida partida = new Partida();
			partida.crearJugadores(nombres); // Lo hago fuera de uno para que
												// los jugadores se comporten
												// igual en todas las partidas
			BlackJack juego = new BlackJack(partida,/* nombres, */fout);
			for (int i = 0; i < NPARTIDAS; i++)
				juego.juega();
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BarajaMesaVacia e){
			e.printStackTrace();
		}
	}
}
