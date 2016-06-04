package ro.alex.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Logger;

import ro.alex.message.ChatMessage;

public class ClientHandler implements Runnable{

	
	public ClientHandler(Socket clientConnection){
		this.clinetConnection = clientConnection;
		clientIp = clinetConnection.getLocalAddress();
	}
	
	private Socket clinetConnection;
	private Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private InetAddress clientIp;
	
	
	
	@Override
	public void run() {
		LOGGER.info("A connection has been opend from: "+clientIp.toString());
		try {
			Writer out = new OutputStreamWriter(clinetConnection.getOutputStream());
			Date now = new Date();
			
			ChatMessage message =  new ChatMessage();
			
			message.setFrom("SERVER");
			message.setContent(now.toString());
			message.setTo("All");
			
			
			out.write(message.toString()+"\n");
			out.flush();
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
			
		} finally{
			try {
				clinetConnection.close();
				LOGGER.info("A connetin was closed from: "+clientIp.toString());
			} catch (IOException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		
		
	}

	

}
