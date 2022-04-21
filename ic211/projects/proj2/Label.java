public class Label {
    private String u;
    private String a;
    private String d;
    Label(String username, String algorithm, String data){
        u = username;
        a = algorithm;
        d = data;
    }
    
    /** 
     * @return String that represents the username of a specific Label.
     */
    public String getName(){
        return u;
    }
    
    /** 
     * @return String that represents the algorithm of a specific Label.
     */
    public String getAlgo(){
        return a;
    }
    
    /** 
     * @return String that represents the encrypted data of a specific Label.
     */
    public String getData(){
        return d;
    }
}