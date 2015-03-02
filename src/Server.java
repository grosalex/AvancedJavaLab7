import java.io.IOException;
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
			server.bind(server.getLocalSocketAddress(),20);
			client=server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
