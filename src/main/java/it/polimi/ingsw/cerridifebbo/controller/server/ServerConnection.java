package it.polimi.ingsw.cerridifebbo.controller.server;

import it.polimi.ingsw.cerridifebbo.model.User;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.UUID;

public abstract class ServerConnection {
	
	protected final Server server;
	
	public ServerConnection(Server server) {
		this.server = server;
	}
	
	public abstract void start() throws IOException, AlreadyBoundException;

	public abstract void close() throws AccessException, NotBoundException, IOException;

	public abstract boolean registerClientOnServer(UUID id, Object clientInterface);
	
	public abstract String getMoveFromUser(User user);
	
}
