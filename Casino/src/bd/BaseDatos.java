package bd;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

import utils.BDNoHayUsuarios;
public class BaseDatos {
	private Connection conexion = null;
	private String[] usuarios = null;
	private int partidaInicio;
	private BufferedReader entrada;
	public BaseDatos(BufferedReader entrada){
		this.entrada=entrada;
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
		String usuarios[];
		String login,pass;
		
		System.out.println("Introduzca Nº Jugadores.");
		numjugadores=Integer.parseInt(entrada.readLine());
		usuarios = new String[numjugadores];
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
					usuarios[i]=login;
				}else return null;
			}else{
			System.out.println("ERRORRRRRRRRRR");
			}
		}
		return usuarios;
	}
	public void iniciaSesion() throws SQLException, BDNoHayUsuarios{
		Statement stmt=conexion.createStatement();
		if(usuarios==null)throw new BDNoHayUsuarios();
		else{
			//1º busco el número de la última partida
			partidaInicio=numUltPartida()+1;
			//2º inserto fecha, hora y el numpartida inicial.
			stmt.executeUpdate("INSERT INTO estadisticas_sesion" +
				" (FechaInicioSesion, HoraInicioSesion, PartidaInicialSesion) " +
				"VALUES (CURDATE(),CURTIME(),"+ partidaInicio +")");
			//3º inserto los logins
			for(int i=0; i<usuarios.length ; i++){
				stmt.executeUpdate("UPDATE estadisticas_sesion " +
						"SET Login"+(i+1)+"='"+ usuarios[i] +"'" +
						" WHERE PartidaInicialSesion="+ partidaInicio);
			}
		}
	}
	public void cierraSesion() throws SQLException, BDNoHayUsuarios{
		Statement stmt=conexion.createStatement();
		if(usuarios==null)throw new BDNoHayUsuarios();
		else{
			//1º busco el número de la última partida				
			int partidaFin = numUltPartida();
			//2º inserto fecha, hora y el numpartida final.
			stmt.executeUpdate("UPDATE estadisticas_sesion " +
					"SET FechaFinSesion=CURDATE(), HoraFinSesion=CURTIME(), " +
					"PartidaFinSesion=" + partidaFin +
					" WHERE PartidaInicialSesion="+ partidaInicio);
		}
	}
	public int numUltPartida() throws SQLException{
		Statement stmt=conexion.createStatement();
		ResultSet resultado = stmt.executeQuery("SELECT * FROM partidas ORDER BY Npartida DESC LIMIT 1");
		if(resultado.next()) return resultado.getInt("Npartida");
		else return 0;
	}
}
