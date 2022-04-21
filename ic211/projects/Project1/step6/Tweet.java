/**
 * Establishes the Tweet class that contains all of the data for a tweet.
 */
public class Tweet {
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
     * Compares two dates and returns true if they are equal.
     * @param day The day.
     * @param month The month.
     * @param year The year.
     * @return true or false.
     */
    public boolean compareDates(int day, int month, int year) {
        if((splitdate[2] == day)&&(splitdate[1] == month)&&(splitdate[0] == year)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Converts a tweet to a string representation of all it's data.
     * @return tweetstring
     */
    public String toString() {
        String tweetstring = text + '\t' + '[' + user + ']' + '\t' + splitdate[1]
         + '/' + splitdate[2] + '/' + splitdate[0];
        return tweetstring;
    }

    /**
     * Checks if a tweet contains a keyword.
     * @param keyword The search term.
     * @return A boolean, true if it contains the keyword.
     */
    boolean containsKeyword(String keyword) {
        String a = this.text;
        a = a.toLowerCase();
        String b = keyword;
        b = b.toLowerCase();

        return a.contains(b);
    }
}