package bd;

import java.io.IOException;
import java.sql.*;
import utils.BDNoHayUsuarios;

public class BaseDatos {
	/*
	 ATENCI�N!!
	 NO USAR LA ENTRADA SALIDA en las funciones, eso se har� fuera, aqu� solo lo 
	 referido a base de datos.
	 */
	private Connection conexion = null;
	int numsesion;
	
	public BaseDatos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{ 
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		conexion = DriverManager.getConnection("jdbc:mysql://localhost/casino","root","kasino");  
	}
	public boolean comprobarLogin(String login) throws SQLException, IOException{
		Statement stmt=conexion.createStatement(); 
		ResultSet resultado = stmt.executeQuery("SELECT * FROM jugadores WHERE Login='"+login+"'");
		return resultado.next();
	}
	public boolean comprobarPass(String login,String str) throws SQLException, IOException{
		Statement stmt=conexion.createStatement(); 
		String pass="";
		ResultSet resultado = stmt.executeQuery("SELECT * FROM jugadores WHERE Login='"+login+"'");
		if(resultado.next()) pass=resultado.getString("Pass");
		return str.equals(pass);
	}
	public void crearUsuario(String login, String pass, String nombre,
			String apellido, int tipojugadoruno, boolean doblar,
			boolean separar, int plantarse) throws SQLException {
		Statement stmt=conexion.createStatement();
		stmt.executeUpdate("INSERT INTO tipo_jugador_black" +
				" (Doblar, Separar, Plantarse) " +
				"VALUES ("+doblar+","+separar+","+plantarse+")");
		stmt.executeUpdate("INSERT INTO jugadores" +
				" (Login, Pass, Nombre, Apellido,TipoJugadorUno,TipoJugadorBlack) " +
				"VALUES ('"+login+"','"+pass+"','"+nombre+"','"+apellido+"',"+tipojugadoruno+","
				+ultNum("tipo_jugador_black","Id_Tipo")+")");
	}
	public void iniciaSesion(String[] nombres) throws SQLException, BDNoHayUsuarios{
		Statement stmt=conexion.createStatement();
		if(nombres.length==0){
			numsesion=0; //para que no pete al cerrar la sesion (si vale 0, la sesi�n no se inici�)
			throw new BDNoHayUsuarios();
		}
		else{
			//1� busco el n�mero de la �ltima partida
			int partidaInicio=ultNum("partidas","NPartida")+1;
			//2� inserto fecha, hora y el numpartida inicial.
			stmt.executeUpdate("INSERT INTO sesion" +
				" (FechaInicioSesion, HoraInicioSesion) " +
				"VALUES (CURDATE(),CURTIME())");
			//3� busco el n� de ult sesion y lo guardo para poder usarlo cuando cierre
			numsesion=ultNum("sesion","NSesion");
			//4� Inserto los logins en la tabla jugadores_sesiones
			for(int i=0; i<nombres.length ; i++){
				stmt.executeUpdate("INSERT INTO jugadores_sesiones" +
						" (Login,NSesion) "
						+"VALUES ('"+nombres[i]+"',"+numsesion+")");
			}
		}
	}
	public void cierraSesion() throws SQLException, BDNoHayUsuarios{
		Statement stmt=conexion.createStatement();
		if(numsesion!=0){
			//1� busco el n�mero de la �ltima partida				
			int partidaFin = ultNum("partidas","NPartida");
			//2� inserto fecha, hora y el numpartida final.
			stmt.executeUpdate("UPDATE sesion " +
					"SET FechaFinSesion=CURDATE(), HoraFinSesion=CURTIME(), " +
					"PartidaFinSesion=" + partidaFin +
					" WHERE NSesion="+ numsesion);
		}
	}
	//devuelve el ultimo valor de un campo num�rico en una tabla.Devuelve 0 si esta vacia.
	public int ultNum(String tabla, String campo) throws SQLException{
		Statement stmt=conexion.createStatement();
		ResultSet resultado = stmt.executeQuery("SELECT * FROM "+tabla+" ORDER BY "+campo+" DESC LIMIT 1");
		if(resultado.next()) return resultado.getInt(campo);
		else return 0;
	}
}
