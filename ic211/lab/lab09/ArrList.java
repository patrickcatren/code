import java.util.*;

public class ArrList<T> implements Iterable<T>{
    private Object[] A = new Object[10];
    private int size = 0;
    
    private class Iter<T> implements Iterator<T> {
      private int curr;
      public Iter(int start) {
        curr = start;
      } 

      public boolean hasNext() {
        return curr != size;
      }

      public T next() {
        T s = (T)A[curr];
        curr+= 1;
        return s;
      }
    }
    public void add(T d){
        int location = 0;
        if((size+1)>= A.length){
            location = size();
            Object[] B = new Object[2*A.length];
            for(int j = 0; j < size; j++){
                B[j] = A[j];
            }
            A = B;
        }
        location = size;
        A[location] = d;
        size++;
    }
    public T get(int index){
        return (T)A[index];
    }
   
    public Iterator<T> iterator(){
       Iterator<T> i = new Iter<T> (0);
       return i;
    }

    public T remove(int index) throws IndexOutOfBoundsException{
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        T element = (T)A[index];
        for(int i = index; i < size; i++){
            A[i] = A[i+1];
        }
        size--;
        return element;
    }
    public boolean remove(T d){
        boolean boo = false;
        for(int i = 0; i < size; i++){
            if(d.equals((T)A[i])){
                d = remove(i);
                boo = true;
                break;
            }
        }
        return boo;
    }
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        ArrList<String> Test = new ArrList<String>();
        String s = "what";
        String t = "on";
        String v = "earth";
        Test.add(s);
        Test.add(t);
        Test.add(v);
        System.out.println(Test.get(0));
        System.out.println(Test.get(1));
        System.out.println(Test.get(2));
        boolean boo = Test.remove(t);
        for(int i = 0; i < Test.size(); i++){
            System.out.println(Test.get(i));
        }
    }

}
