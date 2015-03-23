import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
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
		serverChannel.bind(new InetSocketAddress(inet, port));
		
		Selector selector = Selector.open();
		serverChannel.configureBlocking(false);
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true){
			System.out.println("in while true");
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = keys.iterator();
			ByteBuffer buf = ByteBuffer.allocate(8192);
			
			while(keyIterator.hasNext()) {
				System.out.println("init");
				
				SelectionKey key = keyIterator.next();

				if(key.isAcceptable()) {
					SocketChannel socketChannel = serverChannel.accept();
					socketChannel.register(selector, SelectionKey.OP_READ);


				} if (key.isConnectable()) {
					// a connection was established with a remote server.

				} if (key.isReadable()) {
					// a channel is ready for reading
					System.out.println("isreadable");

				    ((SocketChannel)key.channel()).read(buf);
				    Charset charset = Charset.defaultCharset();
				    buf.flip();
				    CharBuffer msg = charset.decode(buf);
				    System.out.println(msg);
				    buf.compact();
					
				} if (key.isWritable()) {
					// a channel is ready for writing
				}

				keyIterator.remove();
			}

			//do something with socketChannel...
		}
	}
}
