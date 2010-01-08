package utils;

public abstract class Jugador<B extends Baraja<Carta>> {
	private String nombre;
	private int numeropartidasganadas;
	protected int numerocartas;
	protected Baraja<Carta> cartasmano;

	public Jugador(String nombre) {
		this.nombre = nombre;
		numeropartidasganadas = 0;
		numerocartas = 0;
		cartasmano = new Baraja<Carta>();
	}

	public void tomarCarta(B baraja) {
		cartasmano.recogerCarta(baraja.darPrimeraCarta());
		numerocartas++;
	}

	public void comenzarPartida() {
		numerocartas = 0;
		cartasmano.vaciarBaraja();
	}

	public abstract boolean gana();

	public float sumaPuntos() {
		float Puntos = 0;
		int n = cartasmano.getNCartas();
		for (int i = 0; i < n; i++) {
			Puntos += cartasmano.verCartas().get(i).getValor();
		}
		return Puntos;
	}

	public void sumaPartidaGanada() {
		numeropartidasganadas++;
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(nombre + " tiene: ");
		for (Carta carta : cartasmano.verCartas())
			str.append(carta);
		return str.toString();
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumerodecartas() {
		return numerocartas;
	}

	public int getNumerodepartidasganadas() {
		return numeropartidasganadas;
	}
}
