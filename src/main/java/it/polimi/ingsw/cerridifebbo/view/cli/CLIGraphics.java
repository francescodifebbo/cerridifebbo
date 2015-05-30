package it.polimi.ingsw.cerridifebbo.view.cli;

import it.polimi.ingsw.cerridifebbo.controller.client.Graphics;
import it.polimi.ingsw.cerridifebbo.controller.common.Application;
import it.polimi.ingsw.cerridifebbo.model.AlienSector;
import it.polimi.ingsw.cerridifebbo.model.Card;
import it.polimi.ingsw.cerridifebbo.model.DangerousSector;
import it.polimi.ingsw.cerridifebbo.model.EscapeHatchSector;
import it.polimi.ingsw.cerridifebbo.model.Game;
import it.polimi.ingsw.cerridifebbo.model.HumanSector;
import it.polimi.ingsw.cerridifebbo.model.Map;
import it.polimi.ingsw.cerridifebbo.model.Move;
import it.polimi.ingsw.cerridifebbo.model.Player;
import it.polimi.ingsw.cerridifebbo.model.Sector;
import it.polimi.ingsw.cerridifebbo.model.SecureSector;
import it.polimi.ingsw.cerridifebbo.model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CLIGraphics extends Graphics {

	private static final String RESET_COLOR = "\u001B[0m";
	private static final String SECURE_SECTOR = "\u001B[37m";
	private static final String DANGEROUS_SECTOR = "\u001B[31m";
	private static final String ALIEN_SECTOR = "\u001B[32m";
	private static final String HUMAN_SECTOR = "\u001B[34m";
	private static final String ESCAPE_HATCH_SECTOR = "\u001B[33m";
	private static final String START_TURN_MESSAGE = "Start Turn";
	private static final String END_TURN_MESSAGE = "End Turn";

	private static final String CHOICE_ONE = "1";
	private static final String CHOICE_TWO = "2";
	private static final String CHOICE_THREE = "3";
	private static final String CHOICE_FOUR = "4";
	private static final String MOVE_OPTIONS = "What do you want to do?" + "\n"
			+ CHOICE_ONE + Move.ATTACK + "\n" + CHOICE_TWO + Move.MOVEMENT
			+ "\n" + CHOICE_THREE + Move.USEITEMCARD + "\n" + CHOICE_FOUR
			+ Move.FINISH;

	private static final String SECTOR_SELECTION = "Which sector?";
	private static final String CARD_SELECTION = "Which card?";

	private Player player;
	private Map map;

	@Override
	public void initialize(Map map, Player player) {
		this.player = player;
		this.map = map;
		printMap();
		printPlayer();
	}

	private void printMap() {
		for (int i = 0; i < Map.COLUMNMAP; i = i + 2) {
			System.out.print(" ___    ");
		}
		System.out.println();
		for (int i = 0; i < Map.ROWMAP; i++) {
			for (int j = 0; j < Map.COLUMNMAP; j = j + 2) {
				Sector currentCell = map.getCell(i, j);
				System.out.print("/");
				if (currentCell != null) {
					System.out.print(printTypeOfSector(currentCell)
							+ currentCell.toString() + RESET_COLOR);
				} else {
					System.out.print("   ");

				}
				if (j != Map.COLUMNMAP - 1)
					System.out.print("\\___");
			}
			Application.print("\\");
			for (int j = 1; j < Map.COLUMNMAP; j = j + 2) {
				System.out.print("\\___/");
				Sector currentCell = map.getCell(i, j);
				if (currentCell != null) {
					System.out.print(printTypeOfSector(currentCell)
							+ currentCell.toString() + RESET_COLOR);
				} else {
					System.out.print("   ");
				}

			}
			Application.print("\\___/");
		}
		System.out.print("    \\___/");
		for (int i = 3; i < Map.COLUMNMAP; i = i + 2) {
			System.out.print("   \\___/");
		}
		Application.print("");

	}

	private void printPlayer() {
		printPlayerPosition();
		printCardPlayer();

	}

	private void printPlayerPosition() {
		Application.print("player position: " + player.getPosition());
	}

	private String printTypeOfSector(Sector currentCell) {
		if (currentCell instanceof SecureSector) {
			return SECURE_SECTOR;
		} else if (currentCell instanceof DangerousSector) {
			return DANGEROUS_SECTOR;
		} else if (currentCell instanceof AlienSector) {
			return ALIEN_SECTOR;
		} else if (currentCell instanceof HumanSector) {
			return HUMAN_SECTOR;
		} else if (currentCell instanceof EscapeHatchSector) {
			return ESCAPE_HATCH_SECTOR;
		} else
			return null;
	}

	private void printCardPlayer() {
		System.out.print("player cards: ");
		int nCard = player.getOwnCards().size();
		if (nCard != 0) {
			for (int i = 0; i < nCard; i++) {
				System.out.print(player.getOwnCards().get(i) + " ");
			}
			Application.print("");
		} else {
			Application.print("no card!");
		}
	}

	public static void main(String args[]) {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User(UUID.randomUUID()));
		users.add(new User(UUID.randomUUID()));
		users.add(new User(UUID.randomUUID()));
		Game game = new Game(null, users);
		game.run();
		CLIGraphics cli = new CLIGraphics();
		cli.initialize(game.getMap(), game.getUsers().get(0).getPlayer());
		cli.startTurn();
		cli.declareMove();
	}

	@Override
	public void sendMessage(String message) {
		Application.print(message);
	}

	@Override
	public void startTurn() {
		printMap();
		Application.print(START_TURN_MESSAGE);

	}

	@Override
	public void endTurn() {
		Application.print(END_TURN_MESSAGE);

	}

	@Override
	public void declareMove() {
		printMap();
		String move = null;
		Scanner in = new Scanner(System.in);
		do {
			Application.print(MOVE_OPTIONS);
			String line = in.next();
			if (line.equals(CHOICE_ONE)) {
				move = Move.ATTACK + " " + player.getPosition();
				break;
			}
			if (line.equals(CHOICE_TWO)) {
				move = Move.MOVEMENT;
				Application.print(SECTOR_SELECTION);
				line = in.next();
				move = move + " " + line;
				break;
			}
			if (line.equals(CHOICE_THREE)) {
				move = Move.USEITEMCARD;
				Application.print(CARD_SELECTION);
				printCardPlayer();
				line = in.next();
				move = move + " " + line;
				break;
			}
			if (line.equals(CHOICE_FOUR)) {
				move = Move.FINISH;
				break;
			}
		} while (move != null);
		in.close();
		// getClient().sendToServer(move);
	}

	@Override
	public void declareSector() {
		printMap();
		Application.print(SECTOR_SELECTION);
		String move = null;
		Scanner in = new Scanner(System.in);
		move = in.next();
		in.close();
		// getClient().sendToServer(move);

	}

	@Override
	public void updatePlayerPosition(Player player) {
		this.player = player;
		printPlayerPosition();

	}

	@Override
	public void declareCard() {
		printCardPlayer();
		Application.print(CARD_SELECTION);
		String move = null;
		Scanner in = new Scanner(System.in);
		move = in.next();
		in.close();
		// getClient().sendToServer(move);
	}

	@Override
	public void deletePlayerCard(Player player, Card card) {
		this.player = player;
		printCardPlayer();
	}

	@Override
	public void addPlayerCard(Player player, Card card) {
		this.player = player;
		printCardPlayer();

	}
}
