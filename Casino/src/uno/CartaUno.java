package uno;

import utils.Carta;
import utils.TColor;

public class CartaUno extends Carta {
	int num;
	TColor c;

	public CartaUno(int valor, int num, TColor c) {
		super(valor);
		this.num = num;
		this.c = c;
	}

	public String toString() {
		return "(" + num + ", " + c + ")";
	}

	public TColor getColor() {
		return c;
	}

	public int getNum() {
		return num;
	}
}
