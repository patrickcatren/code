public class ShiftCaesar implements Encryptor {
    private String initVectorString = "GO_NAVY_2018^mid";
    private int shiftvalue = 0;
    private Shift shifty = new Shift(); 
    public String getAlgName() { return "shift+caesar"; }
    
    /** 
     * @param key The password to initialize the cipher with.
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
     * @param pswd The message to be encrypted.
     * @return String of a hashed encrypted password.
     */
    public String encrypt(String pswd){
        Caesar salad = new Caesar();
        char[] pswdArr = pswd.toCharArray();
        salad.init(pswdArr);
        char[] initVector = initVectorString.toCharArray();
        String result = new String(initVector);
        for(int i = 0; i < 16; i++){
            initVector = result.toCharArray();
            char c = initVector[i];
            int k = (int)c % 16;
            for(int j = 0; j < k; j++){
                result = shifty.encrypt(result);
            }
            result = salad.encrypt(result);
        }
        return result;
    }
    
    /** 
     * @param cipher This method is unused.
     * @return String
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
