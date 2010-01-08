package uno;

import utils.Carta;
import utils.TColor;

public class CartaEspecial extends Carta {
	private boolean comodin = false;
	TColor c;

	public CartaEspecial(int valor) {
		super(valor);
	}

	public CartaEspecial(int valor, TColor c) {
		super(valor);
		this.c = c;
	}

	public TColor getColor() {
		return c;
	}

	public void setColor(TColor c) {
		this.c = c;
	}

	public boolean isComodin() {
		return comodin;
	}

	public void setComodin(boolean comodin) {
		this.comodin = comodin;
	}
}