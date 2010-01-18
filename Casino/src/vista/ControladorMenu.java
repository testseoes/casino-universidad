package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenu implements ActionListener {
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == Gestor.inicioAuto){
			/** Iniciar sesion de varios jugadores y ponerlos a jugar**/
	
		}if (e.getSource() == Gestor.salir){
			System.exit(0);
	
		}
	}

}
