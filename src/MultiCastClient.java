import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

	public void config(TextField message, ListView<String> list,
			ListView<String> buddy) {
		receiver.config(message,list,buddy);
		sender.config(message,list,buddy);
	}

	public void send() {
		sender.send();
	}
}
