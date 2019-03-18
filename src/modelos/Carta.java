package modelos;

public class Carta {
	private int id;
	private String nombre;
	private int coste;
	private int ataque;
	private int defensa;
	private int valorCarta;
	
	public Carta(int id, String nombre, int coste, int ataque, int defensa, int valorCarta) {
		this.id = id;
		this.nombre = nombre;
		this.coste = coste;
		this.ataque = ataque;
		this.defensa = defensa;
		this.valorCarta = valorCarta;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCoste() {
		return coste;
	}

	public int getAtaque() {
		return ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getValorCarta() {
		return valorCarta;
	}

	@Override
	public String toString() {
		return "Carta [id=" + id + ", nombre=" + nombre + ", coste=" + coste + ", ataque=" + ataque + ", defensa="
				+ defensa + ", valorCarta=" + valorCarta + "]";
	}

}
