import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ServerChannel {
	private Map<SocketChannel,String> clientMap;
	
	HashMap <SocketChannel,OutputStream> msgs;

	public ServerChannel() throws IOException{
		Selector selector = Selector.open();
		msgs = new HashMap<>();

		clientMap = new HashMap<SocketChannel, String>();
		ServerSocketChannel serverSocket = ServerSocketChannel.open();

		InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);

		serverSocket.bind(hostAddress);

		serverSocket.configureBlocking(false);

		int ops = serverSocket.validOps();

		SelectionKey selectKy = serverSocket.register(selector, ops, null);

		for (;;) {

			int noOfKeys = selector.select();
			Set selectedKeys = selector.selectedKeys();

			Iterator iter = selectedKeys.iterator();

			while (iter.hasNext()) {

				SelectionKey ky = (SelectionKey) iter.next();

				if (ky.isAcceptable()) {

					SocketChannel client = serverSocket.accept();

					client.configureBlocking(false);

					client.register(selector, SelectionKey.OP_READ);
					ByteBuffer buffer = ByteBuffer.allocate(256);

					client.read(buffer);

					String output = new String(buffer.array()).trim();
					clientMap.put(client, output);
					msgs.put(client,client.socket().getOutputStream());

					System.out.println("Accepted new connection from client: " + output);

					client.write(ByteBuffer.wrap(("New user : " + output ).getBytes()));

				}

				else if (ky.isReadable()) {

					SocketChannel client = (SocketChannel) ky.channel();
					ByteBuffer buffer = ByteBuffer.allocate(256);
					client.read(buffer);

					String output = new String(buffer.array());
					broadcast(client, output);
					System.out.println(output);

				} // end if (ky...)

				iter.remove();

			} // end while loop
		} // end for loop
	}
	
	public synchronized void broadcast(SocketChannel s, String msg) {
		for (Entry<SocketChannel, OutputStream> entry : msgs.entrySet())
		{
		    if(!entry.getKey().equals(s)) {
		    	try {
		    		if(!msg.isEmpty())
					entry.getKey().write( ByteBuffer.wrap((msg+"\n").getBytes()));
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    
		}
	}

}
