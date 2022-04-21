public class Queue {
    private Node head = null, tail = null;
  
    private class Node {
      public String data;
      public Node   next;
      public Node(String d, Node n) {
        data = d;
        next = n;
      }
    }
  
    public void enqueue(String s) {
      if( head == null ) {
        head = tail = new Node(s, null);
      } else {
        tail.next = new Node(s, null);
        tail      = tail.next;
      }
    }
  
    public String dequeue() {
      Node t = head;
  
      head = head.next;
      if( head == null )
        tail = null;
  
      return t.data;
    }
  
    public boolean empty() {
      return head == null;
    }
  
    public String[] toArray() {
      // Assumes at least one node exists.
      // (a dumb way to do it, but don't change this).
      Node t = head;
      int n = 1;
      while( t.next != null ) {
        t = t.next;
        n++;
      }
        
      // Create array and return it.
      String[] arr = new String[n];
      int i = 0;
      for( t = head; t != null; t = t.next)
        arr[i++] = t.data;
      return arr;
    }
  }
