public interface Encryptor {
    public String getAlgName();
    public void   init(char[] key);
    public String encrypt(String plain);
    public String decrypt(String cipher);
  }