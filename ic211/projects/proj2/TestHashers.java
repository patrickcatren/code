import java.util.*;

public class TestHashers {
  
  /** 
   * @param args This program does not use arguments from the command line.
   */
  public static void main(String[] args){
    // Create ArrayList of all supported encryptors
    ArrayList<Encryptor> E = new ArrayList<Encryptor>();
    E.add(new ShiftClear());
    E.add(new ShiftCaesar());
    E.add(new ShiftVigenere());

    //set up a char array for error checking
    char[] vc = new char[80];
    for(int i = 0; i < 80; i++){
        vc[i] = (char)(i+42);
    }

    // Get alg,psw,msg from user
    System.out.print("algorithm: ");
    String encalg = System.console().readLine();
    System.out.print("password : ");
    char[] password = System.console().readPassword();

    // Find index of encryptor (throw exception if not found)
    int i = -1;
    try {
      while( !E.get(++i).getAlgName().equals(encalg) ) ;
    } catch(IndexOutOfBoundsException e) {
      throw new NoSuchElementException("Unknown algorithm '" + encalg +"'.");
    }
    //ensure characters in password are valid
    int j = 0;
    for(j = 0; j < password.length; j++){
        try{
            if((int)vc[((int)password[j]-42)] > 0);
        } catch(IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Error: Character '" + password[j] + "' is not allowed in the password.");
        }
    }
    String passwordString = new String(password);
    // Encrypt, decrypt print sumamry of results
    E.get(i).init(password);
    String ciphertext = E.get(i).encrypt(passwordString);
    System.out.println("password read : " + passwordString);
    System.out.println("hash computed : " + ciphertext);
  }
}