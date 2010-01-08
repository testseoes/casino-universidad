package uno;

import utils.TColor;

public class CartaCambioSentido extends CartaEspecial {
	public CartaCambioSentido(int valor, TColor c) {
		super(valor, c);
		this.c = c;
	}

	public String toString() {
		return "(Cambio de Sentido, " + c + ")";
	}
}
