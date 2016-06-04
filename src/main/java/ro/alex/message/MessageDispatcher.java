package ro.alex.message;

public class MessageDispatcher extends MessageBox<ChatMessage> {

	
	
	@Override
	public void addMessage(ChatMessage message) {
		messageContainer.add(message);
;		
	}

	@Override
	public void removeMessage(ChatMessage message) {
		messageContainer.remove(message);
	}

	 
}
