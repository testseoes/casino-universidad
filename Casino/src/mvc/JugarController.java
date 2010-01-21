package mvc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

import utils.BDNoHayUsuarios;
import utils.BarajaMesaVacia;


public class JugarController {
	
	private static final int N_MESAS =4;
    
	private JugarView m_jugarView;
	private CasinoModel m_model;
	
	public JugarController(JugarView jugarView, CasinoModel model) {
       
        m_jugarView  = jugarView;
        m_model=model;
        
        jugarView.addJugarListener(new JugarListener());
        jugarView.addCancelarListener(new CancelarListener());
        
    }
	

	class JugarListener implements ActionListener {
		String aux;
		int mesa,estado=0;
		BufferedWriter fout;
	
		
		public void actionPerformed(ActionEvent e) {
			
			aux=m_jugarView.getMesaInput();
        		if (aux.isEmpty()){
        			m_jugarView.setEstado("Es necesario rellenar el campo mesa"); 
        		}else{
        			aux.trim();
        			aux.replaceAll(" ", "");
        			try{
        				mesa=Integer.parseInt(aux);
            			estado=m_model.jugar(mesa-1);
            			if (estado==0) m_jugarView.setEstado("No hay jugadores en la mesa"); 
            			if (estado==1) m_jugarView.setEstado("El Uno requiere más de un jugador"); 
            			if (estado==2){
            				m_jugarView.setEstado("Se ha jugado en la mesa : " + mesa); 
//            				System.out.println(fout);
            			}
                   		 
        			}
        			catch (NumberFormatException nfe){
        				m_jugarView.setEstado("El campo ha de ser un número"); 
           			} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BarajaMesaVacia e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
        			
        		}
        	}
    }
	class CancelarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_jugarView.dispose();
        	m_jugarView.setVisible(false);
        }
    }

}
