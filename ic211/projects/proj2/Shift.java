public class Shift implements Encryptor {
    public String getAlgName() { return "shift"; }
    public void   init(char[] key) { }
    
    /** 
     * @param plain The unshifted message.
     * @return String The message shifted left by 1.
     */
    public String encrypt(String plain) {
        char[] a = plain.toCharArray();
        char[] end = new char[16];
        char first = a[0];
        for(int i = 0; i < 15; i++){
            end[i] = a[i+1];
        }
        end[15] = first;
        String result = new String(end);
        return result;
    }
    
    /** 
     * @param cipher The shifted message.
     * @return String The unshifted message.
     */
    public String decrypt(String cipher){
        char[] a = cipher.toCharArray();
        char[] end = new char[16];
        char last = a[15];
        for(int i = 0; i < 15; i++){
            end[i+1] = a[i];
        }
        end[0] = last;
        String result = new String(end);
        return result;
    }
  }