package Casino;
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

import controlador.CasinoController;

import vista.CasinoView;

import modelo.CasinoModel;

import bd.BaseDatos;




public class CasinoMVC {
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
    	
        

            CasinoModel model = new CasinoModel();
            CasinoView view= new CasinoView("Gestor Casino",model);
            CasinoController controller = new CasinoController(model, view);
       
        

   
    }
}