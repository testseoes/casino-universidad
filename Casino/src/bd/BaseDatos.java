package bd;
import java.sql.*;
public class BaseDatos {
	Connection conexion = null;
	public BaseDatos(){
		try {  
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/casino","root","kasino");  
		} catch (SQLException e) {  
			System.out.println("Error de MySQL: " + e.getMessage());  
		} catch (Exception e) {  
			System.out.println("Error inesperado: " + e.getMessage());  
		}  
	}
	public void meterUsuario(){
		Statement stmt;
		try {
			stmt=conexion.createStatement();
			int numTuplas1= stmt.executeUpdate("INSERT INTO jugadores (Login, Pass, Nombre) VALUES ('Homero', '123' , 'Paquillo')"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
