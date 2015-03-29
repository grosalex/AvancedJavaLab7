import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MultiCastSender {
	public MultiCastSender(){
		DatagramSocket socket = null;
		DatagramPacket outPacket = null;
		byte[] outBuf;
		final int PORT = 8888;

		try {
			socket = new DatagramSocket();	
			String msg ="";

			while (true) {
				BufferedReader entreeClavier;

				try {
					entreeClavier = new BufferedReader(new InputStreamReader(System.in));
					msg = entreeClavier.readLine();
				}    catch (Exception exc) {
					System.out.println(exc);
				}
				if(msg!="") {
					outBuf = msg.getBytes();

					//Send to multicast IP address and port
					InetAddress address = InetAddress.getByName("224.2.2.3");
					outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);

					socket.send(outPacket);
				}

			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
