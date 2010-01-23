package mvc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DatosUsuarioController {
	
    
	private DatosUsuarioView m_datosView;
	private CasinoModel m_model;

	
	public DatosUsuarioController(DatosUsuarioView datosView, CasinoModel model) {
       
        m_datosView  = datosView;
        m_model=model;
               
        datosView.addDatosListener(new DatosListener());
        datosView.addCancelarListener(new CancelarListener());
        
    }
	

	class DatosListener implements ActionListener {
		String login,pass;
		int creditosInvertidos,creditosRecuperados,tipoUno,tipoBlack;

		boolean campoVacio;
		
		public void actionPerformed(ActionEvent e) {
			campoVacio=false;
			login=m_datosView.getLoginInput();
			if (login.isEmpty()){
				campoVacio=true;
				m_datosView.setEstado("Es necesario rellenar el campo login");
			}
        	if (!campoVacio){
        		pass=m_datosView.getPassInput();
        		if (!m_model.comprobarPass(login,pass)){
        			campoVacio=true;
        			m_datosView.setEstado("La Password no es correcta"); 
        		}else{
        			//m_model.eliminarUsuario(login);
        			
        			tipoUno=m_model.getTipoUno(login);
        			m_datosView.setEstado("Datos del Usuario : " + login);
        			//System.out.println(tipoUno);
        			String aux= String.valueOf(tipoUno);
        			m_datosView.setTipoUno(aux);
        			
        			try {
						creditosInvertidos=m_model.getCreditosInvertidos(login);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			aux= String.valueOf(creditosInvertidos);
        			m_datosView.setCreditosUno(aux);
        			
        			tipoBlack=m_model.getTipoBlack(login);
        			aux= String.valueOf(tipoBlack);
        			m_datosView.setTipoBlack(aux);
        			
        			try {
						creditosRecuperados=m_model.getCreditosRecuperados(login);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			aux= String.valueOf(creditosRecuperados);
        			m_datosView.setCreditosBlack(aux);
        			
        			
        			
        			
        		}
        	}
        	        
		}
    }
	class CancelarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_datosView.cerrar();
        }
    }

}
