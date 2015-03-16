import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.stage.Stage;

public class Controller {

	//private ChatView v;

	/*public void start(Stage primaryStage) {

			v = new View(primaryStage);
			v.start(this);
	}*/

	public static void main(String[] args) {

		//launch(args);

		try {
			InetAddress adress = InetAddress.getByName("127.0.0.1");
			Server server = new Server(1026,adress);

			server.start();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
