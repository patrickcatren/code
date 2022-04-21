/**
 * This class takes an array of strings and prints them in lines to
 * a specified number of characters(cols). 
 * @author MIDN Patrick Catren
 */
public class Formatter {
    /**
     * This is a constructor.
     */
    public static void writeInColumns(String[] A, int cols) {
        /**
         * Prints the array of strings into the specified length columns.
         * @param A The array of strings
         * @param cols The number of characters allowed in each row.
         */
        int tot = 0;
        for(int i = 0; i < A.length; i++)
        {
          if((A[i].length() + tot) <= cols) {
            System.out.print(A[i] + " ");
            tot = tot + A[i].length() + 1;
          }
          else if((A[i].length() + tot) > cols) {
            System.out.print("\n"+A[i] + " ");
            tot = A[i].length() + 1;
          }
          else {
            System.out.print("Not supposed to happen");
          }
        }
      }
    

    public static void main(String[] args) {
        String[] testarray = new String[8];
        testarray[0] = "These";
        testarray[1] = "are";
        testarray[2] = "the";
        testarray[3] = "times";
        testarray[4] = "that";
        testarray[5] = "try";
        testarray[6] = "men's";
        testarray[7] = "souls.";

        writeInColumns(testarray, 20);

    }

}