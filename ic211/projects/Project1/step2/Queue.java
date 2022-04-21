/**
 * Establishes the Queue class that is essentially a list of Tweets.
 */
public class Queue
{
    private Node list = null;

    /**
     * The Node class used to store Tweets and the next set of data.
     */
    private class Node {
        public Tweet data;
        public Node next;
        /**
         * Contains the individual data and next node.
         * @param d The string.
         * @param n The next node.
         */
        public Node(Tweet d, Node n) {
            data = d;
            next = n;
        }
    }

    /**
     * adds s to the back of the queue
     * @param s The Tweet to be stored into the queue.
     */
    public void enqueue(Tweet s) {
        Node newNode = new Node(s, null);
        if(this.list == null) {
            this.list = newNode;
        }
        else {
            this.findLast().next = newNode;
        }
    }

    /**
     * removes and returns Tweet from the front of the queue
     * @return Returns the tweet.
     */
    public Tweet dequeue() {
        Tweet s = this.list.data;
        this.list = this.list.next;
        return s;
    }

    /**
     * returns true if the queue is empty
     */
    public boolean empty() {
        return this.list == null;
    }
    /**
     * Returns the last node in queue
     * @return Returns the last node in queue
     */
    private Node findLast() {
        if(this.list == null) {
            System.exit(1);
        }
        for(Node p = this.list; p != null; p = p.next) {
            if(p.next == null) {
                return p;
            }
        }
        return null;
    }

    /**
     * Runs through the list and prints all of the tweets.
     */
    public void printAll() {
        for(Node P = this.list; P != null; P = P.next) {
            String output = P.data.toString();
            System.out.println(output);
        }
    }

    /**
     * Measures the length of the queue.
     * @return Integer that represents length.
     */
    public int length() {
        int count = 0;
        for(Node P = this.list; P != null; P = P.next) {
            count++;
        }
        return count;
    }

    /**
     * Clones a queue.
     * @return An exact copy of the queue.
     */
    public Queue clone() {
        Queue clone = new Queue();
        for(Node P = this.list; P != null; P = P.next) {
            clone.enqueue(P.data);
        }
        return clone;
    }
}