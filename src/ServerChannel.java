import java.net.InetAddress;
import java.nio.channels.SocketChannel;


public class ServerChannel {
	private InetAddress inet;
	private int port;
	
	public ServerChannel(InetAddress inet,int port){
		this.inet=inet;
		this.port=port;
	}
	

}
