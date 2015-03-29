import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {

	private ChatView v;
	private static InetAddress address;
	private static String addressString;
	private static String nick;
	private static int port;
	private static boolean nio;
	private static boolean debug;
	private static boolean server;
	private static boolean multicast;
	

	public void start(Stage primaryStage) {
		if(multicast){
			MultiCastClient c = new MultiCastClient();
			v = new ChatView(primaryStage, address, nick,c);
			System.out.println("here");
			v.start();
		}
		else{
			v = new ChatView(primaryStage, address, nick);
			v.start();

		}
	}

	public static void main(String[] args) {
		try {
			address = InetAddress.getByName("233.11.12.13");
			addressString = "127.0.0.1";
			port = 5454;
			nick = "Guest";
			
			options(args);
			
			switch(args[0]){
			case "s": 
				try {
					ServerChannel server = new ServerChannel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "c":
				nick=args[1];
				launch(args);
				break;
			case "m":
				System.out.println("there");
				multicast=true;
				launch(args);

				break;
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}


	}

	public static void options(String[] args)  {
		LongOpt[] longopts = new LongOpt [8] ;
		StringBuffer sb = new StringBuffer( ) ;
		longopts[0] = new LongOpt ( "address" , LongOpt.REQUIRED_ARGUMENT , sb , 'a' ) ;
		longopts [1] = new LongOpt ( "help" , LongOpt.NO_ARGUMENT , null , 'h' ) ;
		longopts [2] = new LongOpt ( "nio" , LongOpt.NO_ARGUMENT , null , 'n' ) ;
		longopts [3] = new LongOpt ( "port" , LongOpt.REQUIRED_ARGUMENT , null , 'p' ) ;
		longopts [4] = new LongOpt ( "multicast" , LongOpt.NO_ARGUMENT , null , 'm' ) ;
		longopts [5] = new LongOpt ( "server" , LongOpt.NO_ARGUMENT , null , 's' ) ;
		longopts [6] = new LongOpt ( "debug" , LongOpt.NO_ARGUMENT , null , 'd' ) ;
		longopts [7] = new LongOpt ( "nick" , LongOpt.REQUIRED_ARGUMENT , null , 'w' ) ;
		Getopt g = new Getopt ( "Chat" , args, "a:hnp:msdn:" ,longopts ) ;
		int c ;
		while ((c=g.getopt())!=-1){
			switch ( c ) {
			case 'a':
				addressString = g.getOptarg();
				break;
			case 'h':
				System.out.println("-a , -- address = ADDR set the IP address");
				System.out.println("-d , -- debug display error messages");
				System.out.println("-h , -- help display this help and quit");
				System.out.println("-m , -- multicast start the client en multicast mode");
				System.out.println("-n , -- nio configure the server in NIO mode");
				System.out.println("-p , -- port = PORT set the port");
				System.out.println("-s , -- server start the server");
				break;
			case 'n':
				nio = true;
				break;
			case 'p':
				port = Integer.parseInt(g.getOptarg());
				break;
			case 'm':
				System.out.println("over");
				multicast = true;
				break;
			case 's':
				server = true;
				break;
			case 'd':
				debug = true;
				break;
				
			case 'w':
				nick = g.getOptarg();
				break;
				
			default:
				System.out.println("Invalid option");
			}
		}
	}

}
