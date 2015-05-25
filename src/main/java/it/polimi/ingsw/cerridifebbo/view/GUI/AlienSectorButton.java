package it.polimi.ingsw.cerridifebbo.view.GUI;

import java.awt.Color;

public class AlienSectorButton extends SectorButton {

	private static final Color ALIEN_COLOR = Color.GREEN;

	public AlienSectorButton(String label) {
		super(label);
		this.setForeground(ALIEN_COLOR);
	}
}