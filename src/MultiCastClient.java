import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

	public void config(TextField message, ListView<String> list,
			ListView<String> buddy) {
		receiver.config(message,list,buddy);
		sender.config(message,list,buddy);
	}

	public void send() {
		sender.send();
	}
}
