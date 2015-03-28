import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {

	private ChatView v;
	private static InetAddress adress;
	private static String nick;

	public void start(Stage primaryStage) {

			v = new ChatView(primaryStage, adress, nick);
			v.start();
	}

	public static void main(String[] args) {
		try {

			adress = InetAddress.getByName("233.11.12.13");
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
				nick=args[1];
				launch(args);

				break;
			}



		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
