/*
 * 108 Cartas distribuidas de la siguiente manera:
 *  19 Cartas azules:    del 0 al 9 (2 cartas por número excepto el 0)
 *  19 Cartas verdes:    del 0 al 9 (2 cartas por número excepto el 0)
 *  19 Cartas rojas:     del 0 al 9 (2 cartas por número excepto el 0)
 *  19 Cartas amarillas: del 0 al 9 (2 cartas por número excepto el 0)
 *   8 Cartas especiales "roba dos":          2 de cada color
 *   8 Cartas especiales "cambio de sentido": 2 de cada color
 *   8 Cartas especiales "pierde el turno":   2 de cada color
 *   4 Cartas especiales "comodín cambio de color"
 *   4 Cartas especiales "comodín cambio de color roba cuatro"
 *   no --> 4 Cartas especiales "comodín carta blanca o UNO"
 */

package uno;

import utils.Baraja;
import utils.Carta;
import utils.TColor;

public class BarajaUno extends Baraja<Carta> {
	final short NCARTAS = 108;
	final short NCARTAS_NUMERO = 9;
	final short NCARTAS_IGUALES = 2;
	final short NCARTAS_ESPECIALES = 2;
	final short NCARTAS_COMODINES = 4;
	final short VALOR_ESPECIALES = 20;
	final short VALOR_COMODINES = 50;

	public BarajaUno() {
		int i = 0;
		int j = 0;

		for (TColor c : TColor.values()) {
			for (i = 0; i <= NCARTAS_NUMERO; i++) {
				for (j = 0; j < NCARTAS_IGUALES; j++) {
					if (i == 0)
						j++;
					mazo.add(new CartaUno(i, i, c));
				}
			}
		}
		for (TColor c : TColor.values()) {
			for (i = 0; i < NCARTAS_ESPECIALES; i++) {
				mazo.add(new CartaRoba2(VALOR_ESPECIALES, c));
				mazo.add(new CartaCambioSentido(VALOR_ESPECIALES, c));
				mazo.add(new CartaSaltaTurno(VALOR_ESPECIALES, c));
			}
		}
		for (i = 0; i < NCARTAS_COMODINES; i++) {
			CartaEspecial carta = new CartaCambioColor(VALOR_COMODINES);
			carta.setComodin(true);
			mazo.add(carta);
			carta = new CartaCambioColorRoba4(VALOR_COMODINES);
			carta.setComodin(true);
			mazo.add(carta);
		}
	}
}