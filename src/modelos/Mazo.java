package modelos;

import java.util.ArrayList;

public class Mazo {
	private String nombre;
	private int valorMazo;
	private ArrayList<Carta> cartas;

	public String getNombre() {
		return nombre;
	}

	public int getValorMazo() {
		return valorMazo;
	}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setValorMazo(int valorMazo) {
		this.valorMazo = valorMazo;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}

	@Override
	public String toString() {
		return "Mazo [nombre=" + nombre + ", valorMazo=" + valorMazo + ", cartas=" + cartas + "]";
	}
}
