import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Establishes the search class that searches through queues.
 */
public class Search {
    /**
     * Reads a file of tweets to create a queue of tweets.
     * @param path A string for the filename.
     * @return Returns the queue of Tweets.
     */
    public static Queue readFile(String path) {
        Queue tweetlist = new Queue();
        try{
            File sometweets = new File(path);
            Scanner sc = new Scanner(sometweets);
            while(sc.hasNextLine()){
                String rawtweet = sc.nextLine();
                String[] parsedtweet = rawtweet.split("\t", 3);
                Tweet a = new Tweet(parsedtweet[0], parsedtweet[1], parsedtweet[2]);
                tweetlist.enqueue(a);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return tweetlist;
    }
    
    public static void main(String[] args) {
        if(args[0] == null)
        {
            System.out.println("usage: java Search <tweets-file>");
            System.exit(0);
        }
        Queue tweetlist = readFile(args[0]);
        int qsize = tweetlist.length();
        System.out.println("Queue size: " + qsize);

        Scanner sc = new Scanner(System.in);
        System.out.print("? ");
        String input = sc.nextLine();
        Queue filQueue = tweetlist.clone();
        while(!(input.equals("!quit"))){
            if(input.contains("+")){
                int inputlength = input.length();
                String input2 = input.substring(1, inputlength);
                filQueue = filQueue.filterForDate(input2);
                System.out.println(input2);
                int size = filQueue.length();
                System.out.println("Queue size: " + size);
            }
            else if(input.contains("-")){
                int inputlength = input.length();
                String input2 = input.substring(1, inputlength);
                System.out.println(input2);
                filQueue = filQueue.filterForNotKeyword(input2);
                int size = filQueue.length();
                System.out.println("Queue size: " + size);
            }
            if(input.equals("!dump")) {
                filQueue.printAll();
                int size = filQueue.length();
                System.out.println("Queue size: " + size);
            }
            else if(!(input.contains("-")) && !(input.contains("+"))) {
                filQueue = filQueue.filterForKeyword(input);
                int size = filQueue.length();
                System.out.println("Queue size: " + size);
            }
            System.out.print("? ");
            input = sc.nextLine();
        }
        sc.close();
    }
}
