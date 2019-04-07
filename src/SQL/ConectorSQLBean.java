package SQL;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import Objeto.Registro;

public class ConectorSQLBean implements Serializable {

	private String ip;
	private String bbdd;
	private String user;
	private String pass;
	private String puerto;
	private Connection conn;

	public ConectorSQLBean() {

	}

	
	
	
	public ConectorSQLBean(String ip,  String puerto, String bbdd, String user, String pass) {
		this.ip = ip;
		this.bbdd = bbdd;
		this.user = user;
		this.pass = pass;
		this.puerto = puerto;
	}


	/*
	 * METODO QUE CONECTA CON LA BASE DE DATOS
	 */
	public Connection conectarBBDD() {

		String url = "jdbc:mysql://" + ip + ":" + puerto + "/" + bbdd + "?useSSL=false";
		conn = null;
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;

		

	}
	
	/*
	 * METODO PARA CERRAR LA CONEXION
	 */
	public void desconectarBBDD() {

		
		try {
			//cerramos la conexion
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		

		

	}

	

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBbdd() {
		return bbdd;
	}

	public void setBbdd(String bbdd) {
		this.bbdd = bbdd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public Connection getConn() {
		return conn;
	}
	
	

}
