import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	int port;
	InetAddress inet;
	ServerSocket server;
	Socket client;
	private DataInputStream streamIn =  null;
	public Server(int inPort, InetAddress inInet){
		this.port=inPort;
		this.inet=inInet;
	}
	
	public void start(){
		
		try {
			server=new ServerSocket(port);
			client=server.accept();
			client.getOutputStream().write("Bienvenue".getBytes());;
			open();
			boolean done = false;
	         while (!done)
	         {  try
	            {  String line = streamIn.readUTF();
	               System.out.println(line);
	               done = line.equals(".bye");
	            }
	            catch(IOException ioe)
	            {  done = true;
	            }
	         }
	         close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void close() {
			try {
				if (client != null)
					client.close();	     
				if (streamIn != null)  
					streamIn.close();		

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void open() {
		try {
			streamIn = new DataInputStream(new BufferedInputStream(client.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
