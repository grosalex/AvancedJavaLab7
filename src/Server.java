import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;


public class Server {
	private int port;
	private InetAddress inet;
	private ServerSocket server;
	private Socket client;
	private String message;
	private HashMap <Socket,OutputStream> msgs;
	private boolean debug;

	public Server(int inPort, InetAddress inInet, boolean d){
		this.port=inPort;
		this.inet=inInet;
		this.debug = d;
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

				ClientHandler ch = new ClientHandler(client, this,debug);
				ch.start();
	
			}


		} catch (IOException e) {
			if(debug) {
				Logger log = Logger.getLogger(Controller.class.getName());
				ConsoleHandler ch =  new ConsoleHandler();
				log.addHandler(ch);
				log.severe(e.getMessage());
			}
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
					if(debug) {
						Logger log = Logger.getLogger(Controller.class.getName());
						ConsoleHandler ch =  new ConsoleHandler();
						log.addHandler(ch);
						log.severe(e.getMessage());
					}
				}
		    }
		    
		}
	}

}
