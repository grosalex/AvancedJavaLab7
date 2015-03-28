import java.io.IOException;
import java.net.InetAddress;

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

	
	public ChatView(Stage primaryStage, InetAddress adress, String arg){
		this.primaryStage=primaryStage;
		try {
			client = new ClientChannel(1026, adress, arg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
		hbTop.getChildren().addAll(list,buddy);
		hbBottom.getChildren().addAll(labelMessage,message,b);
		
		((VBox) vb.getRoot()).getChildren().addAll(hbTop,hbBottom);

		primaryStage.setScene(vb);
		primaryStage.show();
	}
}
