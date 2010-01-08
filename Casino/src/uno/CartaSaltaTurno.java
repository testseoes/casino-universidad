package uno;

import utils.TColor;

public class CartaSaltaTurno extends CartaEspecial {
	public CartaSaltaTurno(int valor, TColor c) {
		super(valor, c);
		this.c = c;
	}

	public String toString() {
		return "(Salta Turno, " + c + ")";
	}
}
