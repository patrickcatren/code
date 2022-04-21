/**
 * A simple Queue for storing Aircraft
*/
public class Queue {

  private Node front, back;

  /**
   * adds s to the back of the queue
   */
  public void enqueue(Aircraft s) {
    Node n = new Node(s, null);
    if( empty() )
      front = back = n;
    else
      back = back.next = n;
  }

  /**
   * removes and returns Aircraft from the front of the queue
   */
  public Aircraft dequeue() {
    if( empty() ) 
      return null;

    Aircraft str = front.data;
    front = front.next;

    // Cleanup if we just removed the only remaining Node.
    if( front == null )
      back = null;
    return str;
  }

  /**
   * returns true if the queue is empty
   */
  public boolean empty() {
    return front == null;
  }

  public int length() {
    int count = 0;
    for(Node P = this.front; P != null; P = P.next) {
      count++;
    }
    return count;
  }

  public Queue filterForRange(int given) {
    Queue filteredlist = new Queue();
    for(Node P = this.front; P != null; P = P.next) {
      if(P.data.returnRange() >= given) {
        filteredlist.enqueue(P.data);
      }
    }
    return filteredlist;
  }

  public void remove(String given) {
    for(Node P = this.front; P != null; P = P.next) {
      if(P.data.returnName().equals(given)) {
        this.dequeue();
      }
    }
  }

    public void printAll() {
        for(Node P = this.front; P != null; P = P.next) {
            String output = P.data.toString();
            System.out.println(output);
        }
    }

  private class Node {
    public Aircraft data;
    public Node next;
    public Node(Aircraft d, Node n) {
      data = d;
      next = n;
    }
  }
}
