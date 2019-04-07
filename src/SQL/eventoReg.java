package SQL;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import Objeto.Registro;

public class eventoReg implements PropertyChangeListener{

	private static ArrayList<Registro> regis = new ArrayList<Registro>();
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {

		regis.add((Registro) e.getNewValue());
		
	}

	public static ArrayList<Registro> getRegis() {
		return regis;
	}
	
	//Metodo para coger un solo registro de todo el array pasandole un indice
	public static Registro getIndice(int i) {
		return regis.get(i);
	}

	public static void setRegis(ArrayList<Registro> regis) {
		eventoReg.regis = regis;
	}
	
	


}
