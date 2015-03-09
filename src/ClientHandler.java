import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;


public class ClientHandler extends Thread implements Runnable {
	
	Socket socket;
	String message;
	Server serv;
	HashMap <Socket, String> hm;
	
	public ClientHandler (Socket s, Server serv) {
		this.socket = s;
		this.message = "";
		this.serv = serv;
		hm = new HashMap<Socket, String>();
	}
	
	@Override
	public void run() {
		serv.broadcast(socket, message);
		while(message!=null) {
			message = getMessage(socket);

			if(message!=null){
				
				if(message.startsWith("/nick")){
					hm.put(socket, message.split(" ")[1]);
				}
				else if(hm.get(socket) != null){
					serv.broadcast(socket, "<"+ hm.get(socket)+"> " + message);
				}
				else serv.broadcast(socket, "<"+"unknown"+"> "+message);
				
			}
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
