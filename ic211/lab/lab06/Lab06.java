public class Lab06{

  public static void main(String[] args){
    int N = 200;

    if( args.length != 0 )
      N = Integer.parseInt(args[0]);

    MovingDotList L = new MovingDotList();
    int count = 0;

    while( true ) { //loop forever; must use Ctrl-c to end program
      // every N rounds add another group of MovingDots to the List
      if( count % N == 0 ) {
        L.add(new RedDot(45,50));   // add a RedDot to the list
        L.add(new BlueDot(55,50));  // add a BlueDot to the list
      }

      // for each MovingDot in our MovingDotList
      for(MovingDot md : L){
        System.out.println(md); //print each MovingDot
        md.step(); //move each MovingDot
      }
      System.out.println("done");
      System.out.flush();

      count++;
    }
  }
}
