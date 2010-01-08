package uno;

public class CartaCambioColorRoba4 extends CartaEspecial {
	final int ROBA = 4;

	public CartaCambioColorRoba4(int valor) {
		super(valor);
	}

	public int getNCartasRobadas() {
		return ROBA;
	}

	public String toString() {
		return "(Roba" + ROBA + ", Cambio de Color)";
	}
}