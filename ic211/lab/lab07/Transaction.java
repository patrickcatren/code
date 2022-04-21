import java.util.Random;

public abstract class Transaction {
    private int freq;
    private MyDate startDate;
    private boolean isMonthly;
    private String name;
    private double probability;
    private Random rand;
    private MyDate limit;
    public Transaction(int f, MyDate sD, String n, boolean m, double prob, Random r, MyDate l){
        freq = f;
        startDate = sD;
        name = n;
        isMonthly = m;
        probability = prob;
        rand = r;
        limit = l;
    }
    
    /** 
     * @return int returns the frequency
     */
    public int returnFrequency(){
        return freq;
    }
    
    /** 
     * @return MyDate returns the startdate
     */
    public MyDate returnStartDate(){
        return startDate;
    }
    
    /** 
     * @param A the date to be compared
     * @return boolean returns true if date A is after the start date
     */
    public boolean isAfterStart(MyDate A){
        if(A.getYear() > startDate.getYear()){
            return true;
        }
        else if(A.getYear() < startDate.getYear()){
            return false;
        }
        if(A.getMonth() > startDate.getMonth()){
            return true;
        }
        else if(A.getMonth() < startDate.getMonth()){
            return false;
        }
        if(A.getDay() > startDate.getDay()){
            return true;
        }
        else if(A.getDay() <= startDate.getDay()){
            return false;
        }
        return false;
    }
    
    /** 
     * @param A the date to be compared
     * @return boolean returns true if date a is before the limiting date
     */
    public boolean isBeforeLimit(MyDate A){
        if(A.getYear() < limit.getYear()){
            return true;
        }
        else if(A.getYear() > limit.getYear()){
            return false;
        }
        if(A.getMonth() < limit.getMonth()){
            return true;
        }
        else if(A.getMonth() > limit.getMonth()){
            return false;
        }
        if(A.getDay() < limit.getDay()){
            return true;
        }
        else if(A.getDay() >= limit.getDay()){
            return false;
        }
        return false;
    }
    
    /** 
     * @param curDate the current date to be compared
     * @return boolean returns true if the curDate is equal to the start date
     */
    public boolean isStart(MyDate curDate){
        if(curDate.getYear() == startDate.getYear()){
            if(curDate.getMonth() == startDate.getMonth()){
                if(curDate.getDay() == startDate.getDay()){
                    return true;
                }
            }
        }
        return false;
    }
    
    /** 
     * @return String returns the name
     */
    public String returnName(){
        return name;
    }
    
    /** 
     * @param curDate the current date to be compared
     * @return boolean returns true if the even occurs on the given date
     */
    boolean occurs(MyDate curDate){
        if(isMonthly){
            boolean isDay = false;
            int monthDiff = ((curDate.getYear() - startDate.getYear()) * 12 + (curDate.getMonth() - startDate.getMonth()));
            if(monthDiff < 0){
                monthDiff = monthDiff * -1;
            }
            if(isStart(curDate)==true){
                isDay = true;
            }
            if(monthDiff%returnFrequency() == 0 && isBeforeLimit(curDate) && (startDate.getDay() == curDate.getDay())){
                isDay = true;
            }
            if(monthDiff == 0 && !(isStart(curDate))){
                isDay = false;
            }
            if(isDay && probability == 1){
                return true;
            }
            if(isDay){
                double a = rand.nextDouble();
                boolean randOccurs = false;
                if(a < probability){
                    randOccurs = true;
                }
                else{
                    randOccurs = false;
                }
                if(randOccurs && isDay){
                    return true;
                }
            }
            return false;
        }
        else{  //DAILY
            int dayCount = (startDate.daysUntil(curDate));
            boolean isDay = false;
            if(isStart(curDate)){
                isDay = true;
            }
            if(dayCount < 0){
                isDay = false;
            }
            if(dayCount%returnFrequency() == 0 && isBeforeLimit(curDate) && isAfterStart(curDate)){
                isDay = true;
            }
            if(isDay && probability == 1){
                return true;
            }
            if(isDay){
                double a = rand.nextDouble();
                boolean randOccurs = false;
                if(a < probability){
                    randOccurs = true;
                }
                else{
                    randOccurs = false;
                }
                if(randOccurs && isDay){
                    return true;
                }
            }
            return false;
        }
    }
}
