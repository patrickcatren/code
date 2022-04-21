import java.util.*;
import java.io.*;

public class WordRead {
  public static String[] get(String fname) {
    Scanner sc = null;
    try {
      sc = new Scanner(new FileReader(fname));
    }   
    catch(IOException e) {
      e.printStackTrace(); System.exit(1);
    }
    
    StringNode N = null;
    while(sc.hasNext()) {
      N = ListStuff.addToFront(sc.next(), N);
    }

    StringNode M = null;
    for(StringNode temp = N; temp != null; temp = temp.next) {
      M = ListStuff.addToFront(temp.data, M);
    }

    String[] A = ListStuff.listToArray(M);
   
    return A;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String fname = in.next();
    String[] A = get(fname);
    for(int i = 0; i < A.length; i++) {
      System.out.println(A[i]);
    }
  }
}