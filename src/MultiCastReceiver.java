import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;


public class MultiCastReceiver implements Runnable{
	private boolean debug;
	public MultiCastReceiver(boolean debug) {
		this.debug = debug;
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
			}
		} catch (IOException ioe) {
			if(debug) {
				Logger log = Logger.getLogger(Controller.class.getName());
				ConsoleHandler ch =  new ConsoleHandler();
				log.addHandler(ch);
				log.severe(ioe.getMessage());
			}
		}
		
	}
}
