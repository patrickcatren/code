/**
 * Creates a constructor for the rotor and has a main that tests it. Also
 * includes methods for rotating, raturning characters on a rotor at an 
 * index, and returning an index from a rotor given the char. Also includes
 * a method that returns the private field topletter.
 */
public class Rotor{
    private String rotorString;
    private char topletter;
    //constructor for the rotor
    public Rotor(String fullrotor, char first){
        rotorString = fullrotor;
        topletter = first;

        while(returnChar(0) != topletter)
        {
            Rotate();
        }    
    }
    //method for rotating
    public void Rotate(){
        char[] array = rotorString.toCharArray();
        char origlast = array[26];
        for(int i = 26; i > 0; i--){
            array[i] = array[i-1];
        }
        array[0] = origlast;
        rotorString = new String(array);
    }
    //returns a char on a rotor given an index
    public char returnChar(int input){
        char[] array = rotorString.toCharArray();
        return array[input];
    }
    //returns an index from a rotor given a char
    public int returnInt(char input){
        char[] array = rotorString.toCharArray();
        for(int i = 0; i < 27; i++){
            if(array[i] == input){
                return i;
            }
        }
        return -1;
    }
    //returns the private field topletter
    public char getFirst(){
        return topletter;
    }
    //for testing
    public static void main(String[] args) {
        Rotor testrotor = new Rotor("#GNUAHOVBIPWCJQXDKRYELSZFMT", 'Q');
        System.out.println(testrotor.rotorString);
    }

}