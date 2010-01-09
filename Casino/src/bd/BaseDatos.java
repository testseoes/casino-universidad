package bd;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	public String[] meterUsuarios() throws SQLException, IOException{
		int numjugadores;
		BufferedReader entrada = new BufferedReader (new InputStreamReader(System.in));
		String nombres[];
		String login,pass;
		
		System.out.println("Introduzca Nº Jugadores.");
		numjugadores=Integer.parseInt(entrada.readLine());
		nombres = new String[numjugadores];
		for(int i=0; i<numjugadores ; i++){
			System.out.println("Introduzca Login "+(i+1));
			login=entrada.readLine();
			Statement stmt=conexion.createStatement(); 
			ResultSet resultado = stmt.executeQuery("SELECT * FROM jugadores WHERE Login='"+login+"'");
			if (resultado.next()){
				pass = resultado.getString("Pass");
				System.out.println("Introduzca la Pass");
				String str=entrada.readLine();
				int j=0;
				while(!str.equals(pass) && j<3){
					j++;
					System.out.println("ERROR... Introduzca la Pass");
					str=entrada.readLine();
				}
				if(str.equals(pass)){
					nombres[i]=login;
				}else return null;
			}else{
			System.out.println("ERRORRRRRRRRRR");
			}
		}
		return nombres;
	}
}
