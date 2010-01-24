package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.CasinoView;
import vista.EliminarUsuarioView;

import modelo.CasinoModel;

public class EliminarUsuarioController {
	
    
	private EliminarUsuarioView m_eliminarView;
	private CasinoModel m_model;
	private CasinoView m_view;
	
	public EliminarUsuarioController(EliminarUsuarioView eliminarView, CasinoModel model,CasinoView view) {
       
        m_eliminarView  = eliminarView;
        m_model=model;
        m_view=view;
        
        eliminarView.addEliminarListener(new EliminarListener());
        eliminarView.addCancelarListener(new CancelarListener());
        
    }
	

	class EliminarListener implements ActionListener {
		String [] aux;
		String login,pass,add;
		boolean campoVacio, cerrada=false;
		
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
        			m_model.eliminarUsuario(login);
        			cerrada=m_model.cerrarSesion(login);
        			add=null;
        			if (cerrada) {
        				add=" y su sesion ha sido cerrada";
        				aux=m_model.GetConectados();
        				m_view.muestraUsuariosConectados(aux);
        			}
        			m_eliminarView.setEstado("Cuenta del Usuario : " + login + " eliminada" + add);
        			m_view.muestraUsuariosBd();
        			
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
