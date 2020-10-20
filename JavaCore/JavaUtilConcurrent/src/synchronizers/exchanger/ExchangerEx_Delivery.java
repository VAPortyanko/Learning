package synchronizers.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerEx_Delivery {
    //������� ��������, ������� ����� ������������ ����� String
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        String[] p1 = new String[]{"{������� A->D}", "{������� A->C}"};//��������� ���� ��� 1-�� ���������
        String[] p2 = new String[]{"{������� B->C}", "{������� B->D}"};//��������� ���� ��� 2-�� ���������
        new Thread(new Truck(1, "A", "D", p1)).start();//���������� 1-� �������� �� � � D
        Thread.sleep(100);
        new Thread(new Truck(2, "B", "C", p2)).start();//���������� 2-� �������� �� � � �
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
                System.out.printf("� �������� �%d ���������: %s � %s.\n", number, parcels[0], parcels[1]);
                System.out.printf("�������� �%d ������ �� ������ %s � ����� %s.\n", number, dep, dest);
                Thread.sleep(1000 + (long) Math.random() * 5000);
                System.out.printf("�������� �%d ������� � ����� �.\n", number);
                parcels[1] = EXCHANGER.exchange(parcels[1]);//��� ������ exchange() ����� ����������� � ����
                //���� ������ ����� ������� exchange(), ����� ����� ���������� ����� ���������
                System.out.printf("� �������� �%d ����������� ������� ��� ������ %s.\n", number, dest);
                Thread.sleep(1000 + (long) Math.random() * 5000);
                System.out.printf("�������� �%d ������� � %s � ��������: %s � %s.\n", number, dest, parcels[0], parcels[1]);
            } catch (InterruptedException e) {
            }
        }
    }
}
