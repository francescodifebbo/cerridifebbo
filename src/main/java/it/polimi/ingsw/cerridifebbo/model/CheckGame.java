package it.polimi.ingsw.cerridifebbo.model;

import java.util.ArrayList;

public class CheckGame extends GameState {

	public CheckGame(Game game) {
		super(game);
	}

	@Override
	public void handle() {
		boolean allAlienKilled = true;
		boolean allHumanNotInGame = true;
		for (User u : game.getUsers()) {
			if (u.getPlayer() instanceof HumanPlayer) {
				HumanPlayer human = (HumanPlayer) u.getPlayer();
				if (human.isAlive() && !human.isEscaped()) {
					allHumanNotInGame = false;

				}
			} else if (u.getPlayer() instanceof AlienPlayer) {
				AlienPlayer alien = (AlienPlayer) u.getPlayer();
				if (alien.isAlive()) {
					allAlienKilled = false;

				}
			}
		}
		if (allHumanNotInGame || allAlienKilled) {
			game.setState(new EndGame(game));
			game.run();
		}

	}
}