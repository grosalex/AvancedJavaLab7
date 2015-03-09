import java.net.InetAddress;


public class ServerChannel {
	private InetAddress inet;
	private int port;
	public ServerChannel(InetAddress inet,int port){
		this.inet=inet;
		this.port=port;
	}
}
