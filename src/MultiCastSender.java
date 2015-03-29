import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MultiCastSender {
	private TextField message;
	private ListView<String> list;
	private ListView<String> buddy;
	private ObservableList<String> items;
	private ObservableList<String> buddys;
	private byte[] outBuf;
	private DatagramSocket socket = null;
	final int PORT = 8888;
	private boolean debug;

	public MultiCastSender(boolean d){
		items =FXCollections.observableArrayList();
		buddys = FXCollections.observableArrayList();
		DatagramPacket outPacket = null;
		this.debug = d;


		try {
			socket = new DatagramSocket();	
			String msg ="";
/*
			while (true) {
				BufferedReader entreeClavier;

				try {
					entreeClavier = new BufferedReader(new InputStreamReader(System.in));
					msg = entreeClavier.readLine();
				}    catch (Exception exc) {
					if(debug) {
						Logger log = Logger.getLogger(Controller.class.getName());
						ConsoleHandler ch =  new ConsoleHandler();
						log.addHandler(ch);
						log.severe(exc.getMessage());
					}
				}
				if(msg!="") {
					outBuf = msg.getBytes();

					//Send to multicast IP address and port
					InetAddress address = InetAddress.getByName("224.2.2.3");
					outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);

					socket.send(outPacket);
				}

			}*/
		} catch (IOException ioe) {
			if(debug) {
				Logger log = Logger.getLogger(Controller.class.getName());
				ConsoleHandler ch =  new ConsoleHandler();
				log.addHandler(ch);
				log.severe(ioe.getMessage());
			}
		}
	}


	public void config(TextField message, ListView<String> list,
			ListView<String> buddy) {
		this.message=message;
		this.list=list;
		this.buddy=buddy;
		
		list.setItems(items);
		buddy.setItems(buddys);
	}


	public void send() {
		outBuf = message.getText().getBytes();
		DatagramPacket outPacket = null;

		//Send to multicast IP address and port
		InetAddress address;
		try {
			address = InetAddress.getByName("224.2.2.3");
			outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);

			socket.send(outPacket);		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
