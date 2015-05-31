package it.polimi.ingsw.cerridifebbo.model;

public class RedEscapeHatchCard extends EscapeHatchCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object performAction(Player player, Object target, Game game) {
		if (player != null && player instanceof HumanPlayer) {
			HumanPlayer p = (HumanPlayer) player;
			p.getPosition().setPassable(false);
		} else {
			throw new IllegalArgumentException();
		}
		return null;
	}
}
