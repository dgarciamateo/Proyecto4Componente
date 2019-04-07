package Objeto;

import java.util.Date;

public class Registro {

	private String user;
	private String bbdd;
	private String tipoSentencia;
	private String sentencia;
	private Date fecha;

	private int numReturns;



	
	public Registro(String user, String bbdd, String tipoSentencia, String sentencia, Date fecha, int numReturns) {
		this.user = user;
		this.bbdd = bbdd;
		this.tipoSentencia = tipoSentencia;
		this.sentencia = sentencia;
		this.fecha = fecha;
		this.numReturns = numReturns;
	}

	
	public Registro() {
		
		
		
	}



	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getBbdd() {
		return bbdd;
	}

	public void setBbdd(String bbdd) {
		this.bbdd = bbdd;
	}

	public String getTipoSentencia() {
		return tipoSentencia;
	}

	public void setTipoSentencia(String tipoSentencia) {
		this.tipoSentencia = tipoSentencia;
	}

	public String getSentencia() {
		return sentencia;
	}

	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumReturns() {
		return numReturns;
	}

	public void setNumReturns(int numReturns) {
		this.numReturns = numReturns;
	}

	@Override
	public String toString() {
		return "USUARIO: " + user + " -  BBDD: " + bbdd + " - TIPO: " + tipoSentencia + " - SENTENCIA: "+ sentencia + " - FECHA: " + fecha + " - NUMRETURNS: " + numReturns;
	}

	
	
}
