package utils;

public class BarajaMesaVacia extends Exception {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "No quedan cartas para robar\n";
	}
}
