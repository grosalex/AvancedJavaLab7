import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

public class ClientChannel {
	
	MulticastSocket socket;
	InetAddress group;
	String msg;
	int port;
	
	public ClientChannel(int port, InetAddress inet) {
		msg = "blabla";
		this.port = port;
		try {
			this.socket = new MulticastSocket(port);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		this.group = inet;
		
		try {
			socket.joinGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMessage() {
		DatagramPacket pkt = new DatagramPacket(msg.getBytes(), msg.length(), group, port);
		try {
			socket.send(pkt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
