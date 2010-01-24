package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.CasinoModel;

import vista.CasinoView;
import vista.CerrarSesionView;

public class CerrarSesionController {
	
    
	private CerrarSesionView m_eliminarView;
	private CasinoModel m_model;
	private CasinoView m_view;
	
	public CerrarSesionController(CerrarSesionView eliminarView, CasinoModel model,CasinoView view) {
       
        m_eliminarView  = eliminarView;
        m_model=model;
        m_view=view;
        
        eliminarView.addCerrarListener(new EliminarListener());
        eliminarView.addCancelarListener(new CancelarListener());
        
    }
	

	class EliminarListener implements ActionListener {
		String login,pass;
		boolean cerrada=false;
		
		public void actionPerformed(ActionEvent e) {
			String[] aux;
			login=m_eliminarView.getLoginInput();
			if (login.isEmpty()){
				m_eliminarView.setEstado("Es necesario rellenar el campo login");
			}else{
				cerrada=m_model.cerrarSesion(login);
				if (cerrada){
        			m_eliminarView.setEstado("Sesion Usuario : " + login + " Cerrada");
            		aux=m_model.GetConectados();
            		m_view.muestraUsuariosConectados(aux);
        		}else  		m_eliminarView.setEstado("El Usuario : " + login + " No tiene sesion iniciada");
        		
        	}
        	
        	        
		}
    }
	class CancelarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_eliminarView.cerrar();
        }
    }

}
