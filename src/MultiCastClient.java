public class MultiCastClient {
	MultiCastReceiver receiver;
	MultiCastSender sender;
	Thread thread;
	
	public MultiCastClient() {
		receiver = new MultiCastReceiver();
		
		thread = new Thread(receiver);
		thread.start();
		sender = new MultiCastSender();
	}
}
