import java.util.*;
 
public class Lab08 {

  public static void main(String[] args) {
    Scanner  sc = new Scanner(System.in);
    ModQueue Q  = new ModQueue();

    do {
      System.out.print("> ");
      String cmd = sc.next();

      if( cmd.equals("quit") ) {
        break;
      } else if( cmd.equals("clearto") ) {
        Q.dequeue(sc.next());
      } else if( cmd.equals("add") )   {
        Q.enqueue(sc.next());
      } else if( cmd.equals("dump") )   {
        System.out.println(Q.dump());
      }
    } while( true );
  }
}
