import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ClientChannel {

	Socket socket;
	InetAddress group;
	String msg;
	String myNick;
	private SocketChannel client;
	int port;
	private TextField message;
	private ListView<String> list;
	private ListView<String> buddy;
	private ObservableList<String> items;
	private ObservableList<String> buddys;

	public ClientChannel(int port, InetAddress inet, String nick, TextField message, ListView<String> list, ListView<String> buddy) throws IOException, InterruptedException {
		myNick=nick;
		this.message = message;
		this.list=list;
		this.buddy=buddy;
		
		items =FXCollections.observableArrayList();
		buddys = FXCollections.observableArrayList();
		list.setItems(items);
		buddy.setItems(buddys);
		InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);

		client = SocketChannel.open(hostAddress);
		ByteBuffer tmp=ByteBuffer.allocate(256);
		client.write(ByteBuffer.wrap(nick.getBytes()));
		Thread read = new Thread(new Runnable() {
			@Override
			public void run() {
				for(;;){
					try {
						client.read(tmp);
						String output = new String(tmp.array());
						System.out.println("output :" +output);
							items.add(output);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		read.start();
		/*for(;;){
			
		    BufferedReader entreeClavier;
		    
		    try {
		       entreeClavier = new BufferedReader(new InputStreamReader(System.in));
		       while(true) {
					  	String texte = entreeClavier.readLine();
						ByteBuffer buff = ByteBuffer.wrap((myNick+ " : " +texte).getBytes());
						client.write(buff);
		      }
		    }    catch (Exception exc) {
		        System.out.println(exc);
		    }


		}*/


	}

	public void send(String text) {
		ByteBuffer buff = ByteBuffer.wrap((myNick+ " : " +text).getBytes());
		items.add(myNick+ " : " +text);
		try {
			client.write(buff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
