public class ShiftClear implements Encryptor {
    public String getAlgName() { return "clear"; }
    public void   init(char[] key) { }
    
    /** 
     * @param plain The plaintext to be encrypted.
     * @return String that is the encrypted plaintext.
     */
    public String encrypt(String plain) {
        String result = plain;
        if(plain.length() < 16){
            for(int i = 0; i < (16 - plain.length()); i++){
                result = result + 'x';
            }
        }
        else if(plain.length()>16){
            char[] A = plain.toCharArray();
            char[] B = new char[16];
            for(int i = 0; i < 16; i++){
                B[i] = A[i];
            }
            result = new String(B);
        }
        return result;
    }
    public String decrypt(String cipher){ return cipher; }
  }
