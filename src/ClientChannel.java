import java.nio.channels.SocketChannel;

public class ClientChannel {
	
	SocketChannel socket;
	
	public ClientChannel(SocketChannel s) {
		this.socket = s;
	}

}
