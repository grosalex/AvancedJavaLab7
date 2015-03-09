import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientHandler extends Thread implements Runnable {
	
	Socket socket;
	String message;
	
	public ClientHandler (Socket s) {
		this.socket = s;
		this.message = "";
	}
	
	@Override
	public void run() {
		System.out.println(getMessage(socket));
		while(message!=null) {
			
			message = getMessage(socket);
			if(message!=null)
				System.out.println(message);
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
