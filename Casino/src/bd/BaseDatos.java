package bd;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import utils.BDNoHayUsuarios;
import utils.TipoJugadorBlack;

public class BaseDatos {
	/*
	 ATENCIÓN!!
	 NO USAR LA ENTRADA SALIDA en las funciones, eso se hará fuera, aquí solo lo 
	 referido a base de datos.
	 */
	private static Connection conexion = null;
	
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
			String apellido, int tipojugadoruno, int tipojugadorblack, int plantarse) throws SQLException {
		Statement stmt=conexion.createStatement();
		stmt.executeUpdate("INSERT INTO jugadores" +
				" (Login, Pass, Nombre, Apellido,TipoJugadorUno,TipoJugadorBlack,PlantarseBlack) " +
				"VALUES ('"+login+"','"+pass+"','"+nombre+"','"+apellido+"',"+tipojugadoruno+","
				+tipojugadorblack+","+plantarse+")");
	}
	public int iniciaSesion(String[] nombres) throws SQLException, BDNoHayUsuarios{
		Statement stmt=conexion.createStatement();
		int numsesion;   //necesario devolverlo para cuando se cierre la sesión saber si se habia iniciado.
		if(nombres.length==0){
			numsesion=0;
			throw new BDNoHayUsuarios();
		}
		else{
			//1º inserto fecha, hora
			stmt.executeUpdate("INSERT INTO sesion" +
				" (FechaInicioSesion, HoraInicioSesion) " +
				"VALUES (CURDATE(),CURTIME())");
			//2º busco el nº de ult sesion
			numsesion=ultNum("sesion","NSesion");
			//3º Inserto los logins en la tabla jugadores_sesiones
			for(int i=0; i<nombres.length ; i++){
				stmt.executeUpdate("INSERT INTO jugadores_sesiones" +
						" (Login,NSesion) "
						+"VALUES ('"+nombres[i]+"',"+numsesion+")");
			}
		}
		return numsesion;
	}
	public int iniciaSesionCasino() throws SQLException, BDNoHayUsuarios{
		Statement stmt=conexion.createStatement();
		stmt.executeUpdate("INSERT INTO sesion" +
				" (FechaInicioSesion, HoraInicioSesion) " +
				"VALUES (CURDATE(),CURTIME())");
		return ultNum("sesion","NSesion"); //necesario para cuando se cierre la sesión y para añadir un solo jugador.
	}	

	public void insertaJugadorSesion(String login, int numsesion) throws SQLException{
		Statement stmt=conexion.createStatement();
		stmt.executeUpdate("INSERT INTO jugadores_sesiones" +
				" (Login,NSesion) "
				+"VALUES ('"+login+"',"+numsesion+")");
	}
	
	public void cierraSesion(int numsesion) throws SQLException, BDNoHayUsuarios{
		Statement stmt=conexion.createStatement();
		if(numsesion!=0){
			stmt.executeUpdate("UPDATE sesion " +
					"SET FechaFinSesion=CURDATE(), HoraFinSesion=CURTIME()" +
					" WHERE NSesion="+ numsesion);
		}
	}
	//devuelve el ultimo valor de un campo numérico en una tabla.Devuelve 0 si esta vacia.
	public int ultNum(String tabla, String campo) throws SQLException{
		Statement stmt=conexion.createStatement();
		ResultSet resultado = stmt.executeQuery("SELECT * FROM "+tabla+" ORDER BY "+campo+" DESC LIMIT 1");
		if(resultado.next()) return resultado.getInt(campo);
		else return 0;
	}
	//Insertar Partida
	public void insertarPartida(int mesa,String juego) throws SQLException{
		Statement stmt=conexion.createStatement();
		stmt.executeUpdate("INSERT INTO partidas" +
				" (Mesa,NombreJuego,HoraInicioPartida) "
				+"VALUES ("+mesa+",'"+juego+"',CURTIME())");
	}
	//Para rellenar la tabla jugadores/partida
	public void jugadoresPartida (String[] nombres) throws SQLException{
		Statement stmt=conexion.createStatement();
		int npartida=ultNum("partidas","NPartida");
		for(int i=0; i<nombres.length; i++){
			stmt.executeUpdate("INSERT INTO jugadores_partidas" +
					" (NPartida,Login) "
					+"VALUES ("+npartida+",'"+nombres[i]+"')");
		}		
	}
	//Insertar partidas por sesion	
	public void partidasSesion () throws SQLException{
	Statement stmt=conexion.createStatement();
	int npartida=ultNum("partidas","NPartida");
	int nsesion=ultNum("sesion","NSesion");
		stmt.executeUpdate("INSERT INTO partidas_sesiones" +
				" (NSesion,NPartida) "
				+"VALUES ("+nsesion+",'"+npartida+"')");
	}	
	

	//Inicia partida para uno y black
	public void iniciaPartida(int nmesa, String juego,String[] nombres) throws SQLException{
		insertarPartida(nmesa,juego);
		jugadoresPartida(nombres);
		partidasSesion();
	}
	//Fin partida para uno y black
	public void finPartida() throws SQLException{
		Statement stmt=conexion.createStatement();
		int ultpartida=ultNum("partidas","NPartida");
		stmt.executeUpdate("UPDATE partidas " +
				"SET HoraFinPartida=CURTIME()" +
				" WHERE NPartida="+ ultpartida);	
	}
	//Total de creditos para Black
	public void creditosBlack(String login, float invertidos,float ganados) throws SQLException{
		Statement stmt=conexion.createStatement();
		float suma=0, suma1=0;
		int ultpartida=ultNum("partidas","NPartida");
		ResultSet resultado = stmt.executeQuery("SELECT * FROM jugadores WHERE Login='"+login+"'");
		if(resultado.next()) suma=resultado.getInt("RecuperadoTotal")+ganados;
		stmt.executeUpdate("UPDATE jugadores " +
				"SET RecuperadoTotal="+suma +
				" WHERE Login='"+ login+"'");
		stmt.executeUpdate("UPDATE jugadores_partidas " + //para rellenar partida_jugadores
				"SET Recuperado="+ganados +
				" WHERE Login='"+login+"' && NPartida="+ultpartida);
		ResultSet resultado1 = stmt.executeQuery("SELECT * FROM jugadores WHERE Login='"+login+"'");
		if(resultado1.next())suma1=resultado1.getInt("InvertidoTotal")+invertidos;
		stmt.executeUpdate("UPDATE jugadores " +
				"SET InvertidoTotal="+suma1 +
				" WHERE Login='"+ login+"'");
		stmt.executeUpdate("UPDATE jugadores_partidas " + //para rellenar partida_jugadores
				"SET Invertido="+invertidos +
				" WHERE Login='"+login+"' && NPartida="+ultpartida);
	}
	//Recuperar credito para Uno
	public void recuperarCreditoUno(int c, String login) throws SQLException{
		Statement stmt=conexion.createStatement();
		int suma=0;
		int ultpartida=ultNum("partidas","NPartida");
		ResultSet resultado = stmt.executeQuery("SELECT * FROM jugadores WHERE Login='"+login+"'");
		if(resultado.next()) suma=resultado.getInt("RecuperadoTotal")+c;
		stmt.executeUpdate("UPDATE jugadores " +
				"SET RecuperadoTotal="+suma +
				" WHERE Login='"+login+"'");
		stmt.executeUpdate("UPDATE jugadores_partidas " + 
				"SET Recuperado="+c +
				" WHERE Login='"+login+"' && NPartida="+ultpartida);
	}
	
	//Invertir creditos para el Uno 
	public void invertirCreditoUno(int c, String[] nombres) throws SQLException{
		Statement stmt=conexion.createStatement();
		int suma=0;
		for(int i=0; i<nombres.length; i++){
			ResultSet resultado = stmt.executeQuery("SELECT * FROM jugadores WHERE Login='"+nombres[i]+"'");
			if(resultado.next()) suma=resultado.getInt("InvertidoTotal")+c;
			stmt.executeUpdate("UPDATE jugadores " +
					"SET InvertidoTotal="+suma +
					" WHERE Login='"+nombres[i]+"'");
		}
		for(int i=0; i<nombres.length; i++){
				int ultpartida=ultNum("partidas","NPartida");
				stmt.executeUpdate("UPDATE jugadores_partidas " +
					"SET Invertido="+c +
					" WHERE Login='"+nombres[i]+"' && NPartida="+ultpartida);
		}
	}
	
	public int obtenerTipoJugadorUno(String nombre) throws SQLException{
		Statement stmt=conexion.createStatement();
		ResultSet resultado = stmt.executeQuery("SELECT TipoJugadorUno FROM jugadores WHERE Login='"+nombre+"'");
		if(resultado.next()) return resultado.getInt("TipoJugadorUno");
		else return 0;//si no existe el jugador, devuelve 0
	}
	
	/* OJO!!, es necesario inventare una nueva clase TipoJugadorBlack x q en java no se pueden pasar
	 tipos primitivos por referencia. Es decir, para q la función devuelva las variables tipo y plantarse. */
	public TipoJugadorBlack obtenerTipoJugadorBlack(String nombre)throws SQLException{
		TipoJugadorBlack tipo= new TipoJugadorBlack();
		Statement stmt=conexion.createStatement();
		ResultSet resultado = stmt.executeQuery("SELECT * FROM jugadores WHERE Login='"+nombre+"'");
		if(resultado.next()){
			tipo.setTipo(resultado.getInt("TipoJugadorBlack"));
			tipo.setPlantarse(resultado.getInt("PlantarseBlack"));
		}
		return tipo;
	}
	public String[] extraerTodosLogin () throws SQLException{
		ArrayList<String> lista = new ArrayList<String>();
		Statement stmt=conexion.createStatement();
		ResultSet resultado = stmt.executeQuery("SELECT Login FROM jugadores");
		while(resultado.next())lista.add(resultado.getString("Login"));
		return lista.toArray(new String[lista.size()]);
	}
	public void eliminarUnUsuario(String login) throws SQLException{
		Statement stmt=conexion.createStatement();
		stmt.executeUpdate("DELETE FROM jugadores_partidas WHERE Login='"+login+"'");
		stmt.executeUpdate("DELETE FROM jugadores_sesiones WHERE Login='"+login+"'");
		stmt.executeUpdate("DELETE FROM jugadores WHERE Login='"+login+"'");
	}

	public int obtenerInvertidoTotal (String login) throws SQLException{
		Statement stmt=conexion.createStatement();
		ResultSet resultado = stmt.executeQuery("SELECT InvertidoTotal FROM jugadores WHERE Login='"+login+"'");
		if(resultado.next())return resultado.getInt("InvertidoTotal");
		else return 0; //si no existe el jugador, devuelve 0
	}
	
	public int obtenerRecuperadoTotal (String login) throws SQLException{
		Statement stmt=conexion.createStatement();
		ResultSet resultado = stmt.executeQuery("SELECT RecuperadoTotal FROM jugadores WHERE Login='"+login+"'");
		if(resultado.next())return resultado.getInt("RecuperadoTotal");
		else return 0; //si no existe el jugador, devuelve 0
	}
}