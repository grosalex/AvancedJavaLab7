public class MultiCastClient {
	private MultiCastReceiver receiver;
	private MultiCastSender sender;
	private Thread thread;
	
	public MultiCastClient(boolean d) {
		receiver = new MultiCastReceiver(d);
		thread = new Thread(receiver);
		thread.start();
		sender = new MultiCastSender(d);
	}
}
