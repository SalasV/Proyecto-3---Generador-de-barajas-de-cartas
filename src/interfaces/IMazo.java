package interfaces;

import modelos.Mazo;

public interface IMazo {

	public Mazo obtenerMazoPorNombre(String name);
	public void insertarMazo(Mazo mazo);
	public void actualizarMazo(Mazo mazo);

}

