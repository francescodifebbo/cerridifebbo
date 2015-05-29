package it.polimi.ingsw.cerridifebbo.view.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class AlienSectorButton extends SectorButton {

	private static final long serialVersionUID = 1L;
	private static final Color ALIEN_COLOR = Color.GREEN;

	public AlienSectorButton(String label) {
		super(label);
		this.setForeground(ALIEN_COLOR);
	}

}
