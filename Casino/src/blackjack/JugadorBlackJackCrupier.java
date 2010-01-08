package blackjack;

import utils.Baraja;
import utils.Carta;

public class JugadorBlackJackCrupier extends JugadorBlackJack{
	
	public JugadorBlackJackCrupier(String nombre) {
		super(nombre,17);
	}
	public void comenzarPartida(Baraja<Carta> baraja){
		this.comenzarPartida(baraja, 1);
	}
}
