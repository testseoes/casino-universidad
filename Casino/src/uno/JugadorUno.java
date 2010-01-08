package uno;

import utils.Carta;
import utils.Baraja;
import utils.TColor;
import utils.Jugador;

public abstract class JugadorUno extends Jugador<Baraja<Carta>> implements
		Comparable<JugadorUno> {
	public JugadorUno(String nombre) {
		super(nombre);
	}

	abstract Carta echarCarta(Carta Carta_jugada);

	public boolean gana() {
		return cartasmano.vacia();
	}

	public void comenzarPartida(Baraja<Carta> pila, int ncartas) {
		this.comenzarPartida();
		for (int i = 0; i < ncartas; i++)
			tomarCarta(pila);
	}

	public Baraja<Carta> getCartasEspeciales(TColor color) {
		Baraja<Carta> cartasespeciales = new Baraja<Carta>();
		for (Carta carta : cartasmano.verCartas()) {
			if (carta.getValor() == 20)
				if (((CartaEspecial) carta).getColor() == color)
					cartasespeciales.recogerCarta(carta);
		}
		return cartasespeciales;
	}

	public Baraja<Carta> getCartasEspeciales(Carta cartaespecial) {
		Baraja<Carta> cartasespeciales = new Baraja<Carta>();
		for (Carta carta : cartasmano.verCartas()) {
			if (carta.getValor() == 20) {
				if (carta instanceof CartaRoba2
						& cartaespecial instanceof CartaRoba2)
					cartasespeciales.recogerCarta(carta);
				if (carta instanceof CartaSaltaTurno
						& cartaespecial instanceof CartaSaltaTurno)
					cartasespeciales.recogerCarta(carta);
				if (carta instanceof CartaCambioSentido
						& cartaespecial instanceof CartaCambioSentido)
					cartasespeciales.recogerCarta(carta);
			}
		}
		return cartasespeciales;
	}

	public Baraja<Carta> getComodines() {
		Baraja<Carta> comodines = new Baraja<Carta>();
		for (Carta carta : cartasmano.verCartas()) {
			if (carta.getValor() == 50)
				comodines.recogerCarta(carta);
		}
		return comodines;
	}

	public Baraja<Carta> getCartasUno(int num) {
		Baraja<Carta> cartasuno = new Baraja<Carta>();
		for (Carta carta : cartasmano.verCartas()) {
			if (carta.getValor() < 10)
				if (carta.getValor() == num)
					cartasuno.recogerCarta(carta);
		}
		return cartasuno;
	}

	public Baraja<Carta> getCartasUno(TColor color) {
		Baraja<Carta> cartasuno = new Baraja<Carta>();
		for (Carta carta : cartasmano.verCartas()) {
			if (carta.getValor() < 10)
				if (((CartaUno) carta).getColor() == color)
					cartasuno.recogerCarta(carta);
		}
		return cartasuno;
	}

	public Carta elegirUnaCualquiera(Baraja<Carta> cartasmano) {
		cartasmano.Barajar();
		return cartasmano.darPrimeraCarta();
	}

	public int compareTo(JugadorUno j) {
		int mayor;
		if (this.sumaPuntos() > j.sumaPuntos() | j.gana())
			mayor = 1;
		else
			mayor = -1;
		return mayor;
	}
}
