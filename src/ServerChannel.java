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
	
	public ServerChannel() throws IOException{
		Selector selector = Selector.open();


		ServerSocketChannel serverSocket = ServerSocketChannel.open();

		InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);

		serverSocket.bind(hostAddress);

		serverSocket.configureBlocking(false);

		int ops = serverSocket.validOps();

		SelectionKey selectKy = serverSocket.register(selector, ops, null);

		for (;;) {
			System.out.println("Waiting for select...");

			int noOfKeys = selector.select();


			Set selectedKeys = selector.selectedKeys();

			Iterator iter = selectedKeys.iterator();

			while (iter.hasNext()) {

				SelectionKey ky = (SelectionKey) iter.next();

				if (ky.isAcceptable()) {

					SocketChannel client = serverSocket.accept();

					client.configureBlocking(false);

					client.register(selector, SelectionKey.OP_READ);

					System.out.println("Accepted new connection from client: " + client);

				}

				else if (ky.isReadable()) {

					SocketChannel client = (SocketChannel) ky.channel();

					ByteBuffer buffer = ByteBuffer.allocate(256);

					client.read(buffer);

					String output = new String(buffer.array()).trim();

					System.out.println(output);

		
				} // end if (ky...)

				iter.remove();

			} // end while loop
		} // end for loop
	}

}
