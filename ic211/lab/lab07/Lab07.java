import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Lab07 {
    
    /** 
     * @param filename the name of the file
     * @return Scanner
     */
    private static Scanner readFile(String filename){
        Scanner sc = null;
        try{
          sc = new Scanner(new File(filename));
          // put the call to file line counter 
          // in this scope as well
        }catch(Exception e){
          e.printStackTrace();
          System.exit(1);
        }
        return sc;
    }
    
    /** 
     * @param args Array of Strings Brought in from the command
     * @throws FileNotFoundException  throws if the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException{
        //exits with a usage message if there are too few args
        if(args.length < 2){
            System.out.println("usage: java Lab07 <infilename> <numDays>");
            System.exit(1);
        }
        //sets date to 1/1/2017
        MyDate date = new MyDate(2017, 1, 1);
        //set # of days to simulate, find end date
        int numDaysToSimulate = Integer.parseInt(args[1]);
        MyDate endDate = date.clone();
        endDate.incDay(numDaysToSimulate);
        // create a random object for use
        Random rand;
        if( args.length == 3 ){
            rand = new Random(Long.parseLong(args[2]));
        }
        else {
            rand = new Random(System.currentTimeMillis());
        }
        //read from file
        int arraySize = FileLineCounter.countLines(args[0]);
        Transaction[] eventArray = new Transaction[arraySize];
        Scanner sca = readFile(args[0]);
        for(int j = 0; j < arraySize; j++){
            String line = sca.nextLine();
            String[] splitLine = line.split(" ");
            int splitSize = splitLine.length;
            int freq = Integer.parseInt(splitLine[1]);
            boolean isMonthly = false;
            MyDate startDate = date.clone();
            MyDate limit = endDate.clone();
            double prob = 1;
            boolean isExpense = false;
            String name = " ";
            for(int i = 0; i < splitSize; i++)
            {
                if(splitLine[i].equals("prob")){
                    prob = Double.parseDouble(splitLine[(i+1)]);
                }
                if(splitLine[i].equals("for")){
                    if(isMonthly == true){
                        limit = date.clone();
                        limit.incMonth(Integer.parseInt(splitLine[(i+1)]));
                    }
                    else{
                        limit = date.clone();
                        limit.incDay(Integer.parseInt(splitLine[(i+1)]));
                    }
                }
                if(splitLine[i].equals("start")){
                    String[] splitStart = splitLine[(i+1)].split("/", 3);
                    startDate = new MyDate(Integer.parseInt(splitStart[2]), Integer.parseInt(splitStart[0]), Integer.parseInt(splitStart[1]));
                }
                if(splitLine[i].equals("expense")){ //assigns name
                    isExpense = true;
                    String[] findName = line.split(" expense ");
                    name = findName[1];
                }
                if(splitLine[i].equals("months")){
                    isMonthly = true;
                }
            }
            if(!(isExpense)){
                String[] findName = line.split(" income ");
                name = findName[1];
            }
            if(isMonthly && isExpense){
                eventArray[j] = new MonthlyExpense(freq, startDate, name, isMonthly, prob, rand, limit);
            }
            if(isMonthly && !(isExpense)){
                eventArray[j] = new MonthlyIncome(freq, startDate, name, isMonthly, prob, rand, limit);
            }
            if(!(isMonthly) && isExpense){
                eventArray[j] = new DailyExpense(freq, startDate, name, isMonthly, prob, rand, limit);
            }
            if(!(isMonthly) && !(isExpense)){
                eventArray[j] = new DailyIncome(freq, startDate, name, isMonthly, prob, rand, limit);
            }
        }
        //runs through days
        for( int j=0; j < numDaysToSimulate; j++ ) {
            // ask each event whether they have something going on "today"
            boolean first = true;
            boolean anyHappens = false;
            for(int i = 0; i < arraySize; i++){
                boolean happens = false;
                happens = eventArray[i].occurs(date);
                // if any do, print them out
                if(happens && first)
                {
                    System.out.print(date.toString() + ": " + eventArray[i].returnName());
                    first = false;
                    anyHappens = true;
                }
                else if(happens)
                {
                    System.out.print(", " + eventArray[i].returnName());
                }
            }
            if(anyHappens){
                System.out.print("\n");
            }
            // increment "today"
            date.incDay(1);
        }
    }
}
    
