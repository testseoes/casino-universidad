package mvc;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.text.View;


public class CasinoController {
    //... The Controller needs to interact with both the Model and View.
    private CasinoModel m_model;
    private CasinoView  m_view;
    //private CalcView  m_modelCC;
    private CrearCuentaView m_ventanaCC;
    private CrearCuentaController m_controllerCC;
    private SentarMesaView m_ventanaSM;
    private SentarMesaController m_controllerSM;
    private JugarView m_ventanaJ;
    private JugarController m_controllerJ;
    
    
    //========================================================== constructor
    /** Constructor */
    public CasinoController(CasinoModel model, CasinoView view) {
        m_model = model;
        m_view  = view;
        
        
        
        //... Add listeners to the view.
        view.addInicioAutoListener(new InicioAutoListener());
        view.addResetListener(new ResetListener());
        view.addSalirListener(new SalirListener());
        
        view.addCrearCuentaListener(new CrearCuentaListener());
        view.addIniciarSesionListener(new IniciarSesionListener());
        view.addCerrarSesionListener(new CerrarSesionListener());
        view.addSentarMesaListener(new SentarMesaListener());
        view.addJugarListener(new JugarListener());
        
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
        	  System.exit(0);
//            String userInput = "";
//            try {
//                userInput = m_view.getUserInput();
//                m_model.multiplyBy(userInput);
//                m_view.setTotal(m_model.getValue());
//                
//            } catch (NumberFormatException nfex) {
//                m_view.showError("Bad input: '" + userInput + "'");
//            }
        }
    }//end inner class MultiplyListener
    
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	  System.out.println("Resetttteearr");
        }
    }
    class SalirListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	m_model.Salir();
        	System.exit(0);
        }
    }
    class CrearCuentaListener implements ActionListener {
        
    	public void actionPerformed(ActionEvent e) {
        	System.out.println("creetttteearr");
        	m_ventanaCC=new CrearCuentaView();
        	m_controllerCC=new CrearCuentaController(m_ventanaCC,m_model);        	
        }
    }
    class IniciarSesionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	  System.exit(0);
        }
    }
    class CerrarSesionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	  System.exit(0);
        }
    }
    class SentarMesaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	  m_ventanaSM=new SentarMesaView();
        	  m_controllerSM=new SentarMesaController(m_ventanaSM, m_model);
        }
    }
    class JugarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	  m_ventanaJ=new JugarView();
        	  m_controllerJ=new JugarController(m_ventanaJ,m_model);
        }
    }

    
    //////////////////////////////////////////// inner class ClearListener
    /**  1. Reset model.
     *   2. Reset View.
     */    
    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
				m_model.reset();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//            m_view.reset();
 catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e3) {
	// TODO Auto-generated catch block
	e3.printStackTrace();
}
        }
    }// end inner class ClearListener
}