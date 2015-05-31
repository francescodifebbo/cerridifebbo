package it.polimi.ingsw.cerridifebbo.controller.client;

import it.polimi.ingsw.cerridifebbo.controller.common.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private static final Logger LOG = Logger.getLogger(Client.class.getName());

	private static String NETWORK_INTERFACE_SELECTION = "Select '1' for RMI interface, '2' for socket interface";
	private static String GRAPHICS_SELECTION = "Select '1' for GUI graphics, '2' for cli graphics";
	private static String CHOICE_ONE = "1";
	private static String CHOICE_TWO = "2";

	public static void main(String[] args) throws IOException, NotBoundException {
		new Client().run();
	}

	private void run() throws IOException {
		NetworkInterface network = null;
		Graphics graphics;
		boolean chosen = false;
		while (!chosen) {
			String line = readLine(NETWORK_INTERFACE_SELECTION);
			if (CHOICE_ONE.equals(line)) {
				network = NetworkInterfaceFactory.getInterface(NetworkInterfaceFactory.RMI_INTERFACE);
				chosen = true;
			}
			if (CHOICE_TWO.equals(line)) {
				network = NetworkInterfaceFactory.getInterface(NetworkInterfaceFactory.SOCKET_INTERFACE);
				chosen = true;
			}
		}
		try {
			network.connect();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			Application.exitError();
		}
		chosen = false;
		while (!chosen) {
			String line = readLine(GRAPHICS_SELECTION);
			if (CHOICE_ONE.equals(line)) {
				graphics = GraphicsFactory.getInterface(GraphicsFactory.GUI_INTERFACE);
				chosen = true;
			}
			if (CHOICE_TWO.equals(line)) {
				graphics = GraphicsFactory.getInterface(GraphicsFactory.CLI_INTERFACE);
				chosen = true;
			}
		}
	}

	private static String readLine(String format, Object... args) throws IOException {
		if (System.console() != null) {
			return System.console().readLine(format, args);
		}
		Application.println(String.format(format, args));

		BufferedReader br = null;
		InputStreamReader isr = null;
		String read = null;

		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		read = br.readLine();

		return read;
	}
}
