import java.io.IOException;
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

			InetAddress adress = InetAddress.getByName("233.11.12.13");
			ServerChannel server = new ServerChannel(adress,1026);
			
			try {
				server.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
