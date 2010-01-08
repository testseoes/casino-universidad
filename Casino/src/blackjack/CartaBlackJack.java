package blackjack;

import utils.Carta;
import utils.TNumeracion;
import utils.TPalo;

public class CartaBlackJack extends Carta {
	TNumeracion numero;
	TPalo palo;

	public CartaBlackJack(int valor, TNumeracion numero, TPalo palo) {
		super(valor);
		this.numero = numero;
		this.palo = palo;
	}

	TNumeracion getNumero() {
		return numero;
	}

	TPalo getPalo() {
		return palo;
	}

	public String toString() {
		return "(" + numero + " de " + palo + ")";
	}
}
