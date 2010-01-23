package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.IOException;
import java.sql.SQLException;


public class CrearCuentaController {
	
	private CrearCuentaView m_crearView;
	private CasinoModel m_model;
	private CasinoView m_view;
	
	public CrearCuentaController(CrearCuentaView crearView, CasinoModel model, CasinoView view) {
       
        m_crearView  = crearView;
        m_model=model;
        m_view=view;
        
        crearView.addCrearListener(new CrearListener());
        
        crearView.addCancelarListener(new CancelarListener());
        
    }
	

	class CrearListener implements ActionListener {
		String login,pass1,pass2,nombre,apellido,aux;
		int tipoJugadorUno,tipoJugadorBlack,plantarse;
		boolean campoVacio,creado;
		
		public void actionPerformed(ActionEvent e) {
			campoVacio=false;
			login=m_crearView.getUserInput();
			if (login.isEmpty()){
				campoVacio=true;
				m_crearView.setEstado("Es necesario rellenar el campo login");
			}
        	if (!campoVacio){
        		pass1=m_crearView.getPass1Input();
       		  	if (pass1.isEmpty()){
       		  		campoVacio=true;
       		  		m_crearView.setEstado("Es necesario rellenar el campo password 1");
       		  	}
        	}
        	if (!campoVacio){
        		pass2=m_crearView.getPass2Input();
        		if (pass2.isEmpty()){
        			campoVacio=true;
        			m_crearView.setEstado("Es necesario rellenar el campo password 2");
        		}
        	}
        	if (!campoVacio){
        		nombre=m_crearView.getNombreInput();
        		if (nombre.isEmpty()){
        			campoVacio=true;
        			m_crearView.setEstado("Es necesario rellenar el campo nombre");
        		}
        	}
        	if (!campoVacio){
        		apellido=m_crearView.getApellidoInput();
        		if (apellido.isEmpty()){
        			campoVacio=true;
        			m_crearView.setEstado("Es necesario rellenar el campo apellido");
        		}
        	}
        	if (!campoVacio){
        		aux=m_crearView.getTipoJugadorUnoInput();
        		if (aux.isEmpty()){
        			campoVacio=true;
        			m_crearView.setEstado("Es necesario rellenar el campo Tipo de Jugador Uno"); 
        		}else{
        			aux.trim();
        			aux.replaceAll(" ", "");
        			try{
        				tipoJugadorUno=Integer.parseInt(aux);
            				
        			}
        			catch (NumberFormatException nfe){
        				m_crearView.setEstado("El campo Tipo de Jugador Uno no es un número"); 
        	        	campoVacio=true;
        			}
        			
        		}
        	}
        	   
           	if (!campoVacio){
           		aux=m_crearView.getTipoJugadorBlackInput();
           		if (aux.isEmpty()){
           			campoVacio=true;
           			m_crearView.setEstado("Es necesario rellenar el campo Tipo de Jugador BlackJack");
           		}else{
           			aux.trim();
           			aux.replaceAll(" ", "");
        			try{
        				tipoJugadorBlack=Integer.parseInt(aux);
            				
        			}
        			catch (NumberFormatException nfe){
        				m_crearView.setEstado("El campo Tipo de Jugador Black no es un número"); 
        	        	campoVacio=true;
        			}
           		}
           	}
           	if (!campoVacio){
           		aux=m_crearView.getPlantarseInput();
           		if (aux.isEmpty()){
           			campoVacio=true;
           			m_crearView.setEstado("Es necesario rellenar el campo Puntuacion para plantarse");
           		}else{
           			aux.trim();
           			aux.replaceAll(" ", "");
        			try{
        				plantarse=Integer.parseInt(aux);
            				
        			}
        			catch (NumberFormatException nfe){
        				m_crearView.setEstado("El campo plantarse no es un número"); 
        	        	campoVacio=true;
        			}
           		}
         	
//        		  System.out.println(login);
           	}
           	if (!campoVacio){
           		if (pass1.endsWith(pass2)){
           			try {
           				creado=false;
           				creado=m_model.crearUsuario(login, pass1, nombre, apellido, tipoJugadorUno, tipoJugadorBlack, plantarse);
					if (creado){
						m_crearView.setEstado("Usuario creado con éxito");
						m_model.iniciarUsuario(login, pass1);
						m_view.addUsuario(login);
					} else m_crearView.setEstado("El Usuario ya existe");
           			} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
           		}
           		else{
           			m_crearView.setEstado("Se introdugeron password distintas");
           		}
           	} 
		}
    }

	class CancelarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_crearView.cerrar();
        }
    }

}
