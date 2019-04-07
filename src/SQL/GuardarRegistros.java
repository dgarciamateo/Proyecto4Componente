package SQL;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import Objeto.Registro;

public class GuardarRegistros {

	private transient PropertyChangeSupport propertyChangeListeners = new PropertyChangeSupport(this);
	private static ConectorSQLBean c = new ConectorSQLBean();

	public GuardarRegistros() {
		addPropertyChangeListener(new eventoReg());
	}

	/*
	 * METODO PARA REALIZAR UNA SENTENCIA SQL
	 * 
	 * @param conector El parametro conector es un objeto de la clase
	 * ConectorSQLBean
	 * 
	 * @param consulta El parametro consulta es un string que contiene la consulta
	 * que se desea hacer
	 * 
	 * @param usu El parametro usu es un String que contiene el nombre de usuario
	 * 
	 * @param bd El parametro bd es un String que contiene el nombre de la base de
	 * datos donde se esta trabajando
	 */
	public void sentencia(ConectorSQLBean conector, String consulta, String usu, String bd) {

		String consultaArray[] = consulta.split(" ");

		Connection conn = conector.conectarBBDD();

		// Comprobaremos que tipo de consulta es cada vez que se realize una.
		if (consultaArray[0].equalsIgnoreCase("SELECT")) {

			selectCons(consulta, conn, usu, bd);

		} else if (consultaArray[0].equalsIgnoreCase("INSERT")) {

			insertCons(consulta, conn, usu, bd);

		} else if (consultaArray[0].equalsIgnoreCase("UPDATE")) {

			updateCons(consulta, conn, usu, bd);

		} else if (consultaArray[0].equalsIgnoreCase("DELETE")) {

			deleteCons(consulta, conn, usu, bd);

		} else if (consultaArray[0].equalsIgnoreCase("CALL")) {

			procedureCons(consulta, conn, usu, bd);

		} else {

			System.err.println(
					"La sentencia es erronea, porfavor introduce alguna de las posibles; SELECT, INSERT, UPDATE, DELETE, CALL PROCEDURE");

		}

	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChangeListeners.removePropertyChangeListener(l);
	}

	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChangeListeners.addPropertyChangeListener(l);
	}

	public void selectCons(String consulta, Connection conn, String usu, String bd) {

		try {

			Date fecha = new Date();
			String tipo = "SELECT";
			// El contador nos mostrara el numero de rows que ha encontrado
			int contador = 0;

			// create the java statement
			Statement st = (Statement) conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = (ResultSet) st.executeQuery(consulta);

			while (rs.next()) {

				contador++;

			}

			/*
			 * Creamos una linia de registro pasandole los paramentros pertinentes al objeto
			 * Registro; El usuario que esta realizando la consulta, la base de datos donde
			 * se esta realizando la consulta, el tipo de consulta, en este caso un SELECT,
			 * La consulta en si, La fecha y hora de cuando se esta realizando dicha
			 * consulta Y por ultimo, los registros que nos devuelve el select.
			 */

			Registro r = new Registro(usu, bd, tipo, consulta, fecha, contador);

			// Con esto llamamos al evento que esta escuchando, y le pasamos el objeto
			// Registro que hemos creado al hacer correctamente la sentencia sql
			propertyChangeListeners.firePropertyChange("Registro", null, r);

			st.close();
		} catch (Exception e) {
			System.err.println("El SELECT no se ha podido realizar correctamente.");
			System.err.println(e.getMessage());
		}

	}

	public void updateCons(String consulta, Connection conn, String usu, String bd) {

		try {

			Date fecha = new Date();
			String tipo = "UPDATE";
			// El contador nos mostrara el numero de rows que ha encontrado

			// create the java statement
			Statement st = (Statement) conn.createStatement();

			st.executeUpdate(consulta);
			int contador = st.getUpdateCount();
			/*
			 * Creamos una linia de registro pasandole los paramentros pertinentes al objeto
			 * Registro; El usuario que esta realizando la consulta, la base de datos donde
			 * se esta realizando la consulta, el tipo de consulta, en este caso un UPDATE,
			 * La consulta en si, La fecha y hora de cuando se esta realizando dicha
			 * consulta El contador en este caso devolvera un 1 si se ha hecho la consulta
			 * correctamente
			 * 
			 */

			Registro r = new Registro(usu, bd, tipo, consulta, fecha, contador);

			// Con esto llamamos al evento que esta escuchando, y le pasamos el objeto
			// Registro que hemos creado al hacer correctamente la sentencia sql
			propertyChangeListeners.firePropertyChange("Registro", null, r);

			st.close();
		} catch (Exception e) {
			System.err.println("El UPDATE no se ha podido realizar correctamente.");
			System.err.println(e.getMessage());
		}

	}

	public void deleteCons(String consulta, Connection conn, String usu, String bd) {

		try {
			Date fecha = new Date();
			String tipo = "DELETE";
			// El contador nos mostrara el numero de rows que ha encontrado

			// create the java statement
			Statement st = (Statement) conn.createStatement();

			st.executeUpdate(consulta);

			int contador = st.getUpdateCount();
			/*
			 * Creamos una linia de registro pasandole los paramentros pertinentes al objeto
			 * Registro; El usuario que esta realizando la consulta, la base de datos donde
			 * se esta realizando la consulta, el tipo de consulta, en este caso un DELETE,
			 * La consulta en si, La fecha y hora de cuando se esta realizando dicha
			 * consulta El contador en este caso devolvera un 1 si se ha hecho la consulta
			 * correctamente
			 * 
			 */

			Registro r = new Registro(usu, bd, tipo, consulta, fecha, contador);

			// Con esto llamamos al evento que esta escuchando, y le pasamos el objeto
			// Registro que hemos creado al hacer correctamente la sentencia sql
			propertyChangeListeners.firePropertyChange("Registro", null, r);

			st.close();
		} catch (Exception e) {
			System.err.println("El DELETE no se ha podido realizar correctamente.");
			System.err.println(e.getMessage());
		}

	}

	public void insertCons(String consulta, Connection conn, String usu, String bd) {

		try {
			Date fecha = new Date();
			String tipo = "INSERT";

			Statement st = (Statement) conn.createStatement();

			st.executeUpdate(consulta);
			int contador = st.getUpdateCount();
			/*
			 * Creamos una linia de registro pasandole los paramentros pertinentes al objeto
			 * Registro; El usuario que esta realizando la consulta, la base de datos donde
			 * se esta realizando la consulta, el tipo de consulta, en este caso un INSERT,
			 * La consulta en si, La fecha y hora de cuando se esta realizando dicha
			 * consulta El contador en este caso devolvera un 1 si se ha hecho la consulta
			 * correctamente
			 * 
			 */

			Registro r = new Registro(usu, bd, tipo, consulta, fecha, contador);

			// Con esto llamamos al evento que esta escuchando, y le pasamos el objeto
			// Registro que hemos creado al hacer correctamente la sentencia sql
			propertyChangeListeners.firePropertyChange("Registro", null, r);

			st.close();
		} catch (Exception e) {
			System.err.println("El INSERT no se ha podido realizar correctamente.");
			System.err.println(e.getMessage());
		}

	}

	public void procedureCons(String consulta, Connection conn, String usu, String bd) {

		try {
			int contador;
			Date fecha = new Date();
			String tipo = "PROCEDURE";

			Statement st = (Statement) conn.createStatement();

			st.execute(consulta);

			if (st.execute(consulta) == true) {
				contador = 1;
			} else {
				contador = 0;
			}

			Registro r = new Registro(usu, bd, tipo, consulta, fecha, contador);

			// Con esto llamamos al evento que esta escuchando, y le pasamos el objeto
			// Registro que hemos creado al hacer correctamente la sentencia sql
			propertyChangeListeners.firePropertyChange("Registro", null, r);

			st.close();
		} catch (Exception e) {
			System.err.println("El PROCEDURE no se ha podido realizar correctamente.");
			System.err.println(e.getMessage());
		}

	}

	public void printAllRegis() {

		eventoReg e = new eventoReg();

		ArrayList<Registro> regisAux = e.getRegis();

		for (int i = 0; i < regisAux.size(); i++) {

			System.out.println(regisAux.get(i).toString());
		}

	}

	/*
	 * METODO PARA MOSTRAR POR PANTALLA LOS REGISTROS MEDIANTE FILTROS FACILITADOS
	 * POR EL USUARIO
	 * 
	 * @param bbdd El parametro bbdd es un String que contiene el nombre de la base
	 * de datos
	 * 
	 * @param usu El parametro usu es un String que contiene el nombre de usuario
	 * 
	 * @param tipo El parametro tipo es un string que contiene el tipo de consulta
	 */
	public void printRegis(String bbdd, String usu, String tipo) {

		eventoReg e = new eventoReg();

		ArrayList<Registro> regisAux = e.getRegis();

		// Hacemos un bucle que recorra el arraylist
		for (int i = 0; i < regisAux.size(); i++) {

			// Solo imprimiremos los registros que se hayan hecho en la bbdd especificada
			// por el usuario
			if (bbdd.equalsIgnoreCase(regisAux.get(i).getBbdd())) {

				// Este if controla los otros parametros por los que quieren filtrar
				if (usu.equals(regisAux.get(i).getUser())) {

					// Si el usuario quiere filtrar por usuario y por tipo entonces entrara en este
					// if y lo printara
					if (tipo.equals("")) {

						System.out.println("SENTENCIA: " + regisAux.get(i).getSentencia() + " ~~ FECHA: "
								+ regisAux.get(i).getFecha() + " ~~ TIPO: " + regisAux.get(i).getTipoSentencia());
						// Si entra aqui es que el usuario solo ha querido buscar por bbdd y usuario,
						// asi que el tipo de sentencia lo ha dejado nulo

					} else if (tipo.equalsIgnoreCase(regisAux.get(i).getTipoSentencia())) {
						System.out.println("SENTENCIA: " + regisAux.get(i).getSentencia() + " ~~ FECHA: "
								+ regisAux.get(i).getFecha());
					}
				} else if (usu.equals("")) {

					if (tipo.equalsIgnoreCase(regisAux.get(i).getTipoSentencia())) {

						System.out.println("SENTENCIA: " + regisAux.get(i).getSentencia() + " ~~ FECHA: "
								+ regisAux.get(i).getFecha() + " ~~ USUARIO: " + regisAux.get(i).getUser());

					} else if (tipo.equals("")) {

						System.out.println("");

					}
				}

			} else {

			}
		}

		System.out.println();

	}

}
