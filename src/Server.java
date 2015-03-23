import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Server {
	int port;
	InetAddress inet;
	ServerSocket server;
	Socket client;
	String message;
	HashMap <Socket,OutputStream> msgs;

	public Server(int inPort, InetAddress inInet){
		this.port=inPort;
		this.inet=inInet;
		message="";
		msgs = new HashMap<>();
	}

	public void start(){

		try {
		
			server=new ServerSocket(port);

			while(true) {
				client =server.accept();
				client.getOutputStream().write("Welcome :D \n".getBytes());
				msgs.put(client, client.getOutputStream());

				ClientHandler ch = new ClientHandler(client, this);
				ch.start();
	
			}


		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public synchronized void broadcast(Socket s, String msg) {
	
		
		for (Map.Entry<Socket, OutputStream> entry : msgs.entrySet())
		{
		    if(!entry.getKey().equals(s)) {
		    	try {
		    		if(!msg.isEmpty())
					entry.getValue().write((msg+"\n").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    
		}
	}

}
