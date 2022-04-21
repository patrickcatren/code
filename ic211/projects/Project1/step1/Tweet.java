/**
 * Establishes the Tweet class that contains all of the data for a tweet.
 */
public class Tweet{
    private String text;
    private String user;
    private String date;
    private String[] splitdatestrings;
    private int[] splitdate;

    /**
     * Contains the text, user, and date in string forms.
     * @param newtext The string text of the tweet.
     * @param newuser The string username.
     * @param newdate The string date of the tweet.
     */
    public Tweet(String newtext, String newuser, String newdate) {
        text = newtext;
        user = newuser;
        date = newdate;

        splitdatestrings = new String[3];
        splitdatestrings = newdate.split("-", 3);
        splitdate = new int[3];
        for(int i = 0; i < 3; i++){
            splitdate[i] = Integer.parseInt(splitdatestrings[i]);
        }
    }

    /**
     * Converts a tweet to a string representation of all it's data.
     * @return tweetstring
     */
    public String toString(){
        String tweetstring = text + '\t' + '[' + user + ']' + '\t' + splitdate[1]
         + '/' + splitdate[2] + '/' + splitdate[0];
        return tweetstring;
    }
}