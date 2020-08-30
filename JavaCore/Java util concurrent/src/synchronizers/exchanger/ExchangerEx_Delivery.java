package synchronizers.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerEx_Delivery {
    // Create an exchanger that will exchange type String
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        String[] p1 = new String[]{"{parcel A->D}", "{parcel A->C}"}; // We form the cargo for the 1st truck
        String[] p2 = new String[]{"{parcel B->C}", "{parcel B->D}"}; // We form the cargo for the 2nd truck
        new Thread(new Truck(1, "A", "D", p1)).start(); // We send the 1st truck from A to D
        Thread.sleep(100);
        new Thread(new Truck(2, "B", "C", p2)).start(); // We send the 2nd truck from B to C
    }

    public static class Truck implements Runnable {
        private int number;
        private String dep;
        private String dest;
        private String[] parcels;

        public Truck(int number, String departure, String destination, String[] parcels) {
            this.number = number;
            this.dep = departure;
            this.dest = destination;
            this.parcels = parcels;
        }

        @Override
        public void run() {
            try {
                System.out.printf("In the track #%d was loaded: %s � %s.\n", number, parcels[0], parcels[1]);
                System.out.printf("Track #%d left point %s to point %s.\n", number, dep, dest);
                Thread.sleep(1000 + (long) Math.random() * 5000);
                System.out.printf("Truck #%d arrived at point E.\n", number);
                parcels[1] = EXCHANGER.exchange(parcels[1]); // When exchange () is called, the thread blocks and waits
                // while another thread calls exchange (), after that there will be an exchange of packages.
                System.out.printf("The parcel for point %s was moved to truck #%d.\n", dest, number);
                
                Thread.sleep(1000 + (long) Math.random() * 5000);
                System.out.printf("Truck #%d arrived at %s and delivered: %s � %s.\n", number, dest, parcels[0], parcels[1]);
            } catch (InterruptedException e) {
            }
        }
    }
}
