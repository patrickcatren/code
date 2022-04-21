import java.util.*;

public class TestEncryptors {
  public static void main(String[] args) throws Throwable {
    // Create ArrayList of all supported encryptors
    ArrayList<Encryptor> E = new ArrayList<Encryptor>();
    E.add(new Clear());
    E.add(new Caesar());
    E.add(new Vigenere());

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
    System.out.print("message  : ");
    String plaintext = System.console().readLine();

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
    //ensure characters in message are valid
    char[] ptxtArr = plaintext.toCharArray();
    j = 0;
    for(j = 0; j < ptxtArr.length; j++){
        try{
            if((int)vc[((int)ptxtArr[j]-42)] > 0);
        } catch(IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Error: Character '" + ptxtArr[j] + "' is not allowed in the message.");
        }
    }

    // Encrypt, decrypt print sumamry of results
    E.get(i).init(password);
    String ciphertext = E.get(i).encrypt(plaintext);
    String hopefully = E.get(i).decrypt(ciphertext);
    System.out.println("plain : " + plaintext);
    System.out.println("cipher: " + ciphertext);
    System.out.println("decryp: " + hopefully);
  }
}