package utils;

public class BDNoHayUsuarios extends Exception {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "No se han logeado usuarios.\n";
	}
}
