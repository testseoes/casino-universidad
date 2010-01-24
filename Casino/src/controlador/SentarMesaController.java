package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import modelo.CasinoModel;

import vista.CasinoView;
import vista.SentarMesaView;


public class SentarMesaController {
	
	private static final int N_MESAS =4;
    
	private SentarMesaView m_sentarView;
	private CasinoModel m_model;
	private CasinoView m_view;
	
	public SentarMesaController(SentarMesaView sentarView, CasinoModel model,CasinoView view) {
       
        m_sentarView  = sentarView;
        m_model=model;
        m_view=view;
        
        sentarView.addSentarListener(new SentarListener());
        sentarView.addCancelarListener(new CancelarListener());
        
    }
	

	class SentarListener implements ActionListener {
		String login,aux;
		int mesa,estado;
		boolean campoVacio,sentado;
		
		public void actionPerformed(ActionEvent e) {
			campoVacio=false;
			login=m_sentarView.getLoginInput();
			if (login.isEmpty()){
				campoVacio=true;
				m_sentarView.setEstado("Es necesario rellenar el campo login");
			}
        	if (!campoVacio){
        		aux=m_sentarView.getMesaInput();
        		if (aux.isEmpty()){
        			campoVacio=true;
        			m_sentarView.setEstado("Es necesario rellenar el campo mesa"); 
        		}else{
        			aux.trim();
        			aux.replaceAll(" ", "");
        			try{
        				mesa=Integer.parseInt(aux);
            				
        			}
        			catch (NumberFormatException nfe){
        				m_sentarView.setEstado("El campo ha de ser un número"); 
        	        	campoVacio=true;
        			}
        			
        		}
        	}
        	if (!campoVacio){
        		if ((mesa>0)&&(mesa<=N_MESAS)){
        		
        			try {
        				estado=m_model.sentarUsuario(login,mesa-1);
        				if (estado==0) m_sentarView.setEstado("El jugador no se encuentra"); 
        				if (estado==1) m_sentarView.setEstado("El jugador no ha iniciado sesion"); 
        				if (estado==2) m_sentarView.setEstado("La mesa seleccionada está llena"); 
        				if (estado==3) m_sentarView.setEstado("El jugador ya está en esa mesa"); 
        				if (estado==4) {
        					m_sentarView.setEstado("El jugador : " + login + " está en la mesa : " + mesa); 
        					m_view.addUsuarioMesa(login,mesa);
        				}
        			} catch (SQLException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (IOException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        		}	
        		else m_sentarView.setEstado("elija una mesa del 1 al " + N_MESAS); 
    			 
        			
           	} 
		}
    }
	class CancelarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_sentarView.cerrar();
        }
    }

}
