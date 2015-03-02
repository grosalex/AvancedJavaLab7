import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
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
	
	public Server(int inPort, InetAddress inInet){
		this.port=inPort;
		this.inet=inInet;
	}
	
	public void start(){
		
		try {
			server=new ServerSocket(port);
			while(true) {
				readMessage(server.accept());
				close();
			}
			
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void close() {
			try {
				if (client != null)
					client.close();	     
						

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void readMessage(Socket c) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			System.out.println(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
