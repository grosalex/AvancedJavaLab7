import java.net.InetAddress;


public abstract class AbstractMultichatServer {

	InetAddress inet;
	int port;
	
	public AbstractMultichatServer(InetAddress inet, int port) {
		this.inet=inet;
		this.port=port;
	}
}
