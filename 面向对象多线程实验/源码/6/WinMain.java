package lesson5;
public class WinMain {
	public static void main(String args[]) {
		Client application; 
		LoginWindow window=new LoginWindow();
		window.loginFrame();
	      if ( args.length == 0 )
	         application = new Client( "127.0.0.1" ); 
	      else
	         application = new Client( args[ 0 ] ); 
	      application.runClient();  
	}
}
