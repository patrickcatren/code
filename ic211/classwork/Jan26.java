public class Time {
    private int hh, mm, ss; 
    public boolean equal(Time t);
    public static boolean equal(Time t1, Time t2);
    
    public void setHour(int hh){
        if(h < 0){
            System.out.print("ERROR");
            return;
        }
        this.hh = hh;
    }

    public static void main(String[] args){
        Time t = newTime();
        t.setHour(5);
    }
}