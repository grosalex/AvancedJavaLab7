import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {

	private ChatView v;
	private static String address;
	private static String nick;
	private static int port;
	private static boolean nio;
	private static boolean debug;
	private static boolean server;
	private static boolean multicast;


	public void start(Stage primaryStage) {
		if(multicast){
			MultiCastClient c = new MultiCastClient(debug);
			v = new ChatView(primaryStage, nick,c,debug);
			v.start();
		}
		else{

			v = new ChatView(primaryStage, address,port, nick,debug);
			v.start();
		}
	}

	public static void main(String[] args) {
		try {
			nio = false;
			debug = false;
			server = false;
			multicast = false;
			address = "127.0.0.1";
			port = 5454;
			nick = "Guest";

			options(args);
			if(multicast) {
				launch(args);
			}
			else if (nio) {
				try {
					new ServerChannel(address,port,debug);
				} catch (IOException e) {
					if(debug) {
						Logger log = Logger.getLogger(Controller.class.getName());
						ConsoleHandler ch =  new ConsoleHandler();
						log.addHandler(ch);
						log.severe(e.getMessage());
					}
				}
			}
			else if(server){
				Server s= new Server(port, InetAddress.getByName(address), debug);
				s.start();
			}
			else {
				launch(args);
			}

		} catch (UnknownHostException e) {
			if(debug) {
				Logger log = Logger.getLogger(Controller.class.getName());
				ConsoleHandler ch =  new ConsoleHandler();
				log.addHandler(ch);
				log.severe(e.getMessage());
			}
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
				address = g.getOptarg();
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
