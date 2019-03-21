package modelos;

public class Carta {
	private int id;
	private String name;
	private int summonCost;
	private int attack;
	private int defense;
	private int value;
	
	public Carta(int id, String name, int summonCost, int attack, int defense, int value) {
		this.id = id;
		this.name = name;
		this.summonCost = summonCost;
		this.attack = attack;
		this.defense = defense;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return name;
	}

	public int getCoste() {
		return summonCost;
	}

	public int getAtaque() {
		return attack;
	}

	public int getDefensa() {
		return defense;
	}

	public int getValorCarta() {
		return value;
	}

	@Override
	public String toString() {
		return "Carta [id=" + id + ", nombre=" + name + ", coste=" + summonCost + ", ataque=" + attack + ", defensa="
				+ defense + ", valorCarta=" + value + "]";
	}

}
