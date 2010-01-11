package utils;

public class BDNoHayUsuarios extends Exception {
	public String toString() {
		return "No se han logeado usuarios.\n";
	}
}
