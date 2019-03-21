package interfaces;

import java.util.ArrayList;

import modelos.Carta;

public interface ICarta {
	
	public ArrayList<Carta> getCards();
	public Carta getCard(int id);
	
}