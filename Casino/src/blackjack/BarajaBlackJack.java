package blackjack;

import utils.Baraja;
import utils.Carta;
import utils.TPalo;
import utils.TNumeracion;

public class BarajaBlackJack extends Baraja<Carta> {

	public BarajaBlackJack(int numero_barajas) {
		for(int i=0; i<numero_barajas; i++){
			for (TPalo p : TPalo.values()){
				for (TNumeracion n : TNumeracion.values()) {
					if (n != TNumeracion.AS)
						mazo.add(new CartaBlackJack(getValorNum(n), n, p));
					else
						mazo.add(new CartaBlackJackAs(1, n, p));
				}
			}
		}
	}

	int getValorNum(TNumeracion numero) {
		int valor;
		switch (numero) {
		// case AS: valor=1; break;
		case DOS:
			valor = 2;
			break;
		case TRES:
			valor = 3;
			break;
		case CUATRO:
			valor = 4;
			break;
		case CINCO:
			valor = 5;
			break;
		case SEIS:
			valor = 6;
			break;
		case SIETE:
			valor = 7;
			break;
		case OCHO:
			valor = 8;
			break;
		case NUEVE:
			valor = 9;
			break;
		default:
			valor = 10;
		}
		return valor;
	}
}