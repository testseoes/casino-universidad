package uno;

import utils.Baraja;
import utils.BarajaMesaVacia;
import utils.Carta;
import utils.TColor;

public class Partida {
	private int NCARTAS_MANO;
	private int NJUGADORES = 0;
	private int turno;
	private Baraja<Carta> cartas_jugadas;
	private BarajaUno baraja;
	private Carta cartaelegida;
	private JugadorUno[] jugadores;
	private int jugada;

	public Partida() {
		NCARTAS_MANO = 7;
		turno = 0;
		jugada = 0;
	}

	void crearJugadores(String[] nombres) {
		NJUGADORES = nombres.length;
		jugadores = new JugadorUno[NJUGADORES];
		Double tipojugador;
		for (int i = 0; i < NJUGADORES; i++) {
			tipojugador = Math.random() * 3 % 3;
			switch (tipojugador.intValue()) {
			case 0:
				jugadores[i] = new JugadorNumero(nombres[i]);
				break;
			case 1:
				jugadores[i] = new JugadorColor(nombres[i]);
				break;
			default:
				jugadores[i] = new JugadorCartaEspecial(nombres[i]);
			}
		}
	}

	void comenzarPartida() {
		baraja = new BarajaUno();
		cartas_jugadas = new Baraja<Carta>();
		baraja.Barajar();
		jugada = 3; // Asi la carta de la mesa te obliga a robar
		for (int i = 0; i < NJUGADORES; i++)
			jugadores[i].comenzarPartida(baraja, NCARTAS_MANO);
		cartas_jugadas.recogerCarta(baraja.darPrimeraCarta());
	}

	void robarCarta() throws BarajaMesaVacia {
		if (baraja.vacia())
			throw new BarajaMesaVacia();
		cartaelegida = (baraja.verPrimeraCarta());
		jugadores[turno].tomarCarta(baraja);
	}

	void juega() throws BarajaMesaVacia {
		cartaelegida = null;
		cartaelegida = (jugadores[turno].echarCarta(cartas_jugadas
				.verUltimaCarta()));
		if (cartaelegida == null) {
			if (jugada == 0) {
				robarCarta();
			}
			jugada++;
		} else {
			cartas_jugadas.recogerCarta(cartaelegida);
			jugada = 3;
		}
	}

	boolean anteriorEchaCarta() {
		if (jugada == 3)
			return true;
		else
			return false;
	}

	boolean finpartida() {
		return jugadores[turno].gana();
	}

	void anterior() {
		turno = ((NJUGADORES + (turno - 1)) % NJUGADORES);
	}

	void siguiente() {
		turno = ((turno + 1) % NJUGADORES);
	}

	void eligeColor() {
		Double ncolores = Math.random() * 4 % 4;
		TColor color;
		switch (ncolores.intValue()) {
		case 0:
			color = TColor.AZUL;
			break;
		case 1:
			color = TColor.ROJO;
			break;
		case 2:
			color = TColor.VERDE;
			break;
		default:
			color = TColor.AMARILLO;
		}
		((CartaEspecial) cartas_jugadas.verUltimaCarta()).setColor(color);
	}

	void resetJugada() {
		jugada = 0;
	}

	void darVueltaBaraja() {
		for (int i = 1; i < cartas_jugadas.getNCartas(); i++)
			baraja.recogerCarta(cartas_jugadas.darPrimeraCarta());
	}

	Carta getCartaelegida() {
		return cartaelegida;
	}

	int getJugada() {
		return jugada;
	}

	int getNJUGADORES() {
		return NJUGADORES;
	}

	int getTurno() {
		return turno;
	}

	Carta getCartaJugada() {
		return cartas_jugadas.verUltimaCarta();
	}

	JugadorUno[] getJugadores() {
		return jugadores;
	}
}
