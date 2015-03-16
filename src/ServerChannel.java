import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
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

		serverChannel.socket().bind(new InetSocketAddress(port));
		serverChannel.configureBlocking(false);
	    SocketChannel socketChannel = serverChannel.accept();
	    Selector selector = Selector.open();
	    
	    SelectionKey key = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		while(true){

			  int readyChannels = selector.select();

			  if(readyChannels == 0) continue;


			  Set<SelectionKey> selectedKeys = selector.selectedKeys();

			  Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

			  while(keyIterator.hasNext()) {

			    SelectionKey selectionKey = keyIterator.next();

			    if(key.isAcceptable()) {
			        // a connection was accepted by a ServerSocketChannel.

			    } else if (key.isConnectable()) {
			        // a connection was established with a remote server.

			    } else if (key.isReadable()) {
			        // a channel is ready for reading

			    } else if (key.isWritable()) {
			        // a channel is ready for writing
			    }

			    keyIterator.remove();
			  }
		    //do something with socketChannel...
		}
	}
}
