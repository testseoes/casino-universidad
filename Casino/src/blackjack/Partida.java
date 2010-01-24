package blackjack;

import utils.BarajaMesaVacia;

public class Partida {
	private int NJUGADORES = 0;
	private int NUMERO_BARAJAS;
	private BarajaBlackJack baraja;
	private JugadorBlackJack[] jugadores;
	private float[] dineroGanado;
	private float[] dineroApostado;
	StringBuffer str = new StringBuffer();

	public Partida() {
		NUMERO_BARAJAS=2;
	}
	// explicacion de x q es necesario TipoJugadorBlack [] en principal
	void crearJugadores(String[] nombres, TipoJugadorBlack[] tipos) {
		NJUGADORES = nombres.length;
		jugadores = new JugadorBlackJack[NJUGADORES+1];
		dineroGanado=new float[NJUGADORES];
		dineroApostado=new float[NJUGADORES];
		for (int i = 0; i < NJUGADORES; i++) {
			switch (tipos[i].getTipo()) {
			case 1:
				jugadores[i] = new JugadorBlackJackN(nombres[i],true,true,tipos[i].getPlantarse());  // Jugador dobla y separa
				str.append(jugadores[i].getNombre() + " es un Jugador que dobla y separa y se planta en: ");
				str.append(tipos[i].getPlantarse() + "\n");
				break;
			case 2:
				jugadores[i] = new JugadorBlackJackN(nombres[i],false,true,tipos[i].getPlantarse()); // Jugador parte
				str.append(jugadores[i].getNombre() + " es un Jugador que separa y se planta en: ");
				str.append(tipos[i].getPlantarse() + "\n");
				break;
			case 3:
				jugadores[i] = new JugadorBlackJackN(nombres[i],true,false,tipos[i].getPlantarse()); // Jugador dobla
				str.append(jugadores[i].getNombre() + " es un Jugador que dobla y se planta en: ");
				str.append(tipos[i].getPlantarse() + "\n");
				break;
			default:
				jugadores[i] = new JugadorBlackJackN(nombres[i],false,false,tipos[i].getPlantarse());
				str.append(jugadores[i].getNombre() + " es un Jugador que solo juega su mano y se planta en: ");
				str.append(tipos[i].getPlantarse() + "\n");
				break;
			}
			dineroGanado=new float[NJUGADORES];
			dineroApostado=new float[NJUGADORES];
		}
		jugadores[NJUGADORES] = new JugadorBlackJackCrupier("ElCroupier");
	}
	void comenzarPartida() {
		baraja = new BarajaBlackJack(NUMERO_BARAJAS);
		baraja.Barajar();
		for (int i = 0; i < NJUGADORES; i++){
			((JugadorBlackJackN) jugadores[i]).comenzarPartida(baraja);
			dineroGanado[i]=0;
			dineroApostado[i]=0;
		}
		((JugadorBlackJackCrupier) jugadores[NJUGADORES]).comenzarPartida(baraja);
	}
	void pedirCarta(int turno) throws BarajaMesaVacia {
		if (baraja.vacia())
			throw new BarajaMesaVacia();
		jugadores[turno].tomarCarta(baraja);
	}
	void cambioMazoJugadorPartido(int mazo_juega, int turno) throws BarajaMesaVacia {
		if(mazo_juega==0)
			((JugadorBlackJackN) jugadores[turno]).meterenCartasMano(mazo_juega);
		else if(mazo_juega!=0 & mazo_juega<((JugadorBlackJackN) jugadores[turno]).getNMazos_separados()){
			((JugadorBlackJackN) jugadores[turno]).sacarSumaPuntosdeCartasManos(mazo_juega-1);
			((JugadorBlackJackN) jugadores[turno]).sacardeCartasManos(mazo_juega-1);
			((JugadorBlackJackN) jugadores[turno]).meterenCartasMano(mazo_juega);
		}
		else {
			((JugadorBlackJackN) jugadores[turno]).sacarSumaPuntosdeCartasManos(mazo_juega-1);
			((JugadorBlackJackN) jugadores[turno]).sacardeCartasManos(mazo_juega-1);
		}
	}
	void apuesta(int turno){
		dineroApostado[turno]+=((JugadorBlackJackN) jugadores[turno]).getApuesta();
	}
	float getApostado(int turno){
		return dineroApostado[turno];
	}
	void gana(int turno){
		dineroGanado[turno]+=((JugadorBlackJackN) jugadores[turno]).getApuesta();
	}
	float getGanado(int turno){
		return dineroGanado[turno];
	}
	boolean finpartida() {
		return jugadores[NJUGADORES].pedirCarta();
	}
	int getNJUGADORES() {
		return NJUGADORES;
	}
	String tipoJugadores(){
		return str.toString();
	}
	JugadorBlackJack[] getJugadores() {
		return jugadores;
	}	
}
