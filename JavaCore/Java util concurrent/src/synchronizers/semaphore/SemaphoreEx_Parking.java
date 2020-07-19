package synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreEx_Parking {
    //����������� ����� ������ - true, �������� - false
    private static final boolean[] PARKING_PLACES = new boolean[5];
    //������������� ���� "������������", � ����� ������ �����
    //a�quire() ����� ��������� ���������� � ������� �������
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.printf("���������� �%d �������� � ��������.\n", carNumber);
            try {
                //acquire() ����������� ������ � ���������� �� ������� ����� ������ ����� ����,
                //���� ������ �� ��������, ����� ��������� ���� ����� ����������� �� ��� ���,
                //���� ������� �� �������� ������
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                //���� ��������� ����� � ���������
                synchronized (PARKING_PLACES){
                    for (int i = 0; i < 5; i++)
                        if (!PARKING_PLACES[i]) {      //���� ����� ��������
                            PARKING_PLACES[i] = true;  //�������� ���
                            parkingNumber = i;         //������� ���������� �����, ����������� �������
                            System.out.printf("���������� �%d ������������� �� ����� %d.\n", carNumber, i);
                            break;
                        }
                }

                Thread.sleep(5000);       //������ �� ���������, � �������

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber] = false;//����������� �����
                }
                
                //release(), ��������, ����������� ������
                SEMAPHORE.release();
                System.out.printf("���������� �%d ������� ��������.\n", carNumber);
            } catch (InterruptedException e) {
            }
        }
    }
}
