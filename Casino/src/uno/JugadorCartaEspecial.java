package uno;

import utils.Baraja;
import utils.Carta;

public class JugadorCartaEspecial extends JugadorUno {
	public JugadorCartaEspecial(String nombre) {
		super(nombre);
	}

	public Carta echarCarta(Carta carta_jugada) {
		Carta carta;
		Baraja<Carta> posibles = new Baraja<Carta>();
		if (carta_jugada.getValor() > 10) {
			posibles = getCartasEspeciales(((CartaEspecial) carta_jugada)
					.getColor());
			posibles = getCartasEspeciales(carta_jugada);
			if (posibles.vacia()) {
				posibles = getCartasUno(((CartaEspecial) carta_jugada)
						.getColor());
				if (posibles.vacia())
					posibles = getComodines();
			}
		} else {
			posibles = getCartasEspeciales(((CartaUno) carta_jugada).getColor());
			if (posibles.vacia()) {
				posibles = getCartasUno(((CartaUno) carta_jugada).getNum());
				if (posibles.vacia()) {
					posibles = getCartasUno(((CartaUno) carta_jugada)
							.getColor());
					if (posibles.vacia())
						posibles = getComodines();
				}
			}
		}
		if (!posibles.vacia()) {
			carta = elegirUnaCualquiera(posibles);
			cartasmano.eliminarCarta(carta);
			return carta;
		} else
			return null;
	}
}
