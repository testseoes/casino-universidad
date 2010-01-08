package uno;

import utils.TColor;

public class CartaRoba2 extends CartaEspecial {
	final int ROBA = 2;

	public CartaRoba2(int valor, TColor c) {
		super(valor, c);
	}

	public int getNCartasRobadas() {
		return ROBA;
	}

	public String toString() {
		return "(Roba" + ROBA + " , " + c + ")";
	}
}