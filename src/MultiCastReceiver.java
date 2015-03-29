import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class MultiCastReceiver implements Runnable{
	private TextField message;
	private  ListView<String> list;
	private ListView<String> buddy;
	private ObservableList<String> items;
	private ObservableList<String> buddys;
	public MultiCastReceiver() {
		items =FXCollections.observableArrayList();
		buddys = FXCollections.observableArrayList();
	}

	@Override
	public void run() {
		MulticastSocket socket = null;
		DatagramPacket inPacket = null;
		byte[] inBuf = new byte[256];
		try {
			//Prepare to join multicast group
			socket = new MulticastSocket(8888);
			InetAddress address = InetAddress.getByName("224.2.2.3");
			socket.joinGroup(address);

			while (true) {
				inPacket = new DatagramPacket(inBuf, inBuf.length);
				socket.receive(inPacket);
				String msg = new String(inBuf, 0, inPacket.getLength());
				System.out.println("From " + inPacket.getAddress() + " Msg : " + msg);
				items.add("From " + inPacket.getAddress() + " Msg : " + msg);
				list.setItems(items);
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
	}

	public void config(TextField message, ListView<String> list,
			ListView<String> buddy) {
		this.message=message;
		this.list=list;
		this.buddy=buddy;
		
		this.list.setItems(items);
		this.buddy.setItems(buddys);
	}
}
