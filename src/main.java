import java.net.InetAddress;
import java.net.UnknownHostException;


public class main {

	public static void main(String[] args) {
		try {
			InetAddress adress = InetAddress.getByName("127.0.0.1");
			Server server = new Server(1026,adress);
			
			server.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
