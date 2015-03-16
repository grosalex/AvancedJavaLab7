import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

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
		    ByteBuffer buf = ByteBuffer.allocate(128);
		    socketChannel.read(buf);
		    CharBuffer msg = Charset.forName("UTF-8").decode(buf);
		    System.out.println(msg);
		    //do something with socketChannel...
		}
	}
}
