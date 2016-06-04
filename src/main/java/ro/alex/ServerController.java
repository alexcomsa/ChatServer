package ro.alex;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import ro.alex.server.ClientHandler;
import ro.alex.server.ClientRegistry;




public class ServerController {
	private static Logger LOGGER = Logger.getLogger(ServerController.class.getCanonicalName());
	
	
	
	public static void main(String[] args)  {
		ServerSocket server = null;
		ExecutorService es = Executors.newFixedThreadPool(8);
		ClientRegistry registry =  new ClientRegistry();
		
		try {
			server = new ServerSocket(9999);
		
		
		while(true){
			
			Socket clientConnection = null;
			LOGGER.info("Waiting for connections");
			
			try {
				
				while((clientConnection = server.accept())!=null){
					LOGGER.info("Client connection was establised: "+clientConnection.toString());
					
					
					ClientHandler handler =  new ClientHandler(clientConnection);
					registry.register(clientConnection.getPort(), handler);
					
					es.submit((handler));
				}
				
			} catch (IOException e) {
				
				LOGGER.severe(e.getMessage());
			}
		}
} catch (IOException e) {
			
			LOGGER.severe(e.getMessage());
		}finally{
			try {
				server.close();
			} catch (IOException e) {
				
				LOGGER.severe(e.getMessage());
			}
		}

}
}
