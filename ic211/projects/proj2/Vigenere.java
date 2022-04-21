public class Vigenere implements Encryptor {
    private char[] key1;
    public String getAlgName() { return "vigenere"; }
    public void   init(char[] key) { 
        key1 = new char[key.length];
        for(int i = 0; i < key.length; i++){
            key1[i] = key[i];
        }
    }
    public String encrypt(String vigenere) {
        char[] ptxt = vigenere.toCharArray();
        char[] ctxt = new char[ptxt.length];
        for(int i = 0; i < ptxt.length; i++){
            int k = ((int)key1[(i%key1.length)]) - 42;
            int p = (int)ptxt[i] - 42;
            int c = (p+k)%81;
            char cc = (char)(42 + c);
            ctxt[i] = cc;
        }
        String end = new String(ctxt);

        return end;
    }
    public String decrypt(String cipher){
        char[] ctxt = cipher.toCharArray();
        char[] ptxt = new char[ctxt.length];
        for(int i = 0; i < ctxt.length; i++){
            int k = (int)key1[(i%key1.length)] -42;
            int c = (int)ctxt[i] -42;
            int p = (c+(81-k))%81;
            char pc = (char)(42 + p);
            ptxt[i] = pc;
        }
        String end = new String(ptxt);

        return end;
    }
  }
