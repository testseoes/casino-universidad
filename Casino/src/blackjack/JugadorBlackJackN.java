package blackjack;

import utils.Baraja;
import utils.Carta;

public class JugadorBlackJackN extends JugadorBlackJack{
	boolean doblar;
	boolean separar;
	boolean puededoblar;
	boolean puedeseparar;
	Baraja<Carta>[] cartas_mazos_separados;
	int mazos_separados;
	float[] puntos_mazos_separados;
	float apuesta;
	
	public JugadorBlackJackN(String nombre, boolean doblar, boolean separar, int plantarse) {
		super(nombre,plantarse);
		this.doblar=doblar;
		this.separar=separar;
	}
	public void comenzarPartida(Baraja<Carta> baraja){
		this.comenzarPartida(baraja, 2);
		puedeseparar=separar;
		puededoblar=doblar;
		apuesta=1;
		mazos_separados=0;
	}
	public boolean separar() {
		if(puedeseparar & ((CartaBlackJack)this.cartasmano.verPrimeraCarta()).getNumero()==((CartaBlackJack)this.cartasmano.verUltimaCarta()).getNumero()){
			if(mazos_separados==0) mazos_separados++;
			mazos_separados++;
			puededoblar=false;
		}
		else {
			puedeseparar=false;
			if(mazos_separados!=0){
				puntos_mazos_separados=new float[mazos_separados];
				cartas_mazos_separados=new Baraja[mazos_separados];
				for (int i=0; i<mazos_separados;i++){
					cartas_mazos_separados[i]=new Baraja<Carta>();
					cartas_mazos_separados[i].recogerCarta(cartasmano.darPrimeraCarta());
					if(i==0) cartas_mazos_separados[i].recogerCarta(cartasmano.darUltimaCarta());
				}
			}
		}
		return puedeseparar;
	}
	public boolean doblar(){
		if(puededoblar & this.sumaPuntos()>=9 & this.sumaPuntos()<=11) apuesta=apuesta*2;
		else puededoblar=false;
		return puededoblar;
	}
	public void meterenCartasMano(int mazos_separados){
		while(!cartas_mazos_separados[mazos_separados].vacia())
			this.cartasmano.recogerCarta(cartas_mazos_separados[mazos_separados].darPrimeraCarta());
	}
	public void sacardeCartasManos(int mazos_separados){
		while(!cartasmano.vacia())
			this.cartas_mazos_separados[mazos_separados].recogerCarta(cartasmano.darPrimeraCarta());
	}
 	public void sacarSumaPuntosdeCartasManos(int mazos_separados){
		this.puntos_mazos_separados[mazos_separados]=this.sumaPuntos();
	}
 	public float sumaPuntos(int mazos_separados){
 		return this.puntos_mazos_separados[mazos_separados];
 	}
 	public int getNumerodecartas(int turno){
 		return cartas_mazos_separados[turno].getNCartas();
 	}
	public int getNMazos_separados(){
		return mazos_separados;
	}
	public float getApuesta(){
		return apuesta;
	}
	public void setApuesta(float apuesta){
		this.apuesta=apuesta;
	}
}
