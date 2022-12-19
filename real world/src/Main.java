import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Main {
    public static void main(String[] args) {
        System.out.println("\nThis is a Java implementation of the Producer-Consumer Problem\n");
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        int MAX_CAPACITY = 5; // Queue Size

        for (int i = 0; i < 3; i++) {
            waiter  waiter = new waiter(MAX_CAPACITY, queue);
            Thread producerThread = new Thread(waiter, "waiter_" + (i + 1));
            producerThread.start();
        }
        for (int j = 0; j < 3; j++) {
            customer customer = new customer(queue);
            Thread consumerThread = new Thread(customer, "customer_" + (j + 1));
            consumerThread.start();
        }
    }
}