public class Badger{
    private double weight;
    private double health;
    private double intelligence;
    private double aggressiveness;
    private double fitness;

    public Badger(double we, double he, double inte, double agg) {
        weight = we;
        health = he;
        intelligence = inte;
        aggressiveness = agg;
    }

    public double computeFitness() {
        double fitness = (0.5 * this.weight) + this.health + (1.2 * this.intelligence) + (3.0 * this.aggressiveness);
        return fitness;
    }

    public boolean isMoreFitThan(Badger A) {
        return this.computeFitness() > A.computeFitness();
    }
    
    public void printBadger(){
        System.out.println("Badger " + this.weight + " " + this.health + " " +
        this.intelligence + " " + this.aggressiveness + " = " +
        this.computeFitness());
    }


    public static void printList(Badger[] A, int length) {
        System.out.println("All Badgers: ");
        for(int i = 0; i < length; i++) {
            A[i].printBadger();
        }
    }
}