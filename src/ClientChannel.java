import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientChannel {

	MulticastSocket socket;
	InetAddress group;
	String msg;
	String myNick;
	int port;

	public ClientChannel(int port, InetAddress inet, String nick) throws IOException, InterruptedException {
		myNick=nick;
		InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);

		SocketChannel client = SocketChannel.open(hostAddress);
		ByteBuffer tmp=ByteBuffer.allocate(256);
		
		client.read(tmp);
		String output = new String(tmp.array()).trim();
		client.write(ByteBuffer.wrap(nick.getBytes()));
		System.out.println(output);

		for(;;){
			
		    BufferedReader entreeClavier;
		    
		    try {
		       entreeClavier = new BufferedReader(new InputStreamReader(System.in));
		       while(true) {
					  	String texte = entreeClavier.readLine();
						ByteBuffer buff = ByteBuffer.wrap(texte.getBytes());
						client.write(buff);
		      }
		    }    catch (Exception exc) {
		        System.out.println(exc);
		    }


		}


	}


}
