import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	int port;
	InetAddress inet;
	ServerSocket server;
	Socket client;
	String message;
	
	public Server(int inPort, InetAddress inInet){
		this.port=inPort;
		this.inet=inInet;
		message="";
	}
	
	public void start(){
		
		try {
			server=new ServerSocket(port);

			client =server.accept();
			while(message!=null) {
				message = getMessage(client);
				if(message!=null)
					System.out.println(message);
			}
			client.close();
	        
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
