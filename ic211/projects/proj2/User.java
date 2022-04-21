public class User {
    private String u;
    private String a;
    private String p;
    User(String username, String algorithm, String password){
        u = username;
        a = algorithm;
        p = password;
    }
    
    /** 
     * @return String that represents the username of the specific User.
     */
    public String getName(){
        return u;
    }
    
    /** 
     * @return String that represents the algorithm of a specific User.
     */
    public String getAlgo(){
        return a;
    }
    
    /** 
     * @return String that represents the password of a specific User.
     */
    public String getPswd(){
        return p;
    }
}