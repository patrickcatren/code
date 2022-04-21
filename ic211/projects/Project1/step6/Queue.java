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
     * Adds a tweet to the front of the queue.
     * @param s The tweet to be added.
     */
    public void add2front(Tweet s) {
        Node newNode = new Node(s, this.list);
        this.list = newNode;
    }

    public Queue reverseQueue(){
        Node newNode = new Node(this.list.data, null); //creates last from first of old
        Queue reversedQueue = new Queue();
        while(this.list != null)
        {
            reversedQueue.add2front(this.list.data);
            this.list = this.list.next;
        }
        return reversedQueue;
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
     * Finds tweets that match a keyword in a queue and returns a different queue of those that match.
     * @param keyword The search term.
     * @return The new queue of only matching tweets.
     */
    public Queue filterForKeyword(String keyword) {
        Queue filteredlist = new Queue();
        for(Node P = this.list; P != null; P = P.next) {
            if(P.data.containsKeyword(keyword)) {
                filteredlist.enqueue(P.data);
            }
        }
        return filteredlist;
    }

    /**
     * Finds tweets that don't contain a keyword in a queue and returns a different queue of those that match.
     * @param keyword The search term.
     * @return The new filtered queue.
     */
    public Queue filterForNotKeyword(String keyword) {
        Queue filteredlist = new Queue();
        for(Node P = this.list; P != null; P = P.next) {
            if(!(P.data.containsKeyword(keyword))) {
                filteredlist.enqueue(P.data);
            }
        }
        return filteredlist;
    }

    /**
     * Returns a new queue of tweets from a given queue that match a certain date.
     * @param date A string of the date to search.
     * @return The filtered queue.
     */
    public Queue filterForDate(String date) {
        Queue filteredlist = new Queue();
        String[] inputdatestrings = date.split("-", 3);
        int[] inputdate = new int[3];
        for(int i = 0; i < 3; i++) {
            inputdate[i] = Integer.parseInt(inputdatestrings[i]);
        }
        for(Node P = this.list; P != null; P = P.next) {
            if(P.data.compareDates(inputdate[2], inputdate[1], inputdate[0])) {
                filteredlist.enqueue(P.data);
            }
        }
        return filteredlist;
    }

    /**
     * Clones a queue.
     * @return An exact copy of the queue.
     */
    public Queue clone() {
        Queue clone = new Queue();
        for(Node P = this.list; P != null; P = P.next) {
            clone.add2front(P.data);
        }
        Queue A = clone.reverseQueue();
        return A;
    }
}