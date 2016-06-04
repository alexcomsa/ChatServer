package ro.alex;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import ro.alex.server.ChatServer;




public class Main {
	private static Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());
	
	public static void main(String[] args)  {
		ServerSocket server = null;
		try {
			server = new ServerSocket(9999);
		
		ExecutorService es = Executors.newFixedThreadPool(8);
		while(true){
			Socket clientConnection = null;
			LOGGER.info("Waiting for connections");
			
			try {
				
				while((clientConnection = server.accept())!=null){
					LOGGER.info("Client connection was establised: "+clientConnection.toString());
					es.submit(new ChatServer(clientConnection));
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
