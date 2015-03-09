import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;


public class ServerChannel {
	private InetAddress inet;
	private int port;
	private ServerSocketChannel serverChannel;
	
	public ServerChannel(InetAddress inet,int port){
		this.inet=inet;
		this.port=port;
	}

	public void start() throws IOException{
		serverChannel  = ServerSocketChannel.open();

		serverChannel.socket().bind(new InetSocketAddress(9999));

		while(true){
		    SocketChannel socketChannel =serverChannel.accept();

		    //do something with socketChannel...
		}
	}
}
