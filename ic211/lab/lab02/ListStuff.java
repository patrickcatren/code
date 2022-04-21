



public class ListStuff {
    // addToFront(s,Nold) returns a StringNode reference representing the list obtained
    // by adding s to the front of list Nold
    public static StringNode addToFront(String s, StringNode Nold) {
        // you will fill this in
        StringNode temp = new StringNode();
        temp.data = s;
        temp.next = Nold;
        Nold = temp;
        
        return Nold;
    }

    // listToArray(N) returns a reference to an array containing 
    //the same strings as in the list N (in order)
    public static String[] listToArray(StringNode N) {
        // you will fill this in
        int i = 0;
        for(StringNode a = N; a != null; a = a.next)
        {
            i++;
        }
        String[] array = new String[i];
        i = 0;
        for(StringNode a = N; a != null; a = a.next)
        {
            array[i] = a.data;
            i++;
        }
        return array;
    }
    
    public static void main(String[] args) {
        StringNode N = null;      // at this point N *is* an empty list
        N = addToFront("rat",N);  // at this point N *is* the list ("rat")
        N = addToFront("dog",N);  // at this point N *is* the list ("dog","rat")
        N = addToFront("pig",N);  // at this point N *is* the list ("pig","dog","rat")
        String[] A = listToArray(N);
        for(int i = 0; i < A.length; i++)  
        {
            System.out.println(A[i]);
        }  
    }

}