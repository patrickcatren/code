/**
 * Creates a constructor for the enigma machine and methods to rotate it.
 */

public class Enigma{
    private char[] startersarray;
    private Rotor innerRotor;
    private Rotor middleRotor;
    private Rotor outerRotor;

    //constructor for enigma
    public Enigma(int id1, int id2, int id3, String starters){
        String[] rotorStringArray = new String[5];
        rotorStringArray[0] = "#GNUAHOVBIPWCJQXDKRYELSZFMT";
        rotorStringArray[1] = "#EJOTYCHMRWAFKPUZDINSXBGLQV";
        rotorStringArray[2] = "#BDFHJLNPRTVXZACEGIKMOQSUWY";
        rotorStringArray[3] = "#NWDKHGXZVRIFJBLMAOPSCYUTQE";
        rotorStringArray[4] = "#TGOWHLIFMCSZYRVXQABUPEJKND";
        startersarray = starters.toCharArray();
        innerRotor = new Rotor(rotorStringArray[id1-1], startersarray[0]);
        middleRotor = new Rotor(rotorStringArray[id2-1], startersarray[1]);
        outerRotor = new Rotor(rotorStringArray[id3-1], startersarray[2]);
    }

    //does the steps for encryption
    public String encrypt(String message){
        int length = message.length();
        char[] messageArray = message.toCharArray();
        for(int i = 0; i < length; i++){
            int alignment1 = innerRotor.returnInt(messageArray[i]);
            char outerChar = outerRotor.returnChar(alignment1);
            int alignment2 = middleRotor.returnInt(outerChar);
            char encrypted = outerRotor.returnChar(alignment2);
            messageArray[i] = encrypted;
            innerRotor.Rotate();
            if(innerRotor.returnChar(0) == innerRotor.getFirst())
            {
                middleRotor.Rotate();
                if(middleRotor.returnChar(0) == middleRotor.getFirst())
                {
                    outerRotor.Rotate();
                }
            }

        }
        String finalmessage = new String(messageArray);
        return finalmessage;
    }
    //does the steps for decryption
    public String decrypt(String message){
        int length = message.length();
        char[] messageArray = message.toCharArray();
        for(int i = 0; i < length; i++){
            int alignment1 = outerRotor.returnInt(messageArray[i]);
            char outerChar = middleRotor.returnChar(alignment1);
            int alignment2 = outerRotor.returnInt(outerChar);
            char decrypted = innerRotor.returnChar(alignment2);
            messageArray[i] = decrypted;
            innerRotor.Rotate();
            if(innerRotor.returnChar(0) == innerRotor.getFirst())
            {
                middleRotor.Rotate();
                if(middleRotor.returnChar(0) == middleRotor.getFirst())
                {
                    outerRotor.Rotate();
                }
            }
        }
        String finalmessage = new String(messageArray);
        return finalmessage;
    }


}