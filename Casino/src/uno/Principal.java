package uno;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import bd.BaseDatos;

import utils.BarajaMesaVacia;

public class Principal {

	public static void main(String[] args) throws IOException, BarajaMesaVacia, SQLException {
		BaseDatos bd= new BaseDatos();
		String[] nombres;
		
			nombres = bd.meterUsuarios();		
		//funcion de la BD en la que se van metiendo login (OJO AHORA SON DNI, NO NOMBRES)
		//se comprueba si estan y sino se registran. Devuelve la cadena nombres. Si te 
		//equivocas al meter la pass 4 veces devolverá NULL en nombres.
		//OJO, EL Q HAGA EL INTERFAZ tiene tb q traducir el pakete de BD.
		
		final int NPARTIDAS = 30;
		if(nombres!=null){
			try {
				BufferedWriter fout = new BufferedWriter(new FileWriter("PartidaUno.txt"));
				Partida partida = new Partida();
				partida.crearJugadores(nombres); // Lo hago fuera de uno para que
												// los jugadores se comporten
												// igual en todas las partidas
				Uno juego = new Uno(partida,/* nombres, */fout);
				for (int i = 0; i < NPARTIDAS; i++)
					juego.juega();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
