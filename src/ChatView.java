import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ChatView {

	private Stage primaryStage;
	private Scene vb;
	private ClientChannel client;
	private InetAddress address;
	private String nick;
	private boolean debug;
	private MultiCastClient multiCastClient;
	private boolean multicast=false;

	public ChatView(Stage primaryStage, String adress, int port, String arg, boolean d){
		this.debug = d;
		this.primaryStage=primaryStage;
		try {
			this.address=InetAddress.getByName(adress);
		} catch (UnknownHostException e) {
			if(debug) {
				Logger log = Logger.getLogger(Controller.class.getName());
				ConsoleHandler ch =  new ConsoleHandler();
				log.addHandler(ch);
				log.severe(e.getMessage());
			}
		}
		nick=arg;
	}
	public ChatView(Stage primaryStage, String arg,MultiCastClient c, boolean d){
		this.primaryStage=primaryStage;
		nick=arg;
		multiCastClient = c;
		multicast=true;
		this.debug = d;
	}
	public void start() {
		vb = new Scene(new VBox(),800,600);
		Label labelMessage = new Label("Your Message");
		TextField message = new TextField();
		Button b = new Button("Send");

		ListView<String> list = new ListView<String>();
		list.setPrefSize(600, 400);
		list.setEditable(true);
		ListView<String> buddy = new ListView<String>();
		list.setPrefSize(200, 400);
		list.setEditable(true);
		HBox hbTop = new HBox();
		HBox hbBottom = new HBox();

		hbTop.getChildren().addAll(buddy,list);
		hbBottom.getChildren().addAll(labelMessage,message,b);

		((VBox) vb.getRoot()).getChildren().addAll(hbTop,hbBottom);

		primaryStage.setScene(vb);
		primaryStage.show();


		if(multicast){
			multiCastClient.config(message,list,buddy);
			b.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					multiCastClient.send();
				}
			});
		}
		else{
			b.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					client.send(message.getText());
					message.setText("");
				}
			});
			try {
				client = new ClientChannel(1026, address, nick,message,list,buddy,debug);
			} catch (IOException | InterruptedException e1) {
				if(debug) {
					Logger log = Logger.getLogger(ChatView.class.getName());
					ConsoleHandler ch =  new ConsoleHandler();
					log.addHandler(ch);
					log.severe(e1.getMessage());
				}
			}
		}




	}
}
