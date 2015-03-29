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
			switch(args[0]){
			case "s": 
				try {
					ServerChannel server = new ServerChannel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "c":
				ClientChannel client;
				try {
					client = new ClientChannel(1026, adress, args[1]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "m":
				MultiCastClient c = new MultiCastClient();
				break;
			}
			



		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
