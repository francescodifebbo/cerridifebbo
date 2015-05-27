package it.polimi.ingsw.cerridifebbo.model;

public class AdrenalineItemCard extends ItemCard {

	private static final int ADRENALINEMOVEMENT = 2;

	@Override
	public Object performAction(Player player, Object target, Game game) {
		if (player != null && player instanceof HumanPlayer) {
			HumanPlayer p = (HumanPlayer) player;
			p.setMaxMovement(ADRENALINEMOVEMENT);
			p.deleteCard(this);
		} else {
			throw new IllegalArgumentException();
		}
		return null;
	}
}
