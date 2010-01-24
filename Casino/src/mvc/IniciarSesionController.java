package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesionController {
	
    
	private IniciarSesionView m_eliminarView;
	private CasinoModel m_model;
	private CasinoView m_view;
	
	public IniciarSesionController(IniciarSesionView eliminarView, CasinoModel model,CasinoView view) {
       
        m_eliminarView  = eliminarView;
        m_model=model;
        m_view=view;
        
        eliminarView.addEliminarListener(new EliminarListener());
        eliminarView.addCancelarListener(new CancelarListener());
        
    }
	

	class EliminarListener implements ActionListener {
		String login,pass;
		boolean campoVacio;
		int estado;
		String [] conectados;
		
		public void actionPerformed(ActionEvent e) {
			campoVacio=false;
			login=m_eliminarView.getLoginInput();
			if (login.isEmpty()){
				campoVacio=true;
				m_eliminarView.setEstado("Es necesario rellenar el campo login");
			}
        	if (!campoVacio){
        		pass=m_eliminarView.getPassInput();
        		if (!m_model.comprobarPass(login,pass)){
        			campoVacio=true;
        			m_eliminarView.setEstado("La Password no es correcta"); 
        		}else{
        			estado = m_model.iniciarSesion(login);
        			if (estado==0) m_eliminarView.setEstado("El Usuario : " + login + " tenía sesion iniciada");
        			if (estado==1) m_eliminarView.setEstado("El Aforo del Casino está lleno, inténtelo más tarde");
        			if (estado==2) {
        				m_eliminarView.setEstado("Sesion del Usuario : " + login + " iniciada con éxito");
        				conectados = m_model.GetConectados();
        				m_view.muestraUsuariosConectados(conectados);
        			}
        			
        		}
        	}
        	        
		}
    }
	class CancelarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_eliminarView.cerrar();
        }
    }

}
