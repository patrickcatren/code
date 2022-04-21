import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Establishes the search class that searches through Tweets.
 */
public class Search {
    /**
     * Reads a file of tweets to create an array of tweets.
     * @param path A string for the filename.
     * @return Returns the array of Tweets.
     */
    public static Tweet[] readFile(String path) {
        Tweet[] tweetarray = new Tweet[33];
        try{
            File sometweets = new File(path);
            Scanner sc = new Scanner(sometweets);
            int i = 0;
            while(sc.hasNextLine()){
                String rawtweet = sc.nextLine();
                String[] parsedtweet = rawtweet.split("\t", 3);
                tweetarray[i] = new Tweet(parsedtweet[0], parsedtweet[1], parsedtweet[2]);
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return tweetarray;
    }
        
    public static void main(String[] args) {
        if(args[0] == null)
        {
            System.out.println("usage: java Search <tweets-file>");
            System.exit(0);
        }
        Tweet[] tweetarray = readFile(args[0]);
        for(int i = 0; i < 33; i++)
        {
            System.out.println(tweetarray[i].toString());
        }   
    }
}
