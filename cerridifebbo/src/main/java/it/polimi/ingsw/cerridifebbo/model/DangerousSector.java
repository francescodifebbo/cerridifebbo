package it.polimi.ingsw.cerridifebbo.model;

public class DangerousSector extends Sector {

	public DangerousSector(int raw, int column) {
		super(raw, column, true);
	}

	@Override
	public Card playerEnters(Deck deck) {
		return deck.drawSectorCard();
	}

}