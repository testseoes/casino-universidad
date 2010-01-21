package mvc;
/*
 * CalcMVC.java
 *
 * Created on 9 de noviembre de 2007, 12:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.SQLException;

import bd.BaseDatos;




public class CasinoMVC {
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
//    	BaseDatos bd= new BaseDatos();
		
    	
        java.awt.EventQueue.invokeLater(new Runnable() {
            CasinoModel model = new CasinoModel();
            CasinoView view= new CasinoView("Gestor Casino",model);
            public void run() {
            	view.setSize(700,600);
        		view.setLocation(100,100);
        		view.setVisible(true);
            }
            CasinoController controller = new CasinoController(model, view);
        });
        
//        System.out.println(bd.comprobarLogin("Pepe"));
//        bd.crearUsuario("Pepote","Pepote", "Pepe", "Pepe", 2,2,10);
//        System.out.println(bd.comprobarLogin("Pepe"));
   
    }
}