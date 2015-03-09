import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientHandler implements Runnable {
	
	Socket socket;
	
	public ClientHandler (Socket s) {
		this.socket = s;
		
	}
	
	@Override
	public void run() {
		System.out.println(getMessage(socket));
		
		
	}
	
	private String getMessage(Socket c) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
