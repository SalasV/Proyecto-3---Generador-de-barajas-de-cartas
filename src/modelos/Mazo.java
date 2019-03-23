package modelos;

import java.util.ArrayList;

public class Mazo {
	private String deckName;
	private int deckValue;
	private ArrayList<Carta> deck;

	public Mazo() {
	}

	public Mazo(String deckName, int deckValue, ArrayList<Carta> deck) {
		this.deckName = deckName;
		this.deckValue = deckValue;
		this.deck = deck;
	}

	public String getNombre() {
		return deckName;
	}

	public int getValorMazo() {
		return deckValue;
	}

	public ArrayList<Carta> getCartas() {
		return deck;
	}

	public void setNombre(String deckName) {
		this.deckName = deckName;
	}

	public void setValorMazo(int deckValue) {
		this.deckValue = deckValue;
	}

	public void setCartas(ArrayList<Carta> deck) {
		this.deck = deck;
	}

	@Override
	public String toString() {
		return "Mazo [nombre=" + deckName + ", valorMazo=" + deckValue + ", cartas=" + deck + "]";
	}
}
