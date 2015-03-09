import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;


public class ClientHandler extends Thread implements Runnable {
	
	Socket socket;
	String message;
	HashMap <Socket, String> hm;
	
	public ClientHandler (Socket s) {
		this.socket = s;
		this.message = "";
		hm = new HashMap<Socket, String>();

	}
	
	@Override
	public void run() {
		System.out.println(getMessage(socket));
		while(message!=null) {
			
			message = getMessage(socket);
			if(message!=null){
				if(message.startsWith("/nick")){
					hm.put(socket, message.split(" ")[1]);
				}
				else if(hm.get(socket) != null){
					System.out.println("<"+ hm.get(socket)+"> " + message);
				}
				else System.out.println("<"+"unknown"+"> "+message);
				
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
