import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Controller {

	public static void main(String[] args) {
	
		try {
			InetAddress adress = InetAddress.getByName("233.11.12.13");
			switch(args[0]){
			case "s": 
				ServerChannel server = new ServerChannel(adress,1026);
				try {
					server.start();
				} catch (IOException e) {
					System.out.println(e);
				}
				break;
			case "c":
				ClientChannel client = new ClientChannel(1026, adress);
				client.sendMessage();
				break;
			}



		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
