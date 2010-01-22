package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;


public class SentarMesaController {
	
	private static final int N_MESAS =4;
    
	private SentarMesaView m_sentarView;
	private CasinoModel m_model;
	
	public SentarMesaController(SentarMesaView sentarView, CasinoModel model) {
       
        m_sentarView  = sentarView;
        m_model=model;
        
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
        				if (estado==1) m_sentarView.setEstado("La mesa seleccionada está llena"); 
        				if (estado==2) {
        					m_sentarView.setEstado("El jugador : " + login + " está en la mesa : " + mesa); 

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
