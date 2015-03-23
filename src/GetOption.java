import java.io.IOException;
import java.lang.Object;

import gnu.getopt.LongOpt;
import gnu.getopt.Getopt;

public class GetOption extends Getopt{

	
	public GetOption(String progname, String[] argv, String optstring) {
		super(progname, argv, optstring);
		// TODO Auto-generated constructor stub
	}

	public void options() throws Exception {
		LongOpt[] longopts = new LongOpt [ 2 ] ;
		StringBuffer sb = new StringBuffer( ) ;
		longopts[0] = new LongOpt ( "add" , LongOpt.REQUIRED_ARGUMENT , sb , 'a' ) ;
		longopts [ 1 ] = new LongOpt ( "block" , LongOpt.NO_ARGUMENT , null , 'b' ) ;
		Getopt g = new Getopt ( progname , argv, "a:b" ,longopts ) ;
		int c ;
		while ((c=g.getopt())!=-1){
			switch ( c ) {
				case 'a':
				System.out.println("Option a : "+g.getOptarg()) ;
				break;
				case 'h':
				System.out.println("Option b");
				break;
				case 'n':
					System.out.println("Option b");
					break;
				case 'p':
					System.out.println("Option b");
					break;
				case 's':
					System.out.println("Option b");
				break;
				default:
				System.out.println("Invalid option");
			}
		}
	}
}
