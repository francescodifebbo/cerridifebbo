package it.polimi.ingsw.cerridifebbo.model;

public class SilenceCard extends SectorCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SilenceCard() {
		super(false);
	}

	@Override
	public Object performAction(Player player, Object target, Game game) {
		return null;
	}
}
