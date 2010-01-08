package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja<C> {
	protected int ncartas;
	protected List<C> mazo;

	public Baraja() {
		ncartas = 0;
		mazo = new ArrayList<C>();
	}

	public void Barajar() {
		Collections.shuffle(mazo);
	}

	public C darPrimeraCarta() {
		if (!this.vacia())
			return mazo.remove(0);
		else
			return null;
	}
	public C darUltimaCarta(){
		if (!this.vacia())
			return mazo.remove(mazo.size()-1);
		else
			return null;
	}

	public void recogerCarta(C carta) {
		if (carta != null)
			mazo.add(carta);
	}

	public void recogerCartas(List<C> cartas) {
		if (cartas != null)
			mazo.addAll(cartas);
	}

	public List<C> verCartas() {
		return mazo;
	}

	public C verPrimeraCarta() {
		return mazo.get(0);
	}

	public C verUltimaCarta() {
		return mazo.get(mazo.size() - 1);
	}

	public boolean eliminarCarta(C carta) {
		return mazo.remove(carta);
	}

	public boolean vacia() {
		return mazo.isEmpty();
	}

	public void vaciarBaraja() {
		mazo.clear();
	}

	public String toString() {
		return "Cartas: " + mazo;
	}

	public int getNCartas() {
		ncartas = mazo.size();
		return ncartas;
	}
}
