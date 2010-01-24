package blackjack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

import bd.BaseDatos;
import utils.BarajaMesaVacia;

public class BlackJack {
	// private String[] nombres;
	private Partida partida;
	private BufferedWriter fout;
	int turno;

	public BlackJack(Partida partida, /* String[] nombres, */BufferedWriter fout) {
		// this.nombres=nombres;
		this.partida = partida;
		this.fout = fout;
	}
	public void juega(BaseDatos bd) throws IOException, SQLException, BarajaMesaVacia{
		comenzarPartida();
		jueganJugadores();
		fout.write(partida.getJugadores()[turno].toString() + "\n");
		juegaJugadorNormal(); //En este caso el Croupier
		finalizarPartida(bd);
	}
	public void comenzarPartida() throws IOException {
		// partida.crearJugadores(nombres); se hace fuera, en el principal.
		partida.comenzarPartida();
		fout.write("------------------------------------------\n");
		fout.write("          Empieza La Partida\n");
		fout.write("          ------------------\n");
		fout.write(partida.tipoJugadores());
		fout.write("------------------------------------------\n");
		for (int i = 0; i <= partida.getNJUGADORES(); i++)
			fout.write(partida.getJugadores()[i].toString() + "\n");
		fout.write("------------------------------------------\n");
	}
	public void jueganJugadores() throws IOException, BarajaMesaVacia {
		turno=0;
		for(int i=0;i<partida.getNJUGADORES();i++){	
			int mazo_separado=0;
			fout.write(partida.getJugadores()[turno].toString() + "\n");
			while(((JugadorBlackJackN) partida.getJugadores()[turno]).separar()){
				fout.write(partida.getJugadores()[turno].getNombre() + " decide separar\n");
				partida.pedirCarta(turno);
				fout.write(partida.getJugadores()[turno].getNombre() + " roba : " + partida.getJugadores()[turno].verUltimaCarta().toString() + "\n");
			}
			if(((JugadorBlackJackN) partida.getJugadores()[turno]).doblar()){
				fout.write(partida.getJugadores()[turno].getNombre() + " decide doblar\n");
				partida.pedirCarta(turno);
				fout.write(partida.getJugadores()[turno].getNombre() + " roba : " + partida.getJugadores()[turno].verUltimaCarta().toString() + "\n");
				fout.write(partida.getJugadores()[turno].getNombre() + " acaba mano con: " + partida.getJugadores()[turno].toString2() + "\n");
			}
			else{
				if(((JugadorBlackJackN)partida.getJugadores()[turno]).getNMazos_separados()!=0){
					while(mazo_separado<((JugadorBlackJackN)partida.getJugadores()[turno]).getNMazos_separados()){
						partida.cambioMazoJugadorPartido(mazo_separado, turno);
						int mazo=mazo_separado+1;
						fout.write("- Juega " + mazo + "º mano con: " + partida.getJugadores()[turno].toString2() + "\n");
						juegaJugadorNormal();
						mazo_separado++;
					}
					partida.cambioMazoJugadorPartido(mazo_separado, turno);
				}	
				else juegaJugadorNormal();
			}
			fout.write("------------------------------------------\n");
			turno++;
		}
	}
	public void juegaJugadorNormal() throws IOException, BarajaMesaVacia{
		while(partida.getJugadores()[turno].pedirCarta()){
			partida.pedirCarta(turno);
			fout.write(partida.getJugadores()[turno].getNombre() + " roba : " + partida.getJugadores()[turno].verUltimaCarta().toString() + "\n");
		}
		//fout.write("           se planta con: " + partida.getJugadores()[turno].toString2() + "\n");
		fout.write(partida.getJugadores()[turno].getNombre() + " no pide más cartas\n");
		fout.write(partida.getJugadores()[turno].getNombre() + " tiene: " + partida.getJugadores()[turno].toString2() + "\n");
	}
	public void finalizarPartida(BaseDatos bd) throws IOException, SQLException {
		float puntuacionCrupier=partida.getJugadores()[partida.getNJUGADORES()].sumaPuntos();
		fout.write("------------------------------------------\n");
		fout.write("              Resultados\n");
		fout.write("          ------------------\n");
		fout.write(partida.getJugadores()[partida.getNJUGADORES()].getNombre() + " tiene: ");
		fout.write(puntuacionCrupier + " puntos\n");
		for(turno=0;turno<partida.getNJUGADORES();turno++)
			if(((JugadorBlackJackN)partida.getJugadores()[turno]).getNMazos_separados()!=0){
				fout.write(partida.getJugadores()[turno].getNombre() + " tiene mas de una apuesta:\n");
				for(int i=0; i<((JugadorBlackJackN)partida.getJugadores()[turno]).getNMazos_separados();i++){
					partida.apuesta(turno);
					int mazo=i+1;
					fout.write("           la " + mazo + "º tiene: ");
					fout.write(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos(i) + " puntos");
					if(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos(i)<=21){
						if(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos(i)>puntuacionCrupier | puntuacionCrupier>21){
							if(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos(i)==21)
								if(((JugadorBlackJackN)partida.getJugadores()[turno]).getNumerodecartas(i)==2)
									partida.gana(turno);
							partida.gana(turno);
							partida.gana(turno);
							fout.write("(Gana)\n");
						}
						else if(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos(i)==puntuacionCrupier){
							partida.gana(turno);
							fout.write("(Empata)\n");
						}
						else fout.write("(Pierde)\n");
					}
					else fout.write("(Pierde)\n");
				}
			}
			else{
				partida.apuesta(turno);
				fout.write(partida.getJugadores()[turno].getNombre() + " tiene: ");
				fout.write(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos() + " puntos");
				if(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos()<=21){
					if(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos()>puntuacionCrupier | puntuacionCrupier>21){
						if(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos()==21)
							if(((JugadorBlackJackN)partida.getJugadores()[turno]).getNumerodecartas()==2)
								partida.gana(turno);
						partida.gana(turno);
						partida.gana(turno);
						fout.write("(Gana)\n");
					}
					else if(((JugadorBlackJackN)partida.getJugadores()[turno]).sumaPuntos()==puntuacionCrupier){
						partida.gana(turno);
						fout.write("(Empata)\n");
					}
					else fout.write("(Pierde)\n");
				}
				else fout.write("(Pierde)\n");
			}
		//fout.write("------------------------------------------\n");
		//fout.write(partida.tipoJugadores());
		fout.write("------------------------------------------\n");
		for(turno=0;turno<partida.getNJUGADORES();turno++){
			
			fout.write(partida.getJugadores()[turno].getNombre() + " ha apostado: ");
			fout.write(partida.getApostado(turno) + "\n");
			float ganado=partida.getGanado(turno)-partida.getApostado(turno);
			fout.write(partida.getJugadores()[turno].getNombre() + " ha ganado  : ");
			fout.write(ganado + "\n");
			bd.creditosBlack(partida.getJugadores()[turno].getNombre(),partida.getApostado(turno),partida.getGanado(turno));
		}
	}
}


