import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Controller {

	public static void main(String[] args) {
	
		try {
			InetAddress adress = InetAddress.getByName("233.11.12.13");
			switch(args[0]){
			case "s": 
				try {
					ServerChannel server = new ServerChannel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "c":
				ClientChannel client;
				try {
					client = new ClientChannel(1026, adress);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}



		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
