package blackjack;

import utils.Baraja;
import utils.Carta;
import utils.Jugador;

public abstract class JugadorBlackJack extends Jugador<Baraja<Carta>> {
	int plantarse;
	
	public JugadorBlackJack(String nombre, int plantarse)  {
		super(nombre);
		this.plantarse=plantarse;
	}
	public void comenzarPartida(Baraja<Carta> baraja, int ncartas){
		this.comenzarPartida();
		for (int i = 0; i < ncartas; i++)
			tomarCarta(baraja);
	}
	public boolean pedirCarta(){
		int tengoAs11=0;
		for(Carta carta : this.cartasmano.verCartas())
			if(carta instanceof CartaBlackJackAs)
				if(((CartaBlackJackAs) carta).getEs11()) 
					tengoAs11++;
		if(tengoAs11>=1 & this.sumaPuntos()>21){
			boolean cambiado_un_as11=false;
			for(Carta carta : this.cartasmano.verCartas())
				if(carta instanceof CartaBlackJackAs & !cambiado_un_as11)
					if(((CartaBlackJackAs) carta).getEs11()){
						((CartaBlackJackAs) carta).setEs11(false);
						tengoAs11--;
						cambiado_un_as11=true;
					}
		}
		if(this.sumaPuntos()<plantarse) return true;
		else return false;
	}
	public Carta verUltimaCarta(){
		return this.cartasmano.verUltimaCarta();
	}
	public boolean gana() {
		if (this.sumaPuntos()<=21) return true;
		else return false;
	}
	public String toString2(){
		StringBuffer str = new StringBuffer();
		for (Carta carta : cartasmano.verCartas())
			str.append(carta);
		return str.toString();
	}
}
