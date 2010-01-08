package blackjack;

import utils.TNumeracion;
import utils.TPalo;

public class CartaBlackJackAs extends CartaBlackJack {
	boolean es11;

	CartaBlackJackAs(int valor, TNumeracion numero, TPalo palo) {
		super(valor, numero, palo);
		es11 = true;
	}

	public boolean getEs11() {
		return es11;
	}

	public void setEs11(boolean es11) {
		this.es11 = es11;
	}

	public int getValor() {
		int valor = 1;
		if (es11)
			valor = 11;
		return valor;
	}
}
