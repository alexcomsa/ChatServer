package ro.alex.server;

import java.util.HashMap;
import java.util.logging.Logger;

public class ClientRegistry {
	private Logger LOGGER = Logger.getLogger(this.getClass().getName());
	
	
	public ClientRegistry() {
		registry = new HashMap<Integer, ClientHandler>();
	}
	
	private HashMap<Integer, ClientHandler> registry;
	
	
	public synchronized void  register(Integer clientPort, ClientHandler client){
		LOGGER.info("Adding new client to registry: client port "+clientPort); 
		registry.put(clientPort, client);
	}

}
