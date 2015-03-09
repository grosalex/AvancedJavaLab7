import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server {
	int port;
	InetAddress inet;
	ServerSocket server;
	Socket client;
	String message;
	HashMap <Socket, String> hm;
	
	public Server(int inPort, InetAddress inInet){
		this.port=inPort;
		this.inet=inInet;
		message="";
	}
	
	public void start(){
		
		try {
			hm = new HashMap<Socket, String>();
			server=new ServerSocket(port);
			while(true) {
				client =server.accept();
				ClientHandler ch = new ClientHandler(client);
				ch.start();
			}
			
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}

}
