import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientHandler extends Thread implements Runnable {
	
	Socket socket;
	String message;
	Server serv;
	
	public ClientHandler (Socket s, Server serv) {
		this.socket = s;
		this.message = "";
		this.serv = serv;
	}
	
	@Override
	public void run() {
		serv.broadcast(socket, message);
		while(message!=null) {
			message = getMessage(socket);
			if(message!=null)
				serv.broadcast(socket, message);
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
