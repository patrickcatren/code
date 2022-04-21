public class MovingDot extends Dot {
    private int row;
    private int col;
    private int direction;
    // a constructor that takes row and column position as integers
    public MovingDot(int r, int c){
        super(r,c);
        direction = 0;
        //0 = right, 1 = left, 2 = up, 3 = down
    }
    // a step method that moves the Dot in the direction that it is facing
    public void step(){
        if(direction == 0) {
            incCol();
        }
        if(direction == 1) {
            decCol();
        }
        if(direction == 2) {
            incRow();
        }
        if(direction == 3) {
            decRow();
        }
        if(direction == 4) {
            decRow();
            decCol();
        }
        if(direction == 5) {
            incRow();
            incCol();
        }

    }
    public void setDir(int dir) {
        this.direction = dir;
    }
}