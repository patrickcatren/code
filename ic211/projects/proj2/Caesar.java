public class Caesar implements Encryptor {
    private int shiftvalue = 0;
    public String getAlgName() { return "caesar"; }
    
    /** 
     * @param key The password to initialize an instance of this cipher.
     */
    public void   init(char[] key) { 
        //add error catcher if 60<sc<0
        int s = 18;
        for(int i = 0; i < key.length; i++){
            s = s + (key[i] - 42);
        }
        s = s % 81;
        shiftvalue = s;
    }
    
    /** 
     * @param caesar The unencrypted message.
     * @return String the result of encrypting the message with a Caesar cipher.
     */
    public String encrypt(String caesar) { 
        char[] buff = caesar.toCharArray();
        int buffLength = buff.length;
        char[] result = new char[buffLength];
        for(int i = 0; i < buffLength; i++){
            char pc = buff[i];
            int p  = (int)pc - 42;
            int c  = (p + shiftvalue) % 81;
            char cc = (char)(42 + c);
            result[i] = cc;
        }
        String end = new String(result);
        return end;
    }
    
    /** 
     * @param cipher The encrypted text to be decrypted.
     * @return String of the decrypted result.
     */
    public String decrypt(String cipher){
        char[] buff = cipher.toCharArray();
        int buffLength = buff.length;
        char[] result = new char[buffLength];
        for(int i = 0; i < buffLength; i++){
            char pc = buff[i];
            int p  = (int)pc - 42;
            int c  = (p + (81 - shiftvalue)) % 81;
            char cc = (char)(42 + c);
            result[i] = cc;
        }
        String end = new String(result);
        return end;
    }
  }
