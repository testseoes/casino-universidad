package uno;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeSet;

import bd.BaseDatos;
import utils.BarajaMesaVacia;

public class Uno {
	// private String[] nombres;
	private Partida partida;
	private boolean sentido;
	private BufferedWriter fout;


	public Uno(Partida partida, /* String[] nombres, */BufferedWriter fout) {
		// this.nombres=nombres;
		this.partida = partida;
		this.fout = fout;
		
	}

	public void juega(BaseDatos bd) throws IOException, BarajaMesaVacia, SQLException {
		comenzarPartida();
		accionCartaEspecial();
		do {
			robarPorCartaEspecial();
			jugarPartida();
		} while (!partida.finpartida());
		finalizarPartida(bd);
	}

	public void accionCartaEspecial() throws IOException {
		if (partida.anteriorEchaCarta()) {
			if (partida.getCartaJugada() instanceof CartaCambioColor
					| partida.getCartaJugada() instanceof CartaCambioColorRoba4) {
				partida.eligeColor();
				fout.write(partida.getJugadores()[partida.getTurno()].getNombre() + " ha elegido el color "
						+ ((CartaEspecial) partida.getCartaJugada()).getColor()
						+ "\n");
			}
			if (partida.getCartaJugada() instanceof CartaCambioSentido) {
				if (sentido == true)
					sentido = false;
				else
					sentido = true;
				fout.write("\n           Se cambia orientación\n");
			}
			if (partida.getCartaJugada() instanceof CartaSaltaTurno) {
				if (sentido == true)
					partida.siguiente();
				else
					partida.anterior();
				fout.write("\n"
						+ partida.getJugadores()[partida.getTurno()]
								.getNombre() + " pierde su turno\n");
			}
		}
		fout.newLine();
		if (sentido == true)
			partida.siguiente();
		else
			partida.anterior();
	}

	public void robarPorCartaEspecial() throws IOException {
		if (partida.anteriorEchaCarta()) {
			if (partida.getCartaJugada() instanceof CartaRoba2
					| partida.getCartaJugada() instanceof CartaCambioColorRoba4) {
				int robadas = 0;
				if (partida.getCartaJugada() instanceof CartaRoba2)
					robadas = 2;
				do {
					try {
						partida.robarCarta();
						fout.write(partida.getJugadores()[partida.getTurno()].getNombre() + " roba : "
								+ partida.getCartaelegida().toString() + "\n");
						robadas++;
					} catch (BarajaMesaVacia e) {
						partida.darVueltaBaraja();
						fout
								.write("          (Baraja Sin Cartas, recupero cartas de La Mesa)\n");
					}
				} while (robadas < 4);
			}
		}
	}

	public void comenzarPartida() throws IOException {
		// partida.crearJugadores(nombres); se hace fuera, en el principal.
		sentido = true;
		partida.comenzarPartida();
		fout.write("------------------------------------------\n");
		fout.write("          Empieza La Partida\n");
		fout.write("          ------------------\n");
		for (int i = 0; i < partida.getNJUGADORES(); i++) {
			String tipodejugador;
			if (partida.getJugadores()[i] instanceof JugadorColor)
				tipodejugador = "(J.Color ) ";
			else if (partida.getJugadores()[i] instanceof JugadorCartaEspecial)
				tipodejugador = "(J.Especi) ";
			else
				tipodejugador = "(J.Número) ";
			fout.write(tipodejugador);
			fout.write(partida.getJugadores()[i].toString() + "\n");
		}
		fout.write("------------------------------------------\n");
		fout.write("      En la Mesa: " + partida.getCartaJugada() + "\n");
		fout.write("------------------------------------------\n");
	}

	public void jugarPartida() throws IOException {
		fout.write(partida.getJugadores()[partida.getTurno()].toString()
						+ "\n");
		partida.resetJugada();
		while (partida.getJugada() < 2) {
			try {
				partida.juega();
			} catch (BarajaMesaVacia e) {
				partida.darVueltaBaraja();
				fout.write("          (Baraja Sin Cartas, recupero cartas de La Mesa)\n");
			}
			if (partida.getJugada() == 1)
				fout.write(partida.getJugadores()[partida.getTurno()].getNombre() + " roba : "
						+ partida.getCartaelegida().toString() + "\n");
			if (partida.getJugada() == 3)
				fout.write(partida.getJugadores()[partida.getTurno()].getNombre() + " echa : "
						+ partida.getCartaelegida().toString() + "\n");
		}
		if (!partida.finpartida())
			accionCartaEspecial();
	}

	public void finalizarPartida(BaseDatos bd) throws IOException, SQLException{
		partida.getJugadores()[partida.getTurno()].sumaPartidaGanada();
		String ganador= partida.getJugadores()[partida.getTurno()].getNombre();
		bd.recuperarCreditoUno(partida.getNJUGADORES(),ganador);
		JugadorUno jugadores[] = partida.getJugadores();
		float puntuacion = 0;
		for (int i = 0; i < jugadores.length; i++)
			puntuacion += jugadores[i].sumaPuntos();
		TreeSet<JugadorUno> ranking = new TreeSet<JugadorUno>();
		for (int i = 0; i < jugadores.length; i++)
			ranking.add(jugadores[i]);
		fout.write("------------------------------------------\n");
		fout.write("             Clasificación\n");
		fout.write("          ------------------\n");
		int i = 0;
		for (JugadorUno jugador : ranking) {
			i++;
			fout.write(i + "º " + jugador.getNombre() + " tiene "
					+ jugador.sumaPuntos() + " puntos y lleva "
					+ jugador.getNumerodepartidasganadas()
					+ " partidas ganadas.\n");
		}
	}
}
