package controlador;
/*
 * CalcController.java
 *
 * Created on 9 de noviembre de 2007, 12:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */




//    Handles user interaction with listeners.
//    Calls View and Model as needed.
// Fred Swartz -- December 2004

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Random;

import modelo.*;

import utils.BarajaMesaVacia;
import vista.*;


public class CasinoController {
	private static final int N_MESAS =4;

	//... The Controller needs to interact with both the Model and View.
    private CasinoModel m_model;
    private CasinoView  m_view;
 
    private CrearCuentaView m_ventanaCC;
    private CrearCuentaController m_controllerCC;
    private SentarMesaView m_ventanaSM;
    private SentarMesaController m_controllerSM;
    private DatosUsuarioView m_ventanaD;
    private DatosUsuarioController m_controllerD;
    private JugarView m_ventanaJ;
    private JugarController m_controllerJ;
    private EliminarUsuarioView m_ventanaEU;
    private EliminarUsuarioController m_controllerEU;
    private IniciarSesionView m_ventanaIS;
    private IniciarSesionController m_controllerIS;
    private CerrarSesionView m_ventanaCS;
    private CerrarSesionController m_controllerCS;
    
    
    //========================================================== constructor
    /** Constructor */
    public CasinoController(CasinoModel model, CasinoView view) {
        m_model = model;
        m_view  = view;
        
        
        //... Add listeners to the view.
        view.addInicioAutoListener(new InicioAutoListener());
        //view.addResetListener(new ResetListener());
        view.addSalirListener(new SalirListener());
        view.addWindowListener(new WindowClosing());	
        
        view.addCrearCuentaListener(new CrearCuentaListener());
        view.addDatosListener(new DatosListener());
        view.addEliminarCuentaListener(new EliminarListener());
        view.addIniciarSesionListener(new IniciarListener());
        view.addCerrarSesionListener(new CerrarListener());
        view.addSentarMesaListener(new SentarMesaListener());
        view.addJugarListener(new JugarListener());
        view.addJugarMesa1Listener(new JugarMesa1Listener());
        view.addJugarMesa2Listener(new JugarMesa2Listener());
        view.addJugarMesa3Listener(new JugarMesa3Listener());
        view.addJugarMesa4Listener(new JugarMesa4Listener());
        
//        view.addClearListener(new ClearListener());
    }
    
    
    ////////////////////////////////////////// inner class MultiplyListener
    /** When a mulitplication is requested.
     *  1. Get the user input number from the View.
     *  2. Call the model to mulitply by this number.
     *  3. Get the result from the Model.
     *  4. Tell the View to display the result.
     * If there was an error, tell the View to display it.
     */
    class InicioAutoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String [] registrados;
        	int i=0,estado=0,x;

        	Random rand = new Random();
        	x = rand.nextInt(N_MESAS);  	  
        	registrados=m_model.getTodosLogin();
        	while (i<registrados.length){
        		try {
					estado=m_model.iniciarSesion(registrados[i]);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		if (estado==2) m_view.addUsuarioConectado(registrados[i]);
        		try {
					estado=m_model.sentarUsuario(registrados[i], (x%N_MESAS));
					if (estado==4) m_view.addUsuarioMesa(registrados[i],(x%N_MESAS)+1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		i++;
        		x++;
        	}

        }
    }
    
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	 
        }
    }
    class SalirListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	m_model.Salir();
        	System.exit(0);
        }
    }
    class WindowClosing implements WindowListener{
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			m_model.Salir();
        	System.exit(0);
		} 
    } 

    class CrearCuentaListener implements ActionListener {
        
    	public void actionPerformed(ActionEvent e) {
           	m_ventanaCC=new CrearCuentaView();
        	m_controllerCC=new CrearCuentaController(m_ventanaCC,m_model,m_view);        	
        }
    }
    class DatosListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	m_ventanaD=new DatosUsuarioView();
        	m_controllerD=new DatosUsuarioController(m_ventanaD,m_model);
        	    
        }
    }
    class EliminarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	m_ventanaEU=new EliminarUsuarioView();
        	m_controllerEU=new EliminarUsuarioController(m_ventanaEU,m_model,m_view);        	
        }
    }
    class IniciarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	m_ventanaIS=new IniciarSesionView();
        	m_controllerIS=new IniciarSesionController(m_ventanaIS,m_model,m_view);        	
        }
    }
    class CerrarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	m_ventanaCS=new CerrarSesionView();
        	m_controllerCS=new CerrarSesionController(m_ventanaCS,m_model,m_view);        	
        }
    }
    class SentarMesaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	  m_ventanaSM=new SentarMesaView();
        	  m_controllerSM=new SentarMesaController(m_ventanaSM, m_model,m_view);
        }
    }
    class JugarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	  m_ventanaJ=new JugarView();
        	  m_controllerJ=new JugarController(m_ventanaJ,m_model,m_view);
        }
    }
    class JugarMesa1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	  JugarEnMesa(1);
        }
    }
    class JugarMesa2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
      	  JugarEnMesa(2);
        }
    }
    class JugarMesa3Listener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
    	  JugarEnMesa(3);
      }
    }
    class JugarMesa4Listener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		JugarEnMesa(4);
    	}
    }
	void JugarEnMesa(int mesa) {
		
		int estado=0;
		BufferedWriter fOut = null;
						
		try {
			estado=m_model.jugar(mesa-1,fOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BarajaMesaVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (estado==0) m_view.setEstado("No hay jugadores en la mesa "+ mesa); 
      	if (estado==1) m_view.setEstado("El Uno requiere más de un jugador"); 
        if (estado==2){
           	m_view.setEstado("Se ha jugado en la mesa : " + mesa);
           	String sOut = BufferedWriterToString(fOut);
           	m_view.setJuegosPane(sOut);
           	m_view.setEstado("Traza del juego en mesa : " + mesa);
           	m_view.BorraMesa(mesa);			
        }
           	
	}
                   		 
       		private String BufferedWriterToString(BufferedWriter fOut) {
			
			Reader r = null;
			String str=null;
            
			try {
	            r = new FileReader("mesas.txt");
	            StringBuffer sb = new StringBuffer();
	            char[] b = new char[1024];
	            int n;
	            while ((n = r.read(b)) > 0) {
	                sb.append(b, 0, n);
	            }
	            str = sb.toString();
	        } catch (FileNotFoundException ex) {
	            System.out.println(ex);
	        } catch (IOException ex) {
	            System.out.println(ex);
	        } finally {
	            try {
	                r.close();
	            } catch (IOException ex) {
	                System.out.println(ex);
	            }
	        }
			return str;
		}
    
}