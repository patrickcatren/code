public class Exam1{
    public static void main(String[] args) {
        int size = args.length / 4;
        int length = args.length;
        Badger[] Array = new Badger[size];
        double[] doubleArray = new double[length];
        for(int i = 0; i < length; i++) {
            doubleArray[i] = Double.parseDouble(args[i]);
        }
        for(int i = 0; i < size; i++) {
            Array[i] = new Badger(doubleArray[((1+i)*4) -4],doubleArray[((1+i)*4) -3],
            doubleArray[((1+i)*4) -2],doubleArray[((1+i)*4) -1]);
        }
        Badger best = new Badger(0.0, 0.0, 0.0, 0.0);
        for(int i = 0; i < size; i++) {
            if(Array[i].isMoreFitThan(best)) {
                best = Array[i];
            }
        }
        System.out.print("Best Badger: ");
        best.printBadger();
        Badger.printList(Array, size);

    }





}