package it.polimi.ingsw.cerridifebbo.model;

public class DefenseItemCard extends ItemCard {
	@Override
	public Object performAction(Player player, Object target, Game game) {
		if (player != null && player instanceof HumanPlayer) {
			HumanPlayer p = (HumanPlayer) player;
			p.deleteCard(this);
		} else {
			throw new IllegalArgumentException();
		}
		return null;
	}
}
